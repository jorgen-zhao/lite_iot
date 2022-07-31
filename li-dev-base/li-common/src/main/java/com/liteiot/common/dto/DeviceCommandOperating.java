package com.liteiot.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Class:  DeviceCommandOperating
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/15 15:32
 * Desc:   DeviceCommandOperating
 */

@Data
public class DeviceCommandOperating {

    @NotEmpty(message = "设备号不能为空")
    private List<String> imeis;

    @NotBlank(message = "指令编码不能为空")
    private String commandCode;
}
