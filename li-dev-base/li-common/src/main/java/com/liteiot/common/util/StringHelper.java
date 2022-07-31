package com.liteiot.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 获取对象的string方法
 */
public class StringHelper {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 将Double装换为String
     *
     * @param num
     * @return
     */
    public static String changeDoubleHalfUpToString(Double num, int scale) {
        if (num == null) return null;
        BigDecimal numBigDecimal = new BigDecimal(num.toString());
        BigDecimal bigDecimal = numBigDecimal.setScale(scale, RoundingMode.HALF_UP);
        return bigDecimal.toString();
    }
}
