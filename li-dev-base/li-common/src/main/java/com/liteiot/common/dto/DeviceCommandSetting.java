package com.liteiot.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Class:  DeviceCommandSetting
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/15 15:23
 * Desc:   DeviceCommandSetting
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class DeviceCommandSetting extends DeviceCommandOperating {

    @NotBlank(message = "指令参数不能为空")
    private String commandParam;
}
