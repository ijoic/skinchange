package com.ijoic.skin.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * 活动视图容器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class ActivityViewContainer extends WeakViewContainer<Activity> {

  /**
   * 构造函数
   *
   * @param compat 组件
   */
  public ActivityViewContainer(Activity compat) {
    super(compat);
  }

  @Override
  protected View getView(@NonNull Activity compat) {
    return compat.findViewById(android.R.id.content);
  }

}
