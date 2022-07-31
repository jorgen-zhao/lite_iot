package com.liteiot.common.util;


import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @Desc: 查询参数采用map这种key-value的形式传递。不直接体现参数。考虑使用泛型（推荐） / 或者继承的方式改造
 */
@Data
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        //分页参数
        if (params.get("page") != null) {
            this.page = Integer.parseInt(params.get("page").toString());
        }
        if (params.get("limit") != null) {
            this.limit = Integer.parseInt(params.get("limit").toString());
        }
        // 注：该对象主要用转换对象参数的；分页对象已经存在了，所以需要移除，防止遍历参数时，造成影响
        this.remove("page");
        this.remove("limit");
    }
}
