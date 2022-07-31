package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.AlarmStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


/**
 * 警报表
 *
 * @version 2021-09-17 09:53:25
 */
@Data
@Table(name = "alarm")
@ApiModel("警报表")
public class Alarm implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * imei
     */
    @Column(name = "imei")
    @ApiModelProperty("imei")
    private String imei;

    /**
     * 报警处理状态（0-未处理、1-处理中、2-已处理）
     * {@link com.liteiot.common.bmsenum.AlarmStatus}
     */
    @Column(name = "alarm_status")
    @ApiModelProperty("报警处理状态（0-未处理、1-处理中、2-已处理）")
    private int alarmStatus;

    /**
     * 处理结果/设备状态（0-正常、1-报警、2-修复、3-拆除）
     * 与设备的运行状态保持一致 runningStatus
     *
     * @see com.liteiot.admin.modules.admin.entity.DeviceInfo
     */
    @Column(name = "alarm_result")
    @ApiModelProperty("处理结果/设备状态（0-正常、1-报警、2-修复、3-拆除）")
    private int alarmResult;

    /**
     * 负责人
     */
    @Column(name = "charge_man")
    @ApiModelProperty("负责人")
    private String chargeMan;

    /**
     * 电话号码
     */
    @Column(name = "charge_phone")
    @ApiModelProperty("负责人电话")
    private String chargePhone;

    /**
     * 预计完成时间
     */
    @Column(name = "predict_complete_time")
    @ApiModelProperty("预计完成时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date predictCompleteTime;

    /**
     * 完成时间
     */
    @Column(name = "real_complete_time")
    @ApiModelProperty("完成时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date realCompleteTime;

    /**
     * 危险评分
     */
    @Column(name = "danger_score")
    @ApiModelProperty("危险评分")
    private Integer dangerScore;

    /**
     * 处理耗时（分钟）
     */
    @Column(name = "duration")
    @ApiModelProperty("处理耗时（分钟）")
    private Integer duration;

    /**
     * 跌落
     */
    @Column(name = "fall_index")
    @ApiModelProperty("跌落")
    private Boolean fallIndex;

    /**
     * 摇摆
     */
    @Column(name = "swing_index")
    @ApiModelProperty("松动")
    private Boolean swingIndex;

    /**
     * 倾斜
     */
    @Column(name = "lean_index")
    @ApiModelProperty("倾斜")
    private Double leanIndex;

    /**
     * 旋转
     */
    @Column(name = "spin_index")
    @ApiModelProperty("旋转")
    private Double spinIndex;

    /**
     * 报警现象描述
     */
    @Column(name = "alarm_desc")
    private String alarmDesc;

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

    /**
     * 是否需要持久化标识
     */
    @Transient
    private boolean isAlarm;

    /**** 冗余字段, 方便后期同期 *************************************************************************************************************/

    /**
     * 年 格式: yyyy
     */
    private String year;

    /**
     * 月 格式: yyyy-MM
     */
    private String month;

    /**
     * 日 格式: yyyy-MM-dd
     */
    private String day;

    /**
     * 时间 格式: yyyy-MM-dd HH:mm
     */
    private String time;

    /**
     * 冗余字段:
     * 1 ~ 29  低级 1
     * 30 ~ 59 中级 2
     * 60 ~ 100 危险 3
     */
    private int dangerLevel;

    public Alarm(String imei, boolean fallIndex, boolean swingIndex, double leanIndex, double spinIndex, String alarmDesc, int dangerScore, boolean isAlarm) {
        this.imei = imei;
        this.fallIndex = fallIndex;
        this.leanIndex = leanIndex;
        this.swingIndex = swingIndex;
        this.spinIndex = spinIndex;
        this.alarmDesc = alarmDesc;
        this.dangerScore = dangerScore;
        this.isAlarm = isAlarm;
        // 所有新建的报警都为未处理、状态为报警
        this.alarmStatus = AlarmStatus.WAIT_HANDLE;
        this.alarmResult = 1;
        this.crtTime = new Date();
        this.updTime = new Date();
        this.dangerLevel = switchDangerLevelFromScore(dangerScore);

        this.generateTime();
    }

    public Alarm() {
        this.generateTime();
    }

    private void generateTime() {
        // 补充冗余字段，后期统计
        LocalDateTime now = LocalDateTime.now();
        this.year = String.valueOf(now.getYear());
        this.month = now.format(DateTimeFormatter.ofPattern("yyyy-MM", Locale.CHINA));
        this.day = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA));
        this.time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.CHINA));
    }
    /**
     * 根据危险评分转化危险等级
     * 1 ~ 20  低级 1
     * 21 ~ 55 中级 2
     * 55 ~ 100 危险 3
     *
     * @param dangerScore 危险评分
     * @return
     */
    private int switchDangerLevelFromScore(int dangerScore) {
        if (dangerScore >= 0 && dangerScore < 20) {
            return 1;
        } else if (dangerScore >= 21 && dangerScore < 55) {
            return 2;
        } else {
            return 3;
        }
    }
}
