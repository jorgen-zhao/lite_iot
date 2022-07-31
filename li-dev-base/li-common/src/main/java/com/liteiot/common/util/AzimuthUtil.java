package com.liteiot.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Class:  AzimuthUtil
 * <p>
 * Author: zhaoyg
 * Date:   2022/7/18 14:01
 * Desc:   通过角度计算出方位角描述。e.g 北偏东17°
 */

public class AzimuthUtil {

    public static final Map<Integer, String> azimuthMap = new HashMap<>();

    static {
        azimuthMap.put(0, "北");
        azimuthMap.put(1, "东");
        azimuthMap.put(2, "南");
        azimuthMap.put(3, "西");
        azimuthMap.put(4, "北");
    }

    public static String getAzimuth(int angle) {
        int index = angle / 90;
        String key = azimuthMap.get(index);
        int rest = angle % 90;
        if (rest == 0) {
            return "正" + key;
        }
        String otherKey = azimuthMap.get(index + 1);
        int v = rest / 45;
        if (v < 1) {
            return key + "偏" + otherKey + rest + "度";
        } else {
            rest = 90 - rest;
            return otherKey + "偏" + key + rest + "度";
        }
    }
}
