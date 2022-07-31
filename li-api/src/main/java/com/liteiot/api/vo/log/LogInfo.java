package com.liteiot.api.vo.log;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
@Data
@NoArgsConstructor
public class LogInfo implements Serializable {

    /**
     * 菜单名称
     */
    @JSONField(name = "菜单名称")
    private String menu;

    /**
     * 操作
     */
    @JSONField(name = "操作")
    private String opt;

    /**
     * 资源
     */
    @JSONField(name = "资源")
    private String uri;

    /**
     * 创建时间
     */
    @JSONField(name = "创建时间")
    private Long crtTime;

    /**
     * 创建人
     */
    @JSONField(name = "创建人")
    private String crtUser;

    /**
     * 创建人名称
     */
    @JSONField(name = "创建人名称")
    private String crtName;

    /**
     * 创建人主机
     */
    @JSONField(name = "创建人主机")
    private String crtHost;

    /**
     * 请求参数
     */
    @JSONField(name = "请求参数")
    private String body;

    /**
     * 请求路径
     */
    @JSONField(name = "请求路径")
    private String requestUri;

    /**
     * 会话编号
     */
    @JSONField(name = "会话编号")
    private String tokenId;

    public LogInfo(String menu, String option, String uri,  Date crtTime, String crtUser, String crtName, String crtHost,String body,String requestUri,String tokenId) {
        this.menu = menu;
        this.opt = option;
        this.uri = uri;
        this.crtTime = crtTime.getTime();
        this.crtUser = crtUser;
        this.crtName = crtName;
        this.crtHost = crtHost;
        this.body = body;
        this.requestUri = requestUri;
        this.tokenId = tokenId;
    }
}
