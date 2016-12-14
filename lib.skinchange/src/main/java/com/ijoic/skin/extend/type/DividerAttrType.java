package com.ijoic.skin.extend.type;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;

/**
 * 列表分隔符属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class DividerAttrType implements SkinAttrType {

  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    if (!(view instanceof ListView)) {
      return;
    }
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    Drawable d = rm.getDrawableByName(resName);

    if (d == null) {
      try {
        int dividerColor = rm.getColor(resName);
        d = new ColorDrawable(dividerColor);

      } catch (Resources.NotFoundException e) {
        e.printStackTrace();
      }
    }
    if (d != null) {
      ((ListView) view).setDivider(d);
    }
  }

}
