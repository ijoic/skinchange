package com.ijoic.skin.callback;

import android.view.View;

/**
 * 皮肤任务
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.3
 *
 * @param <T> 视图类型
 */
public interface SkinTask<T extends View> {

  /**
   * 执行皮肤替换
   *
   * @param view 视图
   */
  void performSkinChange(T view);

}
