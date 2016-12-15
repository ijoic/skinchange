package com.ijoic.skin.extend.type;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
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
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    Drawable d = rm.getDrawableByName(resName);

    if (d == null) {
      return;
    }
    if (view instanceof ListView) {
      ((ListView) view).setDivider(d);

    } else if (view instanceof LinearLayout) {
      ((LinearLayout) view).setDividerDrawable(d);
    }
  }

}
