package com.ijoic.skinchange;

import android.app.Application;

import com.ijoic.skin.SkinManager;

/**
 * 皮肤App
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SkinApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    SkinManager.getInstance().init(this);
  }

}
