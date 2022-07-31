package com.liteiot.common.dto;

import lombok.Data;

@Data
public class AccessSystemDto extends PageDto {

    private String name;

    private Integer enabled;
}
