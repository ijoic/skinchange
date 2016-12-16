package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * 皮肤组件工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.4
 */
class SkinCompatUtils {

  /**
   * 在容器列表中，添加容器
   *
   * @param compatItems 容器列表
   * @param compat 容器
   */
  static boolean addCompat(@NonNull List<SkinCompat> compatItems, @NonNull  SkinCompat compat) {
    if (!containsItem(compatItems, compat)) {
      compatItems.add(compat);
      return true;
    }
    return false;
  }

  /**
   * 在组件列表中，移除皮肤组件
   *
   * @param compatItems 组件列表
   * @param compat 要移除的皮肤组件
   */
  static @Nullable SkinCompat removeCompat(@NonNull List<SkinCompat> compatItems, @NonNull  SkinCompat compat) {
    SkinCompat compatItem = findCompat(compatItems, compat);

    if (compatItem != null) {
      compatItems.remove(compatItem);
    }
    return compatItem;
  }

  /**
   * 修整组件列表
   *
   * <p>把无效的皮肤组件去掉</p>
   *
   * @param compatItems 组件列表
   * @param removeCache 移除缓存
   */
  static void trim(@NonNull List<SkinCompat> compatItems, @NonNull List<SkinCompat> removeCache) {
    removeCache.clear();

    for (SkinCompat compatItem : compatItems) {
      if (compatItem != null && compatItem.isEmpty()) {
        removeCache.add(compatItem);
      }
    }
    compatItems.removeAll(removeCache);
  }

  /**
   * 查找皮肤组件
   *
   * @param compatItems 组件列表
   * @param compat 查找对象
   * @return 查找结果
   */
  private @Nullable static SkinCompat findCompat(@NonNull List<SkinCompat> compatItems, @NonNull  SkinCompat compat) {
    for (SkinCompat matchItem : compatItems) {
      if (matchItem != null && matchItem.equals(compat)) {
        return matchItem;
      }
    }
    return null;
  }

  /**
   * 判断皮肤组件是否在组件列表中
   *
   * @param compatItems 组件列表
   * @param compat 皮肤组件
   * @return 判断结果
   */
  @org.jetbrains.annotations.Contract("_, null -> false")
  private static boolean containsItem(@NonNull List<SkinCompat> compatItems, @NonNull  SkinCompat compat) {
    return findCompat(compatItems, compat) != null;
  }

  private SkinCompatUtils() {}

}
