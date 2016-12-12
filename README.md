# skinchange

一种完全无侵入的换肤方式，支持插件式和应用内，无需重启Activity。

在[AndroidChangeSkin](https://github.com/hongyangAndroid/AndroidChangeSkin)的基础上，增加自定义属性扩展的灵活性，并且增加碎片换肤支持。

## 特点

* 插件内换肤
* 应用内换肤
* 支持插件或者应用内多套皮肤
* 支持动态增加视图的换肤
* 无需重启Activity

## 引入

下载skinchange，作为module依赖至主项目，例如：

```java

dependencies {
  compile project(':skinchange')
}

```

## 使用

* Application

    `Application`中调用`SkinManager.getInstance().init(this);`。

        public class MyApplication extends Application {
          @Override
          public void onCreate() {
            super.onCreate();
            SkinManager.getInstance().init(this);
          }
        }

* Activity

    在需要换肤的`Activity`的`onCreate()`和`onDestroy`中，分别：
    
    ```java
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      SkinManager.getInstance().register(this);
      // ..
    }
    
    @Override
    protected void onDestroy() {
      super.onDestroy();
      SkinManager.getInstance().unregister(this);
    }
    
    ```
    
* 布局文件
    
    布局文件中添加支持，主要依赖于tag属性：
    
    例如：
    
    ```xml
    
    <TextView
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:tag="skin:item_text_color:textColor"
      android:textColor="@color/item_text_color"/>
    
    ```
    tag属性分为3部分组成：
    
    * skin
    * 资源名称，插件包中的资源名称，需要于app内使用的资源名称一致。
    * 支持的属性，目前支持src、background、textColor，可自由扩展。
    
    3部分，必须以:分隔符拼接。
    
    对于一个 `View`需要多个换肤属性的，`android:tag="skin:item_text_color:textColor|skin:icon:src"`同样使用|进行分隔。
    
    简言之：需要换肤的视图，按上述格式在xml文件中添加`tag`就可以了。
    
* 换肤API
    
    插件式：
    
    ```java
    
    SkinManager.getInstance().changeSkin(
      skinPath,
      "com.ijoic.skin_plugin",
      new SkinChangeCallback() {
        @Override
        public void onStart() {
        }
        @Override
        public void onError(String errorMessage) {
        }
        @Override
        publc void onComplete() {
        }
      }
    );
    
    ```
    
    应用内：
    
    ```java
    
    SkinManager.getInstance().changeSkin(suffix);
    
    ```
    
    应用内多套皮肤以后缀区别就可以，比如：`main_bg`，皮肤资源可以为：`main_bg_red`、`main_bg_green`等。
    
    换肤时，直接传入后缀，例如上面的`red`、`green`。
    
    
