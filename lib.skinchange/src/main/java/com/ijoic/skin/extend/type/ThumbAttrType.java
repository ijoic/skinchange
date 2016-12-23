package com.ijoic.skin.extend.type;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.SeekBar;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;

/**
 * 滑块属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.7
 */
public class ThumbAttrType implements SkinAttrType {

  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    if (!(view instanceof SeekBar)) {
      return;
    }
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    Drawable d = rm.getDrawableByName(resName);

    if (d != null) {
      ((SeekBar) view).setThumb(d);
    }
  }
}
