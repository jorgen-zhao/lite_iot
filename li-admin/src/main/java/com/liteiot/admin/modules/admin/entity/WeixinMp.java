package com.liteiot.admin.modules.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 微信公众号表
 *
 * @version 2022-05-11 17:09:03
 */
@Data
@Table(name = "weixin_mp")
@NoArgsConstructor
@AllArgsConstructor
public class WeixinMp implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 公众号名称
     */
    @Column(name = "mp_name")
    private String mpName;

    /**
     * 所属组织
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 该公众号的微信号
     */
    @Column(name = "mp_id")
    private String mpId;

    /**
     * 开发者Id
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 开发者密码
     */
    @Column(name = "app_secret")
    private String appSecret;

    /**
     * 令牌
     */
    @Column(name = "token")
    private String token;

    /**
     * 加密秘钥
     */
    @Column(name = "aes_key")
    private String aesKey;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    /**
     * 创建人
     */
    @Column(name = "crt_user")
    private String crtUser;

    /**
     * 创建人姓名
     */
    @Column(name = "crt_name")
    private String crtName;

    /**
     * 创建主机
     */
    @Column(name = "crt_host")
    private String crtHost;

    /**
     * 更新时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 更新人
     */
    @Column(name = "upd_user")
    private String updUser;

    /**
     * 更新姓名
     */
    @Column(name = "upd_name")
    private String updName;

    /**
     * 更新主机
     */
    @Column(name = "upd_host")
    private String updHost;

    public WeixinMp(String mpId) {
        this.mpId = mpId;
    }
}
