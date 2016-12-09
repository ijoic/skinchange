package com.ijoic.skin;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.ijoic.skin.attr.SkinAttrSupport;
import com.ijoic.skin.attr.SkinView;
import com.ijoic.skin.callback.SkinChangeCallback;
import com.ijoic.skin.util.RefUtils;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 皮肤管理器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SkinManager {

  @Contract(pure = true)
  public static @NonNull SkinManager getInstance() {
    return SingletonHolder.instance;
  }

  private interface SingletonHolder {
    SkinManager instance = new SkinManager();
  }

  private SkinManager() {}

  private static final @NonNull Handler handler = new Handler();

  private final @NonNull List<WeakReference<Activity>> refActivities = new ArrayList<>();
  private final @NonNull List<WeakReference<Activity>> refActivitiesRemoveCache = new ArrayList<>();

  private @Nullable WeakReference<Context> refContext;
  private @Nullable ResourcesManager resourcesManager;
  private @Nullable SkinPreference skinPrefs;

  /**
   * 初始化
   *
   * @param context 上下文
   */
  public void init(@NonNull Context context) {
    context = context.getApplicationContext();
    refContext = new WeakReference<>(context);

    if (skinPrefs == null) {
      skinPrefs = new SkinPreference(context);
    }
    String pluginPath = skinPrefs.getPluginPath();
    String pluginPackage = skinPrefs.getPluginPackageName();
    String suffix = skinPrefs.getPluginSuffix();
    RefUtils.trim(refActivities, refActivitiesRemoveCache);

    if (!isValidPluginParams(pluginPath, pluginPackage)) {
      return;
    }

    try {
      loadPlugin(pluginPath, pluginPackage, suffix);
    } catch (Exception e) {
      e.printStackTrace();
      skinPrefs.clear();
      resourcesManager = null;
    }
  }

  /**
   * 检查插件参数是否正确
   *
   * <ul>通过条件：
   * <li>1. 插件路径、包名称不为空；</li>
   * <li>2. 插件包的内部名称，运提供的包名称一致</li></ul>
   *
   * @param pluginPath 插件路径
   * @param pluginPackage 插件包名称
   * @return 检查结果
   */
  private boolean isValidPluginParams(@Nullable String pluginPath, @Nullable String pluginPackage) {
    // check path format
    if (TextUtils.isEmpty(pluginPath) || TextUtils.isEmpty(pluginPackage)) {
      return false;
    }
    // check plugin file exist
    if (!checkFileExist(pluginPath)) {
      return false;
    }

    // check package info
    return checkPackageExist(pluginPath, pluginPackage);
  }

  private static boolean checkFileExist(@NonNull String filePath) {
    File file = new File(filePath);
    return file.exists();
  }

  private boolean checkPackageExist(@NonNull String pluginPath, @NonNull String packageName) {
    Context context = getContext();

    if (context == null) {
      return false;
    }
    PackageManager pm = context.getPackageManager();

    try {
      PackageInfo info = pm.getPackageInfo(pluginPath, PackageManager.GET_ACTIVITIES);
      return packageName.equals(info.packageName);

    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Nullable
  private Context getContext() {
    return refContext == null ? null : refContext.get();
  }

  private void loadPlugin(@NonNull String pluginPath, @NonNull String pluginPackage, @Nullable String suffix) throws Exception {
    Context context = getContext();

    if (context == null) {
      return;
    }
    AssetManager assetManager = AssetManager.class.newInstance();
    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
    addAssetPath.invoke(assetManager, pluginPath);

    Resources superRes = context.getResources();
    Resources res = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
    resourcesManager = new ResourcesManager(res, pluginPackage, suffix);
  }

  /**
   * 获取资源管理器
   *
   * @return 资源管理器
   */
  public @Nullable ResourcesManager getResourcesManager() {
    return resourcesManager;
  }

  /**
   * 注册
   *
   * <p>在{@link Activity#onCreate(Bundle)}方法中调用</p>
   *
   * @param activity 活动
   */
  public void register(@NonNull final Activity activity) {
    RefUtils.addItem(refActivities, activity);

    handler.post(new Runnable() {
      @Override
      public void run() {
        applySkin(activity);
      }
    });
  }

  /**
   * 取消注册
   *
   * <p>在{@link Activity#onDestroy()}方法中调用</p>
   *
   * @param activity 活动
   */
  public void unregister(@NonNull Activity activity) {
    RefUtils.removeItem(refActivities, activity);
  }

  private void applySkin(@NonNull Activity rootActivity) {
    applySkin(rootActivity.findViewById(android.R.id.content));
  }

  private void applySkin(@Nullable View rootView) {
    if (rootView == null) {
      return;
    }
    injectSkin(rootView);
  }

  /**
   * 移除所有皮肤
   */
  public void removeAnySkin() {
    clearPluginInfo();
    notifyChangedListeners();
  }

  private void clearPluginInfo() {
    if (skinPrefs == null) {
      return;
    }
    skinPrefs.clear();
  }

  private void notifyChangedListeners() {
    Activity activity;

    for (WeakReference<Activity> refActivity :refActivities) {
      activity = refActivity.get();

      if (activity != null) {
        applySkin(activity);
      }
    }
  }

  /**
   * 更改皮肤（应用内换肤）
   *
   * @param suffix 资源后缀
   */
  public void changeSkin(@Nullable String suffix) {
    clearPluginInfo(); // clear before
    skinPrefs.setPluginSuffix(suffix);
    notifyChangedListeners();
  }

  /**
   * 更改皮肤（应用外）
   *
   * @param pluginPath 皮肤插件路径
   * @param pluginPackage 皮肤插件包名称
   * @param callback 更改回调
   */
  public void changeSkin(@Nullable String pluginPath, @Nullable String pluginPackage, @Nullable SkinChangeCallback callback) {

  }

  /**
   * 更改皮肤（应用外）
   *
   * @param pluginPath 皮肤插件路径
   * @param pluginPackage 皮肤插件包名称
   * @param suffix 资源后缀
   * @param callback 更改回调
   */
  public void changeSkin(@Nullable final String pluginPath, @Nullable final String pluginPackage, @Nullable final String suffix, @Nullable SkinChangeCallback callback) {
    final SkinChangeCallback skinCallback = callback == null ? SkinChangeCallback.DEFAULT_CALLBACK : callback;
    skinCallback.onStart();

    if (pluginPath == null || pluginPackage == null || !isValidPluginParams(pluginPath, pluginPackage)) {
      skinCallback.onError("invalid plugin path or package");
      return;
    }

    new AsyncTask<Void, Void, Boolean>() {
      @Override
      protected Boolean doInBackground(Void... params) {
        try {
          loadPlugin(pluginPath, pluginPackage, suffix);
          return true;

        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
      }

      @Override
      protected void onPostExecute(Boolean result) {
        if (result == null || !result) {
          skinCallback.onError("loadPlugin occur error");
          return;
        }

        try {
          updatePluginInfo(pluginPath, pluginPackage, suffix);
          notifyChangedListeners();
          skinCallback.onComplete();
        } catch (Exception e) {
          e.printStackTrace();
          skinCallback.onError(e.getMessage());
        }
      }
    }.execute();
  }

  private void updatePluginInfo(@NonNull String pluginPath, @NonNull String pluginPackage, @Nullable String suffix) {
    if (skinPrefs == null) {
      throw new IllegalArgumentException("skin preference not found");
    }
    skinPrefs.setPluginPath(pluginPath);
    skinPrefs.setPluginPackageName(pluginPackage);
    skinPrefs.setPluginSuffix(suffix);
  }

  /**
   * 注入皮肤
   *
   * <p>用于动态生成的视图</p>
   *
   * @param view 视图
   */
  public void injectSkin(@NonNull View view) {
    List<SkinView> skinViews = SkinAttrSupport.getSkinViews(view);

    for (SkinView skinView : skinViews) {
      skinView.apply();
    }
  }

}
