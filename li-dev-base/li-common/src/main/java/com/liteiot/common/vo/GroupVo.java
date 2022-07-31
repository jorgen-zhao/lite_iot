package com.liteiot.common.vo;

import com.liteiot.common.bmsenum.ServiceType;
import lombok.Data;

@Data
public class GroupVo {
    private Integer id;
    private String code;
    private String name;
    private Integer parentId;
    private String path;

    /**
     * 服务档位
     * {@link  ServiceType}
     */
    private Integer serviceType;

    /**
     * 是否可拓展
     * 0 - 不可拓展
     * 1 - 可拓展
     */
    private Integer extendable;

}
