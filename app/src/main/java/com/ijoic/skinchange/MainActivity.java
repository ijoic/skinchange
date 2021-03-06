package com.ijoic.skinchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ijoic.skinchange.sample.DynamicSkinActivity;
import com.ijoic.skinchange.sample.OutSkinActivity;
import com.ijoic.skinchange.sample.SimpleSkinActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.button_case_simple).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, SimpleSkinActivity.class));
      }
    });
    findViewById(R.id.button_case_plugin).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, OutSkinActivity.class));
      }
    });
    findViewById(R.id.button_case_dynamic).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, DynamicSkinActivity.class));
      }
    });
  }
}
