package com.liteiot.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 设备警报
 */
@Data
@NoArgsConstructor
@ApiModel("设备警报")
public class AlarmDevice {

    private Integer id;

    /**
     * imei
     */
    @ApiModelProperty("imei")
    private String imei;

    /**
     * 设备名称
     */
    @ApiModelProperty("设备名称")
    private String deviceName;

    /**
     * 设备类型
     */
    @ApiModelProperty("设备类型")
    private Integer deviceType;

    /**
     * 批次号
     */
    @ApiModelProperty("批次号")
    private String batch;

    /**
     * 所属组织
     */
    @ApiModelProperty("所属组织")
    private Integer groupId;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 运行状态
     */
    @ApiModelProperty("运行状态")
    private Integer runningStatus;

    /**
     * 报警处理状态（0-未处理、1-处理中、2-已处理）
     */
    @ApiModelProperty("报警处理状态（0-未处理、1-处理中、2-已处理）")
    private int alarmStatus;

    /**
     * 处理结果/设备状态（0-正常、1-报警、2-修复、3-拆除）
     */
    @ApiModelProperty("处理结果/设备状态（0-正常、1-报警、2-修复、3-拆除）")
    private int alarmResult;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    private String chargeMan;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String chargePhone;

    /**
     * 预计完成时间
     */
    @ApiModelProperty("预计完成时间")
    private Date predictCompleteTime;

    /**
     * 完成时间
     */
    @ApiModelProperty("完成时间")
    private Date realCompleteTime;

    /**
     * 危险评分
     */
    @ApiModelProperty("危险评分")
    private Integer dangerScore;

    /**
     * 处理耗时（分钟）
     */
    @ApiModelProperty("处理耗时（分钟）")
    private Integer duration;

    /**
     * 报警描述
     */
    private String alarmDesc;

    /**
     * 跌落
     */
    @ApiModelProperty("跌落")
    private boolean fallIndex;

    /**
     * 倾斜
     */
    @ApiModelProperty("倾斜")
    private Double leanIndex;

    /**
     * 旋转
     */
    @ApiModelProperty("旋转")
    private Double spinIndex;

    /**
     * 摇摆
     */
    @ApiModelProperty("摇摆")
    private Boolean swingIndex;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 创建时间
     */
    private Date crtTime;

    /**
     * 创建人
     */
    private String crtUser;

    /**
     * 创建人姓名
     */
    private String crtName;

    /**
     * 创建主机
     */
    private String crtHost;

    /**
     * 更新时间
     */
    private Date updTime;

    /**
     * 更新人
     */
    private String updUser;

    /**
     * 更新姓名
     */
    private String updName;

    /**
     * 更新主机
     */
    private String updHost;
}
