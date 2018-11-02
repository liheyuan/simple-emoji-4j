/*
Copyright 2018 by coder4 https://github.com/liheyuan/simple-emoji-4j

Licensed under the Apache License,Version2.0(the"License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,software
distributed under the License is distributed on an"AS IS"BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/
package com.coder4.emoji;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * @author coder4
 */
public class EmojiUtilsTest {

    @Test
    public void testContainsEmoji() {
        // Nope
        String eno = "Hello no emoji";
        Assert.assertThat(EmojiUtils.containsEmoji(eno), is(false));
        // 1.1
        String e1 = "✌️";
        Assert.assertThat(EmojiUtils.containsEmoji(e1), is(true));
        // 3.0
        String e3 = "ℹ️";
        Assert.assertThat(EmojiUtils.containsEmoji(e3), is(true));
        // 4.0
        String e4 = "☔";
        Assert.assertThat(EmojiUtils.containsEmoji(e4), is(true));
        // 5.2
        String e5 = "☔";
        Assert.assertThat(EmojiUtils.containsEmoji(e5), is(true));
        // 6.0
        String e6 = "✅";
        Assert.assertThat(EmojiUtils.containsEmoji(e6), is(true));
        // 7.0
        String e7 = "\uD83C\uDFC5";
        Assert.assertThat(EmojiUtils.containsEmoji(e7), is(true));
        // 8.0
        String e8 = "\uD83C\uDFF8";
        Assert.assertThat(EmojiUtils.containsEmoji(e8), is(true));
        // 9.0
        String e9 = "\uD83D\uDD7A";
        Assert.assertThat(EmojiUtils.containsEmoji(e9), is(true));
        // 10.0
        String e10 = "\uD83E\uDD4C";
        Assert.assertThat(EmojiUtils.containsEmoji(e10), is(true));
        // 11.0
        String e11 = "\uD83E\uDD7C";
        Assert.assertThat(EmojiUtils.containsEmoji(e11), is(true));
        // 12.0
        String e12 = "\uD83E\uDD3F";
        Assert.assertThat(EmojiUtils.containsEmoji(e12), is(true));
    }

    @Test
    public void testRemoveEmoji() {
        String str = "测试✌Test汉语️";
        Assert.assertThat(EmojiUtils.removeEmoji(str), is("测试Test汉语"));
    }

    // Won't hert any English, Chinese
    @Test
    public void testChinese() {
        // ascii
        for (char e = 0; e < 255; e++) {
            Assert.assertThat(EmojiUtils.containsEmoji(e + ""), is(false));
        }
        // chinese basic
        for (char c = 0x4e00; c < 0x9fa5; c++) {
            //System.out.println(c);
            Assert.assertThat(EmojiUtils.containsEmoji(c + ""), is(false));
        }
        // chinese extends
        for (char c = 0x9FA6; c < 0x9FEF; c++) {
            //System.out.println(c);
            Assert.assertThat(EmojiUtils.containsEmoji(c + ""), is(false));
        }
        // chinese extends a
        for (char c = 0x3400; c < 0x4DB5; c++) {
            //System.out.println(c);
            Assert.assertThat(EmojiUtils.containsEmoji(c + ""), is(false));
        }
        // TODO to be add more
        // https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php
    }

}