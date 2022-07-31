package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class:  SimpleUser
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/28 18:17
 * Desc:   SimpleUser
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGroup {

    private String groupId;

    private String groupName;

    private List<SimpleRole> roles;
}
