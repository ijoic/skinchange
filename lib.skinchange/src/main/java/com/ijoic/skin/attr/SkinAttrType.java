package com.ijoic.skin.attr;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * 皮肤属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public interface SkinAttrType {

  /**
   * 应用皮肤
   *
   * @param view 视图
   * @param resName 资源名称
   */
  void apply(@NonNull View view, @NonNull String resName);

}
