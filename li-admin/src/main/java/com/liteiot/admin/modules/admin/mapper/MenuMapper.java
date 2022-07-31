package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {
    List<Menu> selectMenuByAuthorityId(@Param("authorityId") String authorityId, @Param("authorityType") String authorityType);

    /**
     * 根据用户和组的权限关系查找用户可访问菜单
     *
     * @param userId
     * @param groupId
     * @return
     */
    List<Menu> selectAuthorityMenuByUserId(@Param("userId") int userId, @Param("groupId") String groupId);

    /**
     * 根据用户和组的权限关系查找用户可访问的系统
     *
     * @param userId
     * @param groupId
     * @return
     */
    List<Menu> selectAuthoritySystemByUserId(@Param("userId") int userId, @Param("groupId") String groupId);
}