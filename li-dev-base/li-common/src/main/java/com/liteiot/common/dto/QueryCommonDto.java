package com.liteiot.common.dto;

import lombok.Data;

/**
 * 查询参数公用实体
 */
@Data
public class QueryCommonDto extends PageDto {

    /**
     * 当前登录人所属组织id
     */
    private Integer groupId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 截止时间
     */
    private String endTime;

}
