package com.ijoic.skin.view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.ijoic.skin.SkinManager;

import org.jetbrains.annotations.Contract;

/**
 * 活动换肤任务
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.4
 */
public class ActivitySkinTask implements SkinTask<Activity> {

  /**
   * 获取活动换肤任务实例
   *
   * @return 活动换肤任务实例
   */
  @Contract(pure = true)
  public static @NonNull ActivitySkinTask getInstance() {
    return SingletonHolder.instance;
  }

  private interface SingletonHolder {
    ActivitySkinTask instance = new ActivitySkinTask();
  }

  private ActivitySkinTask() {}

  @Override
  public void performSkinChange(@NonNull Activity compat) {
    View contentView = compat.findViewById(android.R.id.content);

    if (contentView == null) {
      return;
    }
    SkinManager.getInstance().injectSkin(contentView);
  }
}
