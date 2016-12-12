package com.ijoic.skin.extend;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * 皮肤工具测试
 *
 * @author ijoic verstlim@126.com
 * @version 1.0
 */
@RunWith(AndroidJUnit4.class)
public class SkinToolTest {

  private static final String COLOR_RED = "color_red";
  private static final String COLOR_GREEN = "color_green";
  private static final String COLOR_WHITE = "color_blue";

  private static final String ATTR_COLOR = "color";
  private static final String ATTR_BACKGROUND = "background";

  private static final String TAG_EMPTY = "";

  @Test
  public void testFillTag() throws Exception {
    String tsColorRed = SkinTool.genTagSegment(COLOR_RED, ATTR_COLOR);
    String tsColorGreen = SkinTool.genTagSegment(COLOR_GREEN, ATTR_COLOR);
    String tsBackgroundWhite = SkinTool.genTagSegment(COLOR_WHITE, ATTR_BACKGROUND);
    String tsBackgroundRed = SkinTool.genTagSegment(COLOR_RED, ATTR_BACKGROUND);
    String tsCR_BW = SkinTool.joinTagSegment(tsColorRed, tsBackgroundWhite);
    String tsCG_BW = SkinTool.joinTagSegment(tsColorGreen, tsBackgroundWhite);
    String tsCR_BR = SkinTool.joinTagSegment(tsColorRed, tsBackgroundRed);

    // "" -> tsColorRed
    assertEquals(tsColorRed, SkinTool.fillTag(TAG_EMPTY, COLOR_RED, ATTR_COLOR));

    // tsColorRed -> tsColorRed
    assertEquals(tsColorRed, SkinTool.fillTag(tsColorRed, COLOR_RED, ATTR_COLOR));

    // tsColorRed -> tsColorGreen
    assertEquals(tsColorGreen, SkinTool.fillTag(tsColorRed, COLOR_GREEN, ATTR_COLOR));

    // tsColorRed -> tsColorRed | tsBackgroundWhite
    assertEquals(tsCR_BW, SkinTool.fillTag(tsColorRed, COLOR_WHITE, ATTR_BACKGROUND));

    // tsColorRed | tsBackgroundWhite -> tsColorRed | tsBackgroundWhite
    assertEquals(tsCR_BW, SkinTool.fillTag(tsCR_BW, COLOR_RED, ATTR_COLOR));

    // tsColorRed | tsBackgroundWhite -> tsColorGreen | tsBackgroundWhite
    assertEquals(tsCG_BW, SkinTool.fillTag(tsCR_BW, COLOR_GREEN, ATTR_COLOR));

    // tsColorRed | tsBackgroundWhite -> tsColorRed | tsBackgroundWhite
    assertEquals(tsCR_BW, SkinTool.fillTag(tsCR_BW, COLOR_WHITE, ATTR_BACKGROUND));

    // tsColorRed | tsBackgroundWhite -> tsColorRed | tsBackgroundRed
    assertEquals(tsCR_BR, SkinTool.fillTag(tsCR_BW, COLOR_RED, ATTR_BACKGROUND));
  }

}
