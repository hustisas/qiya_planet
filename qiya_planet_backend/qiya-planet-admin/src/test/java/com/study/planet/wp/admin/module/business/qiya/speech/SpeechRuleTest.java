package com.study.planet.wp.admin.module.business.qiya.speech;

import org.junit.Assert;
import org.junit.Test;

public class SpeechRuleTest {

    @Test
    public void mapsStars() {
        Assert.assertEquals(3, SpeechStarRule.stars(90D));
        Assert.assertEquals(3, SpeechStarRule.stars(100D));
        Assert.assertEquals(2, SpeechStarRule.stars(89.9D));
        Assert.assertEquals(2, SpeechStarRule.stars(75D));
        Assert.assertEquals(1, SpeechStarRule.stars(74.9D));
        Assert.assertEquals(1, SpeechStarRule.stars(60D));
        Assert.assertEquals(0, SpeechStarRule.stars(59.9D));
        Assert.assertEquals(0, SpeechStarRule.stars(0D));
        Assert.assertEquals("完美", SpeechStarRule.label(3));
        Assert.assertEquals("中等", SpeechStarRule.label(2));
        Assert.assertEquals("一般", SpeechStarRule.label(1));
        Assert.assertEquals("不准确", SpeechStarRule.label(0));
    }

    @Test
    public void acceptsWordSentenceAndPoem() {
        Assert.assertTrue(SpeechTextRule.allowed("cup"));
        Assert.assertTrue(SpeechTextRule.allowed("I see a cup on the table."));
        Assert.assertTrue(SpeechTextRule.allowed("床前明月光，"));
        Assert.assertFalse(SpeechTextRule.allowed(""));
        Assert.assertFalse(SpeechTextRule.allowed("hello <script>"));
    }
}