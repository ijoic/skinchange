package com.ijoic.skinchange.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.ijoic.skin.SkinManager;
import com.ijoic.skinchange.R;
import com.ijoic.skinchange.TestConfig;

/**
 * 动态皮肤活动
 *
 * <p>动态创建视图，并为视图添加皮肤效果</p>
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public class DynamicSkinActivity extends Activity {

  private ViewGroup parentView;

  private String currSuffix = TestConfig.SUFFIX_DEFAULT;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SkinManager.getInstance().register(this);
    setContentView(R.layout.act_dynamic_skin);

    parentView = (ViewGroup) findViewById(R.id.child_parent);

    findViewById(R.id.button_add_view).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        performAddView();
      }
    });
    findViewById(R.id.button_toggle).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        performToggle();
      }
    });
    findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        performReset();
      }
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    SkinManager.getInstance().unregister(this);
  }

  private void performAddView() {
    parentView.addView(genChild());
  }

  private @NonNull View genChild() {
    View child = getLayoutInflater().inflate(R.layout.simple_test_item, parentView, false);
    SkinManager.getInstance().injectSkin(child);
    return child;
  }

  private void performToggle() {
    currSuffix = TestConfig.SUFFIX_DEFAULT.equals(currSuffix) ? TestConfig.SUFFIX_RED : TestConfig.SUFFIX_DEFAULT;
    SkinManager.getInstance().changeSkin(currSuffix);
  }

  private void performReset() {
    currSuffix = TestConfig.SUFFIX_DEFAULT;
    SkinManager.getInstance().removeAnySkin();
  }

}
