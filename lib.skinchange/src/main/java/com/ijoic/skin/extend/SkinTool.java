package com.ijoic.skin.extend;

import android.content.Context;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ijoic.skin.R;
import com.ijoic.skin.constant.SkinConfig;

import org.jetbrains.annotations.Contract;

/**
 * 皮肤工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SkinTool {

  /**
   * 填充皮肤TAG
   *
   * @param view 视图
   * @param resId 资源ID
   * @param type 属性类型
   */
  public static void fillTag(@NonNull View view, @AnyRes int resId, @NonNull String type) {
    Context context = view.getContext();

    if (context == null) {
      Log.i(SkinConfig.TAG, "invalid skin view [" + view.toString() + "], context not found");
      return;
    }
    String resName = context.getResources().getResourceEntryName(resId);

    if (resName == null) {
      return;
    }
    fillTag(view, resName, type);
  }

  /**
   * 填充皮肤TAG
   *
   * @param view 视图
   * @param resName 资源名称
   * @param type 属性类型
   */
  public static void fillTag(@NonNull View view, @NonNull String resName, @NonNull String type) {
    setSkinTag(view, fillTag(getSkinTag(view), resName, type));
  }

  /**
   * 填充TAG
   *
   * @param oldTag 旧TAG
   * @param resName 资源名称
   * @param type 资源类型
   * @return 填充结果
   */
  public static @NonNull String fillTag(@Nullable String oldTag, @NonNull String resName, @NonNull String type) {
    String newTagSegment = genTagSegment(resName, type);

    if (TextUtils.isEmpty(oldTag)) {
      return newTagSegment;
    }
    String matchPattern = genTagSegment("[a-zA-Z0-9_]+", type);
    String matchPatternWild = ".*" + matchPattern + ".*";

    if (oldTag.matches(matchPatternWild)) {
      return oldTag.replaceFirst(matchPattern, newTagSegment);
    }
    return joinTagSegment(oldTag, newTagSegment);
  }

  /**
   * 生成TAG片段
   *
   * @param resName 资源名称
   * @param type 资源类型
   * @return 生成结果
   */
  @Contract(pure = true)
  public static @NonNull String genTagSegment(@NonNull String resName, @NonNull String type) {
    return SkinConfig.SKIN_PREFIX + resName + ":" + type;
  }

  /**
   * 合并TAG片段
   *
   * <p>这里的合并只是简单合并，不做重复检查</p>
   *
   * @param s1 TAG片段1
   * @param s2 TAG片段2
   * @return 合并结果
   */
  @Contract(pure = true)
  public static @NonNull String joinTagSegment(@NonNull String s1, @NonNull String s2) {
    return s1 + "|" + s2;
  }

  /**
   * 获取皮肤TAG
   *
   * @param view 视图
   * @return 皮肤TAG
   */
  private static @Nullable String getSkinTag(@NonNull View view) {
    Object tag = view.getTag(R.id.skin_tag_id);

    if (tag == null) {
      tag = view.getTag();
    }
    return String.class.isInstance(tag) ? String.class.cast(tag) : null;
  }

  /**
   * 设置皮肤TAG
   *
   * @param view 视图
   * @param tag 皮肤TAG
   */
  private static void setSkinTag(@NonNull View view, String tag) {
    view.setTag(R.id.skin_tag_id, tag);
  }

  private SkinTool() {}

}
