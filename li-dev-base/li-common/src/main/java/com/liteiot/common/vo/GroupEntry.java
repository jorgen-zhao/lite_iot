package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  GroupEntry
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/28 10:44
 * Desc:   GroupEntry
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEntry {

    /**
     * 组织Id
     */
    private int groupId;

    /**
     * 组织名称
     */
    private String groupName;
}
