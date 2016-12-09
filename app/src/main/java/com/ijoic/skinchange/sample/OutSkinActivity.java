package com.ijoic.skinchange.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ijoic.skin.SkinManager;
import com.ijoic.skinchange.R;
import com.ijoic.skinchange.TestConfig;
import com.ijoic.skinchange.util.FileUtil;

import java.io.File;

/**
 * 外部皮肤活动
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class OutSkinActivity extends Activity {

  private String currSuffix = TestConfig.SUFFIX_DEFAULT;

  private static final String PLUGIN_FILE_NAME = "outskin.apk";
  private static final String PLUGIN_PACKAGE = "com.ijoic.outskin.def";

  private static final String PLUGIN_SUFFIX_DEFAULT = "";
  private static final String PLUGIN_SUFFIX_ORANGE = "orange";

  private static final String ASSET_PATH = "outskin.apk";

  private String pluginPath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SkinManager.getInstance().register(this);
    setContentView(R.layout.act_plugin_skin);

    prepareSkin();

    findViewById(R.id.button_toggle_plugin).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        currSuffix = PLUGIN_SUFFIX_DEFAULT.equals(currSuffix)
            ? PLUGIN_SUFFIX_ORANGE
            : PLUGIN_SUFFIX_DEFAULT;

        SkinManager.getInstance().changeSkin(pluginPath, PLUGIN_PACKAGE, currSuffix, null);
      }
    });
    findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        currSuffix = PLUGIN_SUFFIX_DEFAULT;
        SkinManager.getInstance().removeAnySkin();
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    SkinManager.getInstance().unregister(this);
  }

  private void prepareSkin() {
    String pluginDir = new File(getFilesDir(), "ijoic").toString();
    pluginPath = pluginDir + File.separator + PLUGIN_FILE_NAME;

    FileUtil.copyFromAsset(this, ASSET_PATH, pluginDir, PLUGIN_FILE_NAME);
  }

}
