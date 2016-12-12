package com.ijoic.skin.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ijoic.skin.view.ViewContainer;

import java.util.List;

/**
 * 视图容器工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class ContainerUtils {

  /**
   * 在容器列表中，添加容器
   *
   * @param containerItems 容器列表
   * @param item 容器
   */
  public static void addItem(@NonNull List<ViewContainer> containerItems, @NonNull  ViewContainer item) {
    if (!containsItem(containerItems, item)) {
      containerItems.add(item);
    }
  }

  /**
   * 在容器列表中，移除容器
   *
   * @param containerItems 容器列表
   * @param item 容器
   */
  public static void removeItem(@NonNull List<ViewContainer> containerItems, @NonNull  ViewContainer item) {
    ViewContainer containerItem = findContainerItem(containerItems, item);

    if (containerItem != null) {
      containerItems.remove(containerItem);
    }
  }

  /**
   * 修整容器列表
   *
   * <p>把无效的容器去掉</p>
   *
   * @param containerItems 容器列表
   * @param removeCache 移除缓存
   */
  public static void trim(@NonNull List<ViewContainer> containerItems, @NonNull List<ViewContainer> removeCache) {
    removeCache.clear();

    for (ViewContainer containerItem : containerItems) {
      if (containerItem != null && containerItem.getView() == null) {
        removeCache.add(containerItem);
      }
    }
    containerItems.removeAll(removeCache);
  }

  /**
   * 查找容器
   *
   * @param containerItems 容器列表
   * @param item 查找容器
   * @return 查找结果
   */
  private @Nullable static ViewContainer findContainerItem(@NonNull List<ViewContainer> containerItems, @NonNull  ViewContainer item) {
    for (ViewContainer containerItem : containerItems) {
      if (containerItem != null && containerItem.equals(item)) {
        return containerItem;
      }
    }
    return null;
  }

  /**
   * 判断容器是否在引用列表中
   *
   * @param containerItems 容器列表
   * @param item 容器
   * @return 判断结果
   */
  @org.jetbrains.annotations.Contract("_, null -> false")
  private static boolean containsItem(@NonNull List<ViewContainer> containerItems, @NonNull  ViewContainer item) {
    return findContainerItem(containerItems, item) != null;
  }

  private ContainerUtils() {}

}
