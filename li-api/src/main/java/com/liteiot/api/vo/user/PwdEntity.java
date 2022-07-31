package com.liteiot.api.vo.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * Class:  PwdEntity
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/11 10:22
 * Desc:   PwdEntity
 */

@Data
public class PwdEntity {

    @NotBlank(message = "原密码不能为空")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空")
    private String newPwd;
}
