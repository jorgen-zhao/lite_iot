package com.liteiot.common.dto;

import com.liteiot.common.constant.RegexpConstant;
import com.liteiot.common.validate.UpdateVerify;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Class:  UpdatedAlarm
 * <p>
 * Author: zhaoyg
 * Date:   2022/4/1 17:56
 * Desc:   UpdatedAlarm
 */

@Data
public class UpdatedAlarm {

    /**
     * 报警Ids：兼容多个报警更新
     */
    @NotEmpty(message = "报警id不能为空", groups = {UpdateVerify.class})
    private List<Integer> alarmIds;

    /**
     * 处理人
     */
    @NotEmpty(message = "请输入处理人", groups = {UpdateVerify.class})
    private String chargeMan;

    /**
     * 处理人电话
     */
    @Pattern(regexp = RegexpConstant.PHONE_REQUIRED, message = "手机号格式错误", groups = {UpdateVerify.class})
    private String chargePhone;

    /**
     * 处理结果
     */
    @NotNull(message = "请输入处理人", groups = {UpdateVerify.class})
    private Integer alarmResult;

    /**
     * 备注
     */
    private String remark;
}
