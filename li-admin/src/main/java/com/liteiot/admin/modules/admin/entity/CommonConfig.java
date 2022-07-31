package com.liteiot.admin.modules.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 配置信息表
 *
 * @version 2021-09-22 11:16:27
 */
@Data
@Table(name = "common_config")
@ApiModel("配置信息表")
public class CommonConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    @Id
    private Integer id;

    /**
     * 报警阈值（默认0）
     */
    @Column(name = "threshold")
    @ApiModelProperty("报警阈值（默认0）")
    private Integer threshold;

    /**
     * 推送间隔（默认10s）
     */
    @Column(name = "push_interval")
    @ApiModelProperty("推送间隔（默认10s）")
    private Integer pushInterval;

    /**
     * 跌落满分分数（默认50）
     */
    @Column(name = "fall_standard")
    @ApiModelProperty(" 跌落满分分数（默认50）")
    private Integer fallStandard;

    /**
     * 倾斜满分分数（默认30）
     */
    @Column(name = "lean_standard")
    @ApiModelProperty("倾斜满分分数（默认30）")
    private Integer leanStandard;

    /**
     * 松动满分分数（默认10）
     */
    @Column(name = "loosen_standard")
    @ApiModelProperty("松动满分分数（默认10）")
    private Integer loosenStandard;

    /**
     * 旋转满分分数（默认10）
     */
    @Column(name = "spin_standard")
    @ApiModelProperty("旋转满分分数（默认10）")
    private Integer spinStandard;

    /**
     * 滚动角/倾斜角偏转角度（默认10）
     */
//    @Column(name = "roll_standard")
//    @ApiModelProperty("滚动角/倾斜角偏转角度（默认10）")
//    private Integer rollStandard;
//
//    /**
//     * 俯仰角偏转角度（默认10）
//     */
//    @Column(name = "pitch_standard")
//    @ApiModelProperty("俯仰角偏转角度（默认10）")
//    private Integer pitchStandard;
//
    /**
     * 倾斜角阈值
     */
    @Column(name = "lean_threshold")
    @ApiModelProperty("倾斜角阈值（默认5）")
    private Integer leanThreshold;


    /**
     * 磁偏角偏转角度（默认10）
     */
    @Column(name = "heading_degree_standard")
    @ApiModelProperty("磁偏角偏转角度（默认10）")
    private Integer headingDegreeStandard;

    /**
     * 备注
     */
    @Column(name = "remark")
    @ApiModelProperty("备注")
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
