package com.liteiot.common.util;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class:  RandomStrGenerator
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/28 9:38
 * Desc:   RandomStrGenerator
 * src: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
 */
public class RandomStrGenerator {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(shuffleArray(chars)[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }


    /**
     * 参见：https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     *
     * @param ar 待洗牌的数组
     * @return
     */
    // Implementing Fisher–Yates shuffle
    private static String[] shuffleArray(String[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
}
