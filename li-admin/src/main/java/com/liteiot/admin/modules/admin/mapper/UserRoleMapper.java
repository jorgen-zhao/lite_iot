package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserRoleMapper extends Mapper<UserRole> {
    void deleteByUserId(@Param("userId") String userId);
}