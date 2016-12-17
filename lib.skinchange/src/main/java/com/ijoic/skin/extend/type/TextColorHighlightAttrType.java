package com.ijoic.skin.extend.type;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;

/**
 * 高亮文字颜色属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.5
 */
public class TextColorHighlightAttrType implements SkinAttrType {
  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    if (!(view instanceof TextView)) {
      return;
    }
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();

    try {
      int highlightColor = rm.getColor(resName);
      ((TextView) view).setHighlightColor(highlightColor);

    } catch (Resources.NotFoundException e) {
      e.printStackTrace();
    }
  }
}
