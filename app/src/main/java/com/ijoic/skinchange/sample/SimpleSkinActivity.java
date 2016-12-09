package com.ijoic.skinchange.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ijoic.skin.SkinManager;
import com.ijoic.skinchange.R;
import com.ijoic.skinchange.TestConfig;

/**
 * 简单皮肤活动
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class SimpleSkinActivity extends Activity {

  private String currSuffix = TestConfig.SUFFIX_DEFAULT;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SkinManager.getInstance().register(this);
    setContentView(R.layout.act_simple_skin);

    findViewById(R.id.button_toggle_skin).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        currSuffix = TestConfig.SUFFIX_DEFAULT.equals(currSuffix) ? TestConfig.SUFFIX_RED : TestConfig.SUFFIX_DEFAULT;
        SkinManager.getInstance().changeSkin(currSuffix);
      }
    });
    findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        currSuffix = TestConfig.SUFFIX_DEFAULT;
        SkinManager.getInstance().removeAnySkin();
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    SkinManager.getInstance().unregister(this);
  }

}
