package com.liteiot.common.vo;

import lombok.Data;

import java.util.List;

/**
 * Class:  PlatformUser
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/30 11:15
 * Desc:   PlatformUser
 */

@Data
public class PlatformUser {

    private Integer userId;

    private String username;

    private String name;

    private String mobilePhone;

    private Integer currentGroupId;

    private String currentGroupName;

    private List<SimpleGroup> groups;
}
