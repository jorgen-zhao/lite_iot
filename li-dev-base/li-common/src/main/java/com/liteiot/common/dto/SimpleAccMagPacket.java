package com.liteiot.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * Class:  SimpleAccMagPacket
 * <p>
 * Author: zhaoyg
 * Date:   2022/7/12 9:34
 * Desc:   SimpleAccMagPacket
 */
@Data
public class SimpleAccMagPacket {

    /**
     * 设备号
     */
    private String imei;

    /**
     * 重力加速度ax
     */
    private Integer ax;

    /**
     * 重力加速度数据ay
     */
    private Integer ay;

    /**
     * 重力加速度数据az
     */
    private Integer az;

    /**
     * 磁力计数据mx
     */
    private Integer mx;

    /**
     * 磁力计数据my
     */
    private Integer my;

    /**
     * 磁力计数据mz
     */
    private Integer mz;
}
