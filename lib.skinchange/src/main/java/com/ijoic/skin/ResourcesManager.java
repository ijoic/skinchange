package com.ijoic.skin;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.Contract;

/**
 * 资源管理器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class ResourcesManager {

  private final @NonNull Resources res;
  private final @NonNull String packageName;
  private final @NonNull String suffix;

  private static final String TYPE_DRAWABLE = "drawable";
  private static final String TYPE_COLOR = "color";

  /**
   * 构造函数
   *
   * @param res 资源
   * @param packageName 包名称
   * @param suffix 资源后缀
   */
  ResourcesManager(@NonNull Resources res, @NonNull String packageName, @Nullable String suffix) {
    this.res = res;
    this.packageName = packageName;

    if (suffix == null) {
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
    resName = appendSuffix(resName);

    try {
      return res.getDrawable(res.getIdentifier(resName, TYPE_DRAWABLE, packageName));
    } catch (Resources.NotFoundException e) {
      e.printStackTrace();
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
    resName = appendSuffix(resName);

    try {
      return res.getColorStateList(res.getIdentifier(resName, TYPE_DRAWABLE, packageName));
    } catch (Resources.NotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Contract(pure = true)
  private @NonNull String appendSuffix(String resName) {
    return resName + suffix;
  }

}
