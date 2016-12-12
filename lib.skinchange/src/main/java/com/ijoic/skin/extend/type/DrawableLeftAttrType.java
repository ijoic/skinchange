package com.ijoic.skin.extend.type;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * 左侧图片属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.2
 */
public class DrawableLeftAttrType extends CompoundDrawableAttrType {
  @Override
  protected int getCompoundIndex() {
    return INDEX_LEFT;
  }
}
