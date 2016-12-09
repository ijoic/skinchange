package com.ijoic.skin.attr;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.ijoic.skin.R;
import com.ijoic.skin.constant.SkinConfig;
import com.ijoic.skin.extend.AttrTypeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 皮肤属性支持
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SkinAttrSupport {

  /**
   * 获取皮肤视图列表
   *
   * @param rootView 根视图
   * @return 皮肤视图列表
   */
  public static @NonNull List<SkinView> getSkinViews(@NonNull View rootView) {
    List<SkinView> skinViews = new ArrayList<>();
    addSkinViews(rootView, skinViews);
    return skinViews;
  }

  private static void addSkinViews(@NonNull View view, @NonNull List<SkinView> skinViews) {
    SkinView skinView = getSkinView(view);

    if (skinView != null) {
      skinViews.add(skinView);
    }

    if (view instanceof ViewGroup) {
      ViewGroup rootContainer = (ViewGroup) view;

      for (int i = 0, size = rootContainer.getChildCount(); i < size; ++i) {
        addSkinViews(rootContainer.getChildAt(i), skinViews);
      }
    }
  }

  private static @Nullable SkinView getSkinView(@NonNull View view) {
    Object tag = view.getTag(R.id.skin_tag_id);

    if (tag == null) {
      tag = view.getTag();
    }
    if (tag == null || !(tag instanceof String)) {
      return null;
    }
    String tagStr = (String) tag;
    List<SkinAttr> attrs = parseSkinAttrs(tagStr);

    if (!attrs.isEmpty()) {
      changeViewTag(view);
      return new SkinView(view, attrs);
    }
    return null;
  }

  private static @NonNull List<SkinAttr> parseSkinAttrs(String tagStr) {
    List<SkinAttr> attrs = new ArrayList<>();

    if (TextUtils.isEmpty(tagStr)) {
      return attrs;
    }
    String[] items = tagStr.split("[|]");

    for (String item : items) {
      String[] resItems;
      String resName;
      String resType;

      // check prefix
      if (!item.startsWith(SkinConfig.SKIN_PREFIX)) {
        continue;
      }

      // check format length
      resItems = item.split(":");

      if (resItems.length != 3) {
        continue;
      }

      resName = resItems[1];
      resType = resItems[2];

      // check for attr type
      SkinAttrType attrType = AttrTypeFactory.obtainAttrType(resType);

      if (attrType == null) {
        continue;
      }

      // add
      attrs.add(new SkinAttr(attrType, resName));
    }
    return attrs;
  }

  private static void changeViewTag(@NonNull View view) {
    Object tag = view.getTag(R.id.skin_tag_id);

    if (tag == null) {
      tag = view.getTag();
      view.setTag(R.id.skin_tag_id, tag);
      view.setTag(null);
    }
  }

  private SkinAttrSupport() {}

}
