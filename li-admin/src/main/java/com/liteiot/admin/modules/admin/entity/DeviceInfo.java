package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.DeviceStatus;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * 设备信息表
 *
 * @version 2021-09-15 17:30:13
 */
@Data
@Table(name = "device_info")
@ApiModel("设备信息表")
public class DeviceInfo implements Serializable {


    private static final long serialVersionUID = 1L;

    //
    @Id
//    @GeneratedValue(generator = "JDBC")
    @NotNull(message = "id不能为空",groups = {UpdateVerify.class})
    private Integer id;

    /**
     * imei
     */
    @ApiModelProperty("imei")
    @NotBlank(message = "设备号不能为空",groups = {InsertVerify.class,UpdateVerify.class})
    @Column(name = "imei")
    private String imei;

    /**
     * 设备名称
     */
    @Column(name = "device_name")
    @ApiModelProperty("设备名称")
    @NotBlank(message = "设备名称不能为空",groups = {InsertVerify.class,UpdateVerify.class})
    private String deviceName;

    /**
     * 设备类型
     * 0-射灯广告牌
     * 1-单立柱广告牌
     * 2-大型灯箱
     * 3-候车厅广告牌
     * 4-霓虹灯广告牌
     */
    @Column(name = "device_type")
    @ApiModelProperty("设备类型")
    private Integer deviceType;

    /**
     * 批次号
     */
    @Column(name = "batch")
    @ApiModelProperty("批次号")
    @NotBlank(message = "批次号不能为空",groups = {InsertVerify.class,UpdateVerify.class})
    private String batch;

    /**
     * 硬件版本（冗余批次号，便于检索）
     */
    @Column(name = "hard_version")
    @ApiModelProperty("硬件版本（冗余批次号，便于检索）")
    private int hardVersion;


    /**
     * 所属组织
     */
    @Column(name = "group_id")
    @ApiModelProperty("所属组织")
    @NotNull(message = "所属组织不能为空",groups = {InsertVerify.class,UpdateVerify.class})
    private Integer groupId;

    /**
     * 地址
     */
    @Column(name = "address")
    @ApiModelProperty("地址")
    private String address;

    /**
     * 物流状态(0-库存、1-出库)
     */
    @Column(name = "logistics_status")
    @ApiModelProperty("物流状态(0-库存、1-出库)")
    private Integer logisticsStatus;

    /**
     * 安装状态: 0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废
     * 设备新增时默认为0 生产状态
     * {@link DeviceStatus}
     */
    @Column(name = "install_status")
    @ApiModelProperty("安装状态(0-生产 1-库存 2-未激活 3-已激活 4-停用 5-维修 6-报废)")
    private Integer installStatus;

    /**
     * 运行状态(0-正常、1-报警、2-恢复、3-拆除)
     * 与报警的处理结果保持一致 alarmResult
     *
     * @see com.liteiot.admin.modules.admin.entity.Alarm
     */
    @Column(name = "running_status")
    @ApiModelProperty("运行状态(0-正常、1-报警、2-恢复、3-拆除)")
    private Integer runningStatus;

    @Column(name = "sphere_center_x")
    @ApiModelProperty("球心x轴坐标")
    private Double sphereCenterX;

    @Column(name = "sphere_center_y")
    @ApiModelProperty("球心y轴坐标")
    private Double sphereCenterY;

    @Column(name = "sphere_center_z")
    @ApiModelProperty("球心z轴坐标")
    private Double sphereCenterZ;

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
