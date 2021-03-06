package com.ijoic.skin.extend.type;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.ijoic.skin.ResourcesManager;
import com.ijoic.skin.SkinManager;
import com.ijoic.skin.attr.SkinAttrType;
import com.ijoic.skin.constant.SkinConfig;

/**
 * 背景属性类型
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class BackgroundAttrType implements SkinAttrType {

  @Override
  public void apply(@NonNull View view, @NonNull String resName) {
    ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
    Drawable d = rm.getDrawableByName(resName);

    if (d != null) {
      hackSetBackground(view, d);
    } else {
      try {
        int color = rm.getColor(resName);
        view.setBackgroundColor(color);
      } catch (Resources.NotFoundException e) {
        Log.i(SkinConfig.TAG, "resource color not found [" + resName + "].");
      }
    }
  }

  private void hackSetBackground(@NonNull View view, @NonNull Drawable background) {
    if (Build.VERSION.SDK_INT >= 16) {
      setBackgroundV16(view, background);
    } else {
      setBackgroundDefault(view, background);
    }
  }

  @SuppressLint("Deprecation")
  private void setBackgroundDefault(@NonNull View view, @NonNull Drawable background) {
    view.setBackgroundDrawable(background);
  }

  @TargetApi(16)
  private void setBackgroundV16(@NonNull View view, @NonNull Drawable background) {
    view.setBackground(background);
  }

}
