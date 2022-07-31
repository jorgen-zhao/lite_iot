package com.liteiot.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class:  AccessSystemDataDto
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/24 11:04
 * Desc:   AccessSystemDataDto
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class AccessSystemDataDto extends PageDto {

    /**
     * 接入系统名称
     */
    private String accessSystemName;

    /**
     * 设备号
     */
    private String imei;
}
