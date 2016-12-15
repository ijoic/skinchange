package com.ijoic.skin.extend;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.ijoic.skin.attr.SkinAttrType;
import com.ijoic.skin.extend.type.BackgroundAttrType;
import com.ijoic.skin.extend.type.DividerAttrType;
import com.ijoic.skin.extend.type.DrawableBottomAttrType;
import com.ijoic.skin.extend.type.DrawableLeftAttrType;
import com.ijoic.skin.extend.type.DrawableRightAttrType;
import com.ijoic.skin.extend.type.DrawableTopAttrType;
import com.ijoic.skin.extend.type.SrcAttrType;
import com.ijoic.skin.extend.type.TextColorAttrType;
import com.ijoic.skin.extend.type.TextColorHintAttrType;

/**
 * 皮肤属性工厂
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class AttrTypeFactory {

  private static final ArrayMap<String, SkinAttrType> attrTypesMap;

  /**
   * 注册属性类型
   *
   * @param typeName 类型名称
   * @param typeClazz 类型类
   */
  public static void register(@NonNull String typeName, @NonNull Class<? extends SkinAttrType> typeClazz) {
    insertAttrType(typeName, typeClazz);
  }

  /**
   * 获取属性类型
   *
   * @param typeName 属性类型名称
   * @return 属性类型
   */
  public static @Nullable SkinAttrType obtainAttrType(@NonNull String typeName) {
    return attrTypesMap.get(typeName);
  }

  private static void insertAttrType(@NonNull String typeName, @NonNull Class<? extends SkinAttrType> typeClazz) {
    try {
      SkinAttrType attrType = typeClazz.newInstance();
      attrTypesMap.put(typeName, attrType);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private AttrTypeFactory() {}

  static {
    attrTypesMap = new ArrayMap<>();

    // Config default attr types here.
    insertAttrType(AttrTypes.BACKGROUND, BackgroundAttrType.class);
    insertAttrType(AttrTypes.SRC, SrcAttrType.class);
    insertAttrType(AttrTypes.DIVIDER, DividerAttrType.class);
    insertAttrType(AttrTypes.TEXT_COLOR, TextColorAttrType.class);
    insertAttrType(AttrTypes.TEXT_COLOR_HINT, TextColorHintAttrType.class);
    insertAttrType(AttrTypes.DRAWABLE_LEFT, DrawableLeftAttrType.class);
    insertAttrType(AttrTypes.DRAWABLE_TOP, DrawableTopAttrType.class);
    insertAttrType(AttrTypes.DRAWABLE_RIGHT, DrawableRightAttrType.class);
    insertAttrType(AttrTypes.DRAWABLE_BOTTOM, DrawableBottomAttrType.class);
  }

}
