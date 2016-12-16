package com.ijoic.skin.view;

import android.support.annotation.NonNull;

/**
 * 皮肤任务
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.3
 *
 * @param <T> 视图类型
 */
public interface SkinTask<T> {

  /**
   * 执行皮肤替换
   *
   * @param compat 组件
   */
  void performSkinChange(@NonNull T compat);

}
