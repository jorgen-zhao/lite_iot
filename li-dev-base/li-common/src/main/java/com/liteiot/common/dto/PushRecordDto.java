package com.liteiot.common.dto;

import lombok.Data;

@Data
public class PushRecordDto extends PageDto {

    private Integer accessSystemId;

    private String startTime;

    private String endTime;

    private Integer pushResult;
}
