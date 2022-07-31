package com.liteiot.admin.modules.auth.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "auth_client_service")
@Data
public class ClientService {
    @Id
    private Integer id;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "client_id")
    private String clientId;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;
}