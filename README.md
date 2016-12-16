# skinchange

[![](https://jitpack.io/v/ijoic/skinchange.svg)](https://jitpack.io/#ijoic/skinchange)

一种完全无侵入的换肤方式，支持插件式和应用内，无需重启Activity。

在[AndroidChangeSkin](https://github.com/hongyangAndroid/AndroidChangeSkin)的基础上，增加自定义属性扩展的灵活性，并且增加碎片换肤支持。

## 特点

* 插件内换肤
* 应用内换肤
* 支持插件或者应用内多套皮肤
* 支持动态增加视图的换肤
* 无需重启Activity

## 引入

1.  在根`build.gradle`中添加`jitpack`仓库：
    
    ```xml
    
    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
    
    ```
    
2. 添加库项目依赖：
    
    ```xml
    
    dependencies {
      compile 'com.github.ijoic.skinchange:lib.skinchange:v1.0.3'
    }
    
    ```
    
## 支持属性

目前支持的属性包括：

* background
* src
* divider
* textColor
* drawableLeft
* drawableTop
* drawableRight
* drawableBottom


## 使用

### 基本配置

1. 在`Application`中初始化皮肤管理器。
    
    ```
    
    public class MyApplication extends Application {
      @Override
      public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
      }
    }
    
    ```
    
2. 在`Activity`中注册、监听换肤操作。
    
    ```
    
    public class MyActivity extends Activity {
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        
        // 这里的调用，必须在视图准备完成之后
        SkinManager.getInstance().register(this);
        ...
      }
      
      @Override
      protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
      }
      
    }
    
    ```
    
3. 在`Fragment`中注册、监听换肤操作。
    
    ```
    
    public class MyFragment extends Fragment {
      
      @Override
      public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SkinManager.getInstance().register(this);
        ...
      }
      
      @Override
      public void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
      }
      
    }
    
    ```
    
4. 布局文件配置
    
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
    
5. 切换皮肤
    
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
    
    
### 动态创建视图换肤

对于动态创建的视图，在视图创建完成后，必须手动调用一次皮肤替换操作。

```java

  View view = inflater.inflate(R.id.my_layout, parent, false);
  ...
  SkinManager.getInstance().injectSkin(view);

```


### 动态更改皮肤关联属性

更改皮肤关联属性，必须先更新皮肤tag，然后重新调用一次皮肤刷新。

```java

  TextView view = (TextView) findViewById(R.id.my_text);
  int newColorRes = R.color.my_new_text_color;
  
  view.setTextColor(context.getResources().getColor(newColorRes));
  SkinTool.fillTag(view, newColorRes, AttrTypes.TEXT_COLOR);
  SkinManager.getInstance().injectSkin(view);

```


### 自定义视图换肤

自定义视图，由于关联的皮肤属性可能比较多，手动配置麻烦，因此使用皮肤任务的形式换肤。

```java

  CustomView view;

  SkinManager.getInstance().registerSkinTask(view, new SkinTask<CustomView>() {
    @Override
    public void injectSkin(@NonNull CustomView view) {
      ResourcesTool resTool = SkinManager.getInstance().getResourcesTool();

      view.setColor(resTool.getColor(R.id.my_custom_color));
      ...
    }
  });

```


### 自定义属性支持

1. 创建属性类型：
    
    ```java
    
    public class MyAttrType implements SkinAttrType {
      @Override
      public void apply(@NonNull View view, @NonNull String resName) {
        if (!(view instance MyCustomView)) {
          return;
        }
        ResourcesManager rm = SkinManager.getInstance().getResourcesManager();
        Drawable d = rm.getDrawableByName(resName);
        
        if (d != null) {
          // ..
        }
      }
    }
    
    ```
    
2. 在`Application#onCreate()`方法中，配置属性类型：
    
    ```
    
    @Override
    protected void onCreate() {
      super.onCreate();
      
      AttrTypeFactory.register("my_custom_attr", MyAttrType.class);
      // ..
      SkinManager.getInstance().init(this);
    }
    
    ```
    
3. 在xml或者动态创建的视图中使用：
    
    ```
    
    MyCustomView view = findViewById(R.id.my_custom_view);
    // ..
    
    SkinTool.fillTag(view, R.drawable.my_drawable, "my_custom_attr");
    SkinManager.getInstance().injectSkin(view);
    
    ```
    
