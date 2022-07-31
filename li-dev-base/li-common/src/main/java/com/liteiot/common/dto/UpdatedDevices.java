package com.liteiot.common.dto;

import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class:  UpdatedDevices
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/7 11:29
 * Desc:   UpdatedDevices
 */

@Data
public class UpdatedDevices {

    /**
     * 设备号
     */
    @NotEmpty(message = "设备号不能为空",groups = {UpdateVerify.class})
    private List<String> imeis;

    /**
     * 所属组织
     */
    @NotNull(message = "所属组织不能为空",groups = {UpdateVerify.class})
    private Integer groupId;

    /**
     * 设备类型
     */
    @NotNull(message = "设备类型不能为空",groups = {UpdateVerify.class})
    private Integer deviceType;

    /**
     * 安装物名称
     */
    @Size(max = 20, message = "安装物名称不能超过20个字符",groups = {UpdateVerify.class})
    private String installAddress;
}

