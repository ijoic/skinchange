package com.ijoic.skin.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 引用工具
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class RefUtils {

  /**
   * 在引用列表中，添加列表项
   *
   * @param refItems 引用列表
   * @param item 数据项
   * @param <T> 数据项类型
   */
  public static<T> void addItem(@NonNull List<WeakReference<T>> refItems, @NonNull  T item) {
    if (!containsItem(refItems, item)) {
      refItems.add(new WeakReference<>(item));
    }
  }

  /**
   * 在引用列表中，移除列表项
   *
   * @param refItems 引用列表
   * @param item 数据项
   * @param <T> 数据项类型
   */
  public static<T> void removeItem(@NonNull List<WeakReference<T>> refItems, @NonNull  T item) {
    WeakReference<T> refItem = findRefItem(refItems, item);

    if (refItem != null) {
      refItems.remove(refItem);
    }
  }

  /**
   * 修整引用列表
   *
   * <p>把无效的引用去掉</p>
   *
   * @param refItems 引用列表
   * @param removeCache 移除缓存
   * @param <T> 数据项类型
   */
  public static<T> void trim(@NonNull List<WeakReference<T>> refItems, @NonNull List<WeakReference<T>> removeCache) {
    removeCache.clear();

    for (WeakReference<T> refItem : refItems) {
      if (refItem != null && refItem.get() == null) {
        removeCache.add(refItem);
      }
    }
    refItems.removeAll(removeCache);
  }

  /**
   * 查找引用项
   *
   * @param refItems 引用列表
   * @param item 数据项
   * @param <T> 数据项类型
   * @return 引用项
   */
  private @Nullable static<T> WeakReference<T> findRefItem(@NonNull List<WeakReference<T>> refItems, @NonNull  T item) {
    for (WeakReference<T> refItem : refItems) {
      if (refItem != null && item.equals(refItem.get())) {
        return refItem;
      }
    }
    return null;
  }

  /**
   * 判断数据项是否在引用列表中
   *
   * @param refItems 引用列表
   * @param item 数据项
   * @param <T> 数据项类型
   * @return 判断结果
   */
  @org.jetbrains.annotations.Contract("_, null -> false")
  private static<T> boolean containsItem(@NonNull List<WeakReference<T>> refItems, @Nullable  T item) {
    return item != null && findRefItem(refItems, item) != null;
  }

  private RefUtils() {}

}
