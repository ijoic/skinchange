package com.ijoic.skin.attr;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * 皮肤属性
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
class SkinAttr {

  private @NonNull SkinAttrType attrType;
  private @NonNull String resName;

  /**
   * 构造函数
   *
   * @param attrType 属性类型
   * @param resName 资源名称
   */
  SkinAttr(@NonNull SkinAttrType attrType, @NonNull String resName) {
    this.attrType = attrType;
    this.resName = resName;
  }

  /**
   * 应用皮肤
   *
   * @param view 视图
   */
  void apply(@NonNull View view) {
    attrType.apply(view, resName);
  }

}
