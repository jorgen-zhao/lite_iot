package com.liteiot.common.dto;

import lombok.Data;

@Data
public class PageDto {
    //当前页码
    private Integer page = 1;
    //每页条数
    private Integer limit = 10;

}
