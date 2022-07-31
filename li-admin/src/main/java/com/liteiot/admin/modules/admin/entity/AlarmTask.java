package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.HandleStatus;
import com.liteiot.common.bmsenum.MsgStatus;
import com.liteiot.common.bmsenum.ServiceType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;


/**
 * 报警任务表
 *
 * @version 2022-05-17 15:11:55
 */
@Data
@Table(name = "alarm_task" )
public class AlarmTask implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @NotNull(message = "id不能为空", groups = {UpdateVerify.class})
    private Integer id;


    /**
     * 同一个报警任务标识Id
     */
    @Column(name = "task_mark_id" )
    private Long taskMarkId;

    /**
     * 报警Id
     */
    @Column(name = "alarm_id" )
    private Integer alarmId;


    /**
     * 设备IMEI
     */
    private String imei;


    /**
     * 是否向下传递
     * {@link MsgStatus}
     */
    @Column(name = "msg_status")
    @NotNull(message = "msgStatus不能为空", groups = {UpdateVerify.class})
    private Integer msgStatus;

    /**
     * 类型
     * {@link ServiceType}
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 处理状态
     * {@link HandleStatus}
     */
    @Column(name = "handle_status")
    private Integer handleStatus;


    /**
     * 描述
     */
    @Size(max = 255, message = "desc 长度不能超过 255", groups = {InsertVerify.class, UpdateVerify.class})
    private String remark;


    /**
     * 处理人id
     */
    @Column(name = "handle_user_id" )
    private Integer handleUserId;


    /**
     * 处理人姓名
     */
    private String handleUserName;


    /**
     * 处理人姓名
     */
    @Column(name = "handle_time" )
    private Date handleTime;


}
