package com.ijoic.skin;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.ref.WeakReference;

/**
 * 资源工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.3
 */
public class ResourcesTool {

  private @Nullable WeakReference<Resources> refResources;
  private final ResourcesManager skinResManager;

  private static final String PREFIX_RES = "res://";

  /**
   * 构造函数
   *
   * @param skinResManager 皮肤资源管理器
   */
  ResourcesTool(ResourcesManager skinResManager) {
    this.skinResManager = skinResManager;
  }

  /**
   * 设置上下文
   *
   * @param context 上下文
   */
  void setContext(@NonNull Context context) {
    refResources = new WeakReference<Resources>(context.getResources());
  }

  /**
   * 获取颜色
   *
   * <p>返回皮肤中包含的颜色，若不存在，返回当前主题中的颜色</p>
   *
   * @param resId 资源
   * @return 颜色
   */
  public @ColorInt int getColor(@ColorRes int resId) throws Resources.NotFoundException {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    try {
      return getSkinColor(res, resId);
    } catch (Resources.NotFoundException e) {
      e.printStackTrace();
    }
    return res.getColor(resId);
  }

  /**
   * 获取图片
   *
   * <p>返回皮肤中包含的图片，若不存在，返回当前主题中的图片</p>
   *
   * @param resId 资源ID
   * @return 图片
   */
  public @Nullable Drawable getDrawable(@DrawableRes int resId) {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    Drawable d = getSkinDrawable(res, resId);

    if (d != null) {
      d = res.getDrawable(resId);
    }
    return d;
  }

  /**
   * 获取颜色列表
   *
   * <p>返回皮肤中包含的颜色列表，若不存在，返回当前主题中的颜色列表</p>
   *
   * @param resId 资源ID
   * @return 颜色列表
   */
  public @Nullable ColorStateList getColorStateList(@ColorRes int resId) {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    ColorStateList colorStateList = getSkinColorStateList(res, resId);

    if (colorStateList == null) {
      colorStateList = res.getColorStateList(resId);
    }
    return colorStateList;
  }

  /**
   * 获取字符串内容
   *
   * <p>返回皮肤中包含的字符串内容，若不存在，返回当前主题中的字符串内容</p>
   *
   * @param resId 资源ID
   * @return 字符串内容
   */
  public @Nullable String getString(@StringRes int resId) {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    String text = getSkinText(res, resId);

    if (text == null) {
      text = res.getString(resId);
    }
    return text;
  }

  /**
   * 获取资源URI
   *
   * @param resId 资源ID
   * @return 资源URI
   */
  public @NonNull String getResourcesUri(int resId) {
    Resources res = getResources();

    if (res == null) {
      throw new Resources.NotFoundException();
    }
    String resName = res.getResourceEntryName(resId);
    String resType = res.getResourceTypeName(resId);
    int skinResId = skinResManager.getSkinResId(resName, resType);

    if (skinResId == 0) {
      throw new Resources.NotFoundException("resource R." + resType + "." + resName + " not found in package [" + skinResManager.getPackageName() + "]");
    }
    return PREFIX_RES + skinResManager.getPackageName() + "/" + skinResId;
  }

  private @ColorInt int getSkinColor(@NonNull Resources res, @ColorRes int resId) throws Resources.NotFoundException {
    String resName = res.getResourceEntryName(resId);
    String type = res.getResourceTypeName(resId);

    return skinResManager.getColor(resName, type);
  }

  private @Nullable Drawable getSkinDrawable(@NonNull Resources res, @DrawableRes int resId) {
    String resName = res.getResourceEntryName(resId);
    String type = res.getResourceTypeName(resId);

    return skinResManager.getDrawableByName(resName, type);
  }

  private @Nullable ColorStateList getSkinColorStateList(@NonNull Resources res, @ColorRes int resId) {
    String resName = res.getResourceEntryName(resId);
    String type = res.getResourceTypeName(resId);

    return skinResManager.getColorStateList(resName, type);
  }

  private @Nullable String getSkinText(@NonNull Resources res, @StringRes int resId) {
    String resName = res.getResourceEntryName(resId);
    String type = res.getResourceTypeName(resId);

    return skinResManager.getString(resName, type);
  }

  private @Nullable Resources getResources() {
    return refResources == null ? null : refResources.get();
  }

}
