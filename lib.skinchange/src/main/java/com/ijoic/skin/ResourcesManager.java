package com.ijoic.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.jetbrains.annotations.Contract;

import java.lang.ref.WeakReference;

/**
 * 资源管理器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class ResourcesManager {

  private @NonNull WeakReference<Resources> refResources;
  private @NonNull String packageName;
  private @NonNull String suffix;

  private static final String TYPE_DRAWABLE = "drawable";
  private static final String TYPE_COLOR = "color";

  /**
   * 构造函数
   */
  ResourcesManager() {
    refResources = new WeakReference<>(null);
    packageName = "";
    suffix = "";
  }

  /**
   * 设置资源
   *
   * @param res 资源
   */
  void setResources(@NonNull Resources res) {
    refResources = new WeakReference<>(res);
  }

  /**
   * 设置皮肤信息
   *
   * @param packageName 包名称
   * @param suffix 资源后缀
   */
  void setSkinInfo(@NonNull String packageName, @Nullable String suffix) {
    this.packageName = packageName;
    setSuffix(suffix);
  }

  /**
   * 设置资源后缀
   *
   * @param suffix 资源后缀
   */
  void setSuffix(@Nullable String suffix) {
    if (TextUtils.isEmpty(suffix)) {
      suffix = "";
    } else {
      suffix = "_" + suffix;
    }
    this.suffix = suffix;
  }

  /**
   * 根据资源名称获取Drawable
   *
   * @param resName 资源名称
   * @return Drawable
   */
  public @Nullable Drawable getDrawableByName(@NonNull String resName) {
    Resources res = getResources();

    if (res == null) {
      return null;
    }
    resName = appendSuffix(resName);
    int resId = res.getIdentifier(resName, TYPE_DRAWABLE, packageName);

    if (resId != 0) {
      try {
        return res.getDrawable(resId);
      } catch (Resources.NotFoundException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 根据资源名称获取颜色值
   *
   * @param resName 资源名称
   * @return 颜色值
   */
  public int getColor(@NonNull String resName) throws Resources.NotFoundException {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    resName = appendSuffix(resName);
    return res.getColor(res.getIdentifier(resName, TYPE_COLOR, packageName));
  }

  /**
   * 根据资源名称获取颜色列表
   *
   * @param resName 资源名称
   * @return 颜色列表
   */
  public @Nullable ColorStateList getColorStateList(String resName) {
    Resources res = getResources();

    if (res == null) {
      return null;
    }
    resName = appendSuffix(resName);
    int resId = res.getIdentifier(resName, TYPE_COLOR, packageName);

    if (resId == 0) {
      resId = res.getIdentifier(resName, TYPE_DRAWABLE, packageName);
    }
    if (resId != 0) {
      try {
        return res.getColorStateList(resId);
      } catch (Resources.NotFoundException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  @Contract(pure = true)
  private @NonNull String appendSuffix(String resName) {
    return resName + suffix;
  }

  /**
   * 获取资源
   *
   * @return 资源
   */
  private @Nullable Resources getResources() {
    return refResources.get();
  }

}
