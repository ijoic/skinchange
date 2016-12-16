package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ijoic.skin.SkinManager;

import org.jetbrains.annotations.Contract;

/**
 * 碎片换肤任务
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.4
 */
public class FragmentSkinTask implements SkinTask<Fragment> {

  /**
   * 获取活动换肤任务实例
   *
   * @return 活动换肤任务实例
   */
  @Contract(pure = true)
  public static @NonNull FragmentSkinTask getInstance() {
    return SingletonHolder.instance;
  }

  private interface SingletonHolder {
    FragmentSkinTask instance = new FragmentSkinTask();
  }

  private FragmentSkinTask() {}

  @Override
  public void performSkinChange(@NonNull Fragment compat) {
    View contentView = compat.getView();

    if (contentView == null) {
      return;
    }
    SkinManager.getInstance().injectSkin(contentView);
  }

}
