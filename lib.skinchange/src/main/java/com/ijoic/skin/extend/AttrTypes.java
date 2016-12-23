package com.ijoic.skin.extend;

/**
 * 皮肤属性类型（默认）
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
public interface AttrTypes {

  /**
   * 背景[View]
   */
  String BACKGROUND = "background";

  /**
   * 文字内容[TextView]
   */
  String TEXT = "text";

  /**
   * 文字颜色[TextView]
   */
  String TEXT_COLOR = "textColor";

  /**
   * 高亮文字颜色[TextView]
   */
  String TEXT_COLOR_HIGHLIGHT = "textColorHighlight";

  /**
   * 提示文字颜色[TextView]
   */
  String TEXT_COLOR_HINT = "textColorHint";

  /**
   * 链接文字颜色[TextView]
   */
  String TEXT_COLOR_LINK = "textColorLink";

  /**
   * 左侧图片[TextView]
   */
  String DRAWABLE_LEFT = "drawableLeft";

  /**
   * 上侧图片[TextView]
   */
  String DRAWABLE_TOP = "drawableTop";

  /**
   * 右侧图片[TextView]
   */
  String DRAWABLE_RIGHT = "drawableRight";

  /**
   * 下侧图片[TextView]
   */
  String DRAWABLE_BOTTOM = "drawableBottom";

  /**
   * 按钮图片[CompoundButton]
   */
  String BUTTON = "button";

  /**
   * 图片来源[ImageView]
   */
  String SRC = "src";

  /**
   * 分隔符[ListView|LinearLayout]
   */
  String DIVIDER = "divider";

  /**
   * 列表选择器[AbsListView]
   */
  String LIST_SELECTOR = "listSelector";

  /**
   * 不定进度图片[ProgressBar]
   */
  String INDITERMINATE_DRAWABLE = "indeterminateDrawable";

  /**
   * 进度图片[ProgressBar]
   */
  String PROGRESS_DRAWABLE = "progressDrawable";

  /**
   * 滑块[SeekBar]
   */
  String THUMB = "thumb";

}
