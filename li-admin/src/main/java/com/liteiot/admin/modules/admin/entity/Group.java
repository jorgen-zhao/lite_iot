package com.liteiot.admin.modules.admin.entity;

import com.liteiot.common.bmsenum.ServiceType;
import com.liteiot.common.validate.InsertVerify;
import com.liteiot.common.validate.UpdateVerify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Table(name = "base_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "JDBC")
    @NotNull(message = "id不能为空", groups = {UpdateVerify.class})
    private Integer id;

    @NotBlank(message = "编码不能为空", groups = {UpdateVerify.class})
    private String code;

    @Size(min = 2, max = 10, message = "名称长度应在2-10个字符", groups = {InsertVerify.class, UpdateVerify.class})
    @NotBlank(message = "名称不能为空", groups = {UpdateVerify.class})
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    private String path;

    private String description;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user")
    private String updUser;

    @Column(name = "upd_name")
    private String updName;

    @Column(name = "upd_host")
    private String updHost;

    /**
     * 服务档位
     * {@link  ServiceType}
     */
    @Column(name = "service_type")
    @Range(min = ServiceType.FIRST_TYPE, max = ServiceType.THIRD_TYPE, message = "服务类型目前仅支持:基础服务、运维服务、专家服务", groups = {InsertVerify.class, UpdateVerify.class})
    @NotNull(message = "服务类型不能为空", groups = {InsertVerify.class, UpdateVerify.class})
    private Integer serviceType;

    /**
     * 是否可拓展
     * 0 - 不可拓展
     * 1 - 可拓展
     */
    @Column(name = "extendable")
    private Integer extendable;

    public Group(int groupId) {
        this.id = groupId;
    }
}