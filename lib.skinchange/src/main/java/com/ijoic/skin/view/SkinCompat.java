package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * 皮肤组件
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.4
 */
public class SkinCompat<T> {

  private @NonNull final WeakReference<T> refCompat;
  private @Nullable SkinTask<T> skinTask;

  /**
   * 构造函数
   *
   * @param compat 组件
   * @param skinTask 换肤任务
   */
  public SkinCompat(@NonNull T compat, @Nullable SkinTask<T> skinTask) {
    refCompat = new WeakReference<>(compat);
    this.skinTask = skinTask;
  }

  /**
   * 判断皮肤组件是否为空
   *
   * @return 判断结果
   */
  boolean isEmpty() {
    return refCompat.get() == null || skinTask == null;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof SkinCompat)) {
      return false;
    }
    T compat = refCompat.get();
    Object otherCompat = ((SkinCompat) other).refCompat.get();

    return (compat == null && otherCompat == null) || (compat != null && compat.equals(otherCompat));
  }

  /**
   * 执行换肤
   */
  public void performSkinChange() {
    T compat = refCompat.get();
    SkinTask<T> skinTask = this.skinTask;

    if (compat == null || skinTask == null) {
      return;
    }
    skinTask.performSkinChange(compat);
  }

}
