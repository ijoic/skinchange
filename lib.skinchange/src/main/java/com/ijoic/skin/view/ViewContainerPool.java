package com.ijoic.skin.view;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 视图容器对象池
 *
 * @author ijoic verstlim@126.com
 * @version 1.0.3
 */
public class ViewContainerPool {

  private final List<ViewContainer> containerItemsAll;
  private final ArrayMap<String, List<ViewContainer>> containerItemsMap;

  private final List<ViewContainer> removeCache;
  private final List<ViewContainer> removeCacheAll;

  /**
   * 构造函数
   */
  public ViewContainerPool() {
    containerItemsAll = new ArrayList<>();
    containerItemsMap = new ArrayMap<>();
    removeCache = new ArrayList<>();
    removeCacheAll = new ArrayList<>();
  }

  /**
   * 添加视图容器
   *
   * @param tag 视图容器TAG
   * @param containerItem 视图容器
   */
  public void add(@NonNull String tag, @NonNull ViewContainer containerItem) {
    List<ViewContainer> containerItems = containerItemsMap.get(tag);

    if (containerItems == null) {
      containerItems = new ArrayList<>();
      containerItemsMap.put(tag, containerItems);
    }
    if (ContainerUtils.addItem(containerItems, containerItem)) {
      containerItemsAll.add(containerItem);
    }
  }

  /**
   * 移除视图容器
   *
   * @param tag 视图容器TAG
   * @param containerItem 视图容器
   */
  public void remove(@NonNull String tag, @NonNull ViewContainer containerItem) {
    List<ViewContainer> containerItems = containerItemsMap.get(tag);

    if (containerItems == null) {
      return;
    }
    ViewContainer removedItem = ContainerUtils.removeItem(containerItems, containerItem);

    if (removedItem != null) {
      containerItemsAll.remove(removedItem);
    }
  }

  /**
   * 获取所有视图容器
   *
   * @return 所有视图容器
   */
  public @NonNull List<ViewContainer> getContainerItemsAll() {
    return containerItemsAll;
  }

  /**
   * 清除无效视图容器
   */
  public void trim() {
    removeCacheAll.clear();

    List<ViewContainer> containerItems;

    for (int i = 0, size = containerItemsMap.size(); i < size; ++i) {
      containerItems = containerItemsMap.valueAt(i);

      if (containerItems == null) {
        continue;
      }
      ContainerUtils.trim(containerItems, removeCache);
      removeCacheAll.addAll(removeCache);
    }
    containerItemsAll.removeAll(removeCacheAll);
  }

}
