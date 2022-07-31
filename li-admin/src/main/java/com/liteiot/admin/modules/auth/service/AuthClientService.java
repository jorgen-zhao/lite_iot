package com.liteiot.admin.modules.auth.service;


import java.util.List;

/**
 * 客户服务
 */
public interface AuthClientService {

    /**
     * 获取授权的客户端列表
     *
     * @param serviceId
     * @param secret
     * @return
     */
    List<String> getAllowedClient(String serviceId, String secret);

    /**
     * 获取服务授权的客户端列表
     *
     * @param serviceId
     * @return
     */
    List<String> getAllowedClient(String serviceId);

    void registryClient();

    void validate(String clientId, String secret) throws Exception;
}
