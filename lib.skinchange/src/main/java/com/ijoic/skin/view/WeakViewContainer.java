package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * 软引用视图容器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 *
 * @param <T> 组件类型
 */
abstract class WeakViewContainer<T> implements ViewContainer {

  private final WeakReference<T> refViewCompat;

  /**
   * 构造函数
   *
   * @param compat 组件
   */
  WeakViewContainer(T compat) {
    refViewCompat = new WeakReference<>(compat);
  }

  @Override
  public final View getView() {
    T compat = refViewCompat.get();

    if (compat == null) {
      return null;
    }
    return getView(compat);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (getClass().isInstance(other)) {
      return false;
    }
    T compat = refViewCompat.get();
    T otherCompat = ((WeakViewContainer<T>) other).refViewCompat.get();

    return compat == otherCompat || (compat != null && compat.equals(otherCompat));
  }

  /**
   * 获取视图
   *
   * @param compat 组件
   * @return 视图
   */
  protected abstract View getView(@NonNull T compat);

}
