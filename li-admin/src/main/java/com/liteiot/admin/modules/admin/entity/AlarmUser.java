package com.liteiot.admin.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 报警人员处理表
 *
 * @version 2021-12-29 09:38:08
 */
@Data
@Table(name = "alarm_user")
public class AlarmUser implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    private Integer id;

    /**
     * 组织列表id
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 报警处理人员
     */
    @Column(name = "alarm_user_id")
    private Integer alarmUserId;


}
