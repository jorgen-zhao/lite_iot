package com.liteiot.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class:  SystemBindData
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/23 15:33
 * Desc:   SystemBindData
 */

@Data
public class SystemBindData {

    @NotEmpty(message = "设备不能为空" )
    private List<String> imeis;

    @NotNull(message = "接入系统不能为空" )
    private Integer accessSystemId;

    @NotBlank(message = "数据参数不能为空" )
    private String attributeValues;
}
