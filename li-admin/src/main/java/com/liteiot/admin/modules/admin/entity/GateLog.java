package com.liteiot.admin.modules.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "gate_log")
@Data
public class GateLog {
    @Id
    private Integer id;

    private String menu;

    private String opt;

    private String uri;

    private String body;

    @Column(name = "crt_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "request_uri")
    private String requestUri;

    @Column(name = "token_id")
    private String tokenId;

}