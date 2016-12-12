package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * 碎片视图容器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class FragmentViewContainer extends WeakViewContainer<Fragment> {

  /**
   * 构造函数
   *
   * @param compat 组件
   */
  public FragmentViewContainer(Fragment compat) {
    super(compat);
  }

  @Override
  protected View getView(@NonNull Fragment compat) {
    return compat.getView();
  }

}
