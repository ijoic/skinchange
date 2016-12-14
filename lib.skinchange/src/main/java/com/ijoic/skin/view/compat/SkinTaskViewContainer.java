package com.ijoic.skin.view.compat;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;

import com.ijoic.skin.callback.SkinTask;

/**
 * 换肤任务视图容器
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.3
 *
 * @param <T> 视图类型
 */
public class SkinTaskViewContainer<T extends View> extends WeakViewContainer<T> {

  private final SkinTask<T> skinTask;

  private static final Handler handler = new Handler();

  /**
   * 构造函数
   *
   * @param compat 组件
   * @param skinTask 换肤任务
   */
  public SkinTaskViewContainer(T compat, SkinTask<T> skinTask) {
    super(compat);
    this.skinTask = skinTask;
  }

  @Override
  protected View getView(@NonNull final T compat) {
    if (skinTask != null) {
      handler.post(new Runnable() {
        @Override
        public void run() {
          skinTask.performSkinChange(compat);
        }
      });
    }
    return compat;
  }
}
