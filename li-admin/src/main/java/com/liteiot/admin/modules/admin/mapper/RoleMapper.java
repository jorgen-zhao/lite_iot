package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    
//    void deleteUsersById(@Param("roleId") int roleId);

//    void insertUsersById(@Param("roleId") int roleId, @Param("userId") int userId);

    List<Role> findRolesByUserIdAndGroupId(@Param("userId") int userId, @Param("groupId") String groupId);

    Role findRoleByCode(@Param("code") String code);
}