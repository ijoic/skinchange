package com.ijoic.skin.extend.type;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;

/**
 * 文本内容属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.5
 */
public class TextAttrType implements SkinAttrType {

  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    if (!(view instanceof TextView)) {
      return;
    }
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    String text = rm.getString(resName);

    if (text != null) {
      ((TextView) view).setText(text);
    }
  }
}
