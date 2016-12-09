package com.ijoic.skin.attr;

import android.support.annotation.NonNull;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 皮肤视图
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SkinView {

  private final @NonNull WeakReference<View> viewRef;
  private final @NonNull List<SkinAttr> attrs;

  /**
   * 构造函数
   *
   * @param view 视图
   * @param attrs 皮肤属性列表
   */
  SkinView(@NonNull View view, @NonNull List<SkinAttr> attrs) {
    viewRef = new WeakReference<>(view);
    this.attrs = attrs;
  }

  /**
   * 应用皮肤
   */
  public void apply() {
    View view = viewRef.get();

    if (view == null) {
      return;
    }
    for (SkinAttr attr : attrs) {
      if (attr != null) {
        attr.apply(view);
      }
    }
  }

}
