package com.liteiot.admin.modules.auth.bean;


import com.liteiot.common.util.jwt.IJWTInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 */
@Data
@AllArgsConstructor
public class ClientInfo implements IJWTInfo {

    /**
     * 客户Id
     */
    String clientId;

    /**
     * 名称
     */
    String name;

    /**
     * 主键
     */
    String id;


    @Override
    public String getUniqueName() {
        return clientId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTokenId() {
        return null;
    }
}
