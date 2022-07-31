package com.liteiot.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class:  PacketChart
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/8 11:13
 * Desc:   PacketChart
 */

@Data
public class PacketChartReq {

    @NotBlank(message = "imei不能为空")
    private String imei;

    @NotNull(message = "开始时间不能为空")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @NotNull(message = "结束时间不能为空")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
