package com.liteiot.admin.modules.admin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 设备操作指令记录表
 *
 * @version 2021-12-16 16:25:26
 */
@Data
@NoArgsConstructor
@Table(name = "operation_record")
public class OperationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 设备号
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 报文头
     */
    @Column(name = "head")
    private String head;

    /**
     * 版本
     */
    @Column(name = "version")
    private Integer version;

    /**
     * 指令类型
     */
    @Column(name = "command_type")
    private String commandType;

    /**
     * 指令参数
     */
    @Column(name = "command_param")
    private String commandParam;

    /**
     * 完整报文
     */
    @Column(name = "origin_packet")
    private String originPacket;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    public OperationRecord(String imei, String head, Integer version, String commandType, String commandParam, String originPacket) {
        this.imei = imei;
        this.head = head;
        this.version = version;
        this.commandType = commandType;
        this.commandParam = commandParam;
        this.originPacket = originPacket;
        this.crtTime = new Date();
    }
}
