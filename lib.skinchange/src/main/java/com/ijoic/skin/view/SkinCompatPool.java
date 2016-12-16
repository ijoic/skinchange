package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 皮肤组件对象池
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.4
 */
public class SkinCompatPool {

  private final List<SkinCompat> compatItemsAll;
  private final ArrayMap<String, List<SkinCompat>> compatItemsMap;

  private final List<SkinCompat> removeCache;
  private final List<SkinCompat> removeCacheAll;

  /**
   * 构造函数
   */
  public SkinCompatPool() {
    compatItemsAll = new ArrayList<>();
    compatItemsMap = new ArrayMap<>();
    removeCache = new ArrayList<>();
    removeCacheAll = new ArrayList<>();
  }

  /**
   * 添加皮肤组件
   *
   * @param tag 皮肤组件TAG
   * @param compat 皮肤组件
   */
  public void add(@NonNull String tag, @NonNull SkinCompat compat) {
    List<SkinCompat> compatItems = compatItemsMap.get(tag);

    if (compatItems == null) {
      compatItems = new ArrayList<>();
      compatItemsMap.put(tag, compatItems);
    }
    if (SkinCompatUtils.addCompat(compatItems, compat)) {
      compatItemsAll.add(compat);
    }
  }

  /**
   * 移除皮肤组件
   *
   * @param tag 皮肤组件TAG
   * @param compat 皮肤组件
   */
  public void remove(@NonNull String tag, @NonNull SkinCompat compat) {
    List<SkinCompat> compatItems = compatItemsMap.get(tag);

    if (compatItems == null) {
      return;
    }
    SkinCompat removedItem = SkinCompatUtils.removeCompat(compatItems, compat);

    if (removedItem != null) {
      compatItemsAll.remove(removedItem);
    }
  }

  /**
   * 获取所有皮肤组件
   *
   * @return 所有皮肤组件
   */
  public @NonNull List<SkinCompat> getCompatItemsAll() {
    return compatItemsAll;
  }

  /**
   * 清除无效皮肤组件
   */
  public void trim() {
    removeCacheAll.clear();

    List<SkinCompat> compatItems;

    for (int i = 0, size = compatItemsMap.size(); i < size; ++i) {
      compatItems = compatItemsMap.valueAt(i);

      if (compatItems == null) {
        continue;
      }
      SkinCompatUtils.trim(compatItems, removeCache);
      removeCacheAll.addAll(removeCache);
    }
    compatItemsAll.removeAll(removeCacheAll);
  }

}
