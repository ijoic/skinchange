package com.ijoic.skin.extend.type;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;

/**
 * 下拉背景属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.8
 */
public class PopupBackgroundAttrType implements SkinAttrType {

  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    if (!(view instanceof Spinner)) {
      return;
    }
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    Drawable d = rm.getDrawableByName(resName);

    if (d != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      ((Spinner) view).setPopupBackgroundDrawable(d);
    }
  }
}
