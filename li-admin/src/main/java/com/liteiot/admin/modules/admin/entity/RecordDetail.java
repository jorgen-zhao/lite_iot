package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 升级记录详情表
 * @version 2021-11-15 14:44:25
 */
@Data
@Table(name = "record_detail")
public class RecordDetail implements Serializable{
private static final long serialVersionUID=1L;

        //主键
    @Id
    private Integer id;
    
        /**
     * 升级记录Id
     */
    @Column(name = "record_id")
    private Integer recordId;
    
        /**
     * 当前包号
     */
    @Column(name = "current_packet_no")
    private Integer currentPacketNo;
    
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
    

}
