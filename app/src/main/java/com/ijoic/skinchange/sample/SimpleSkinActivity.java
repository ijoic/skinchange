package com.ijoic.skinchange.sample;

import android.app.Activity;
import android.os.Bundle;

import com.ijoic.skin.SkinManager;

/**
 * 简单皮肤活动
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SimpleSkinActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SkinManager.getInstance().init(this);
    SkinManager.getInstance().register(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    SkinManager.getInstance().unregister(this);
  }

}
