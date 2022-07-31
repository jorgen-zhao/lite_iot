package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.GroupUserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 组织用户角色关系表
 *
 * @version 2021-12-23 16:14:38
 */
public interface GroupUserRoleMapper extends Mapper<GroupUserRole> {

    void deleteByGroupIdAndUserId(String userId, String groupId);

    int countDistinctByUserId();

    List<GroupUserRole> findNonGroupUserRole();
}
