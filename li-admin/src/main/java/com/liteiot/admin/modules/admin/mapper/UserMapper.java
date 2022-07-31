package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.User;
import com.liteiot.common.vo.GroupUser;
import com.liteiot.common.util.Query;
import com.liteiot.common.vo.UserGroupVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Deprecated
    List<User> selectMemberByGroupId(@Param("groupId") int groupId);

    @Deprecated
    List<User> selectLeaderByGroupId(@Param("groupId") int groupId);

    /**
     * 获取部门名称的用户信息
     *
     * @param userId
     * @return
     */
    GroupUser selectUserWithGroupName(@Param("id") int userId);

    /**
     * 获取该部门下所有的用户总和
     *
     * @param query
     * @return
     */
    int countAllUsersByGroupId(@Param("query") Query query);

    UserGroupVo selectUserGroup(@Param("userId") int userId);
}