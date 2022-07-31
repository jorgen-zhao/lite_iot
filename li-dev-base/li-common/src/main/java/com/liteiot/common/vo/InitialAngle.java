package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  InitialAngle
 * <p>
 * Author: zhaoyg
 * Date:   2021/10/11 15:00
 * Desc:   InitialAngle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialAngle {

    /**
     * 设备号
     */
    private String imei;

    private String roll;

    private String pitch;

    private String headingDegree;

    /**
     * 硬件版本, 用于旋转算法中坐标轴转换判断依据
     */
    private String hardVersion;

    /**************   球心坐标    ******************************************************/

    private String sphereCenterX;
    private String sphereCenterY;
    private String sphereCenterZ;

    /**************   磁球向量TIU    ******************************************************/

    private String wx;
    private String wy;
    private String wz;


    /**************   基向量v1    ******************************************************/

    private String basicV1x;
    private String basicV1y;
    private String basicV1z;

    /**************   基向量v2    ******************************************************/

    private String basicV2x;
    private String basicV2y;
    private String basicV2z;

    /**************   初始w1    ******************************************************/

    private String initialWx;
    private String initialWy;
    private String initialWz;

    /**************   初始d    ******************************************************/

    private String initialDx;
    private String initialDy;
    private String initialDz;

    /**
     * 垂直与地面的轴
     */
    private String verticalAxis;
}
