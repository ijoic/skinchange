package com.ijoic.skin.callback;

/**
 * 皮肤更改回调
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public interface SkinChangeCallback {

  /**
   * 皮肤替换开始
   */
  void onStart();

  /**
   * 皮肤替换出错
   *
   * @param errorMessage 错误信息
   */
  void onError(String errorMessage);

  /**
   * 皮肤替换完成
   */
  void onComplete();

  /**
   * 默认回调（空对象）
   */
  SkinChangeCallback DEFAULT_CALLBACK = new SkinChangeCallback() {
    @Override
    public void onStart() {
    }

    @Override
    public void onError(String errorMessage) {
    }

    @Override
    public void onComplete() {
    }
  };

}
