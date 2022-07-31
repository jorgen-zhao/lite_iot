package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ElementMapper extends Mapper<Element> {

    List<Element> selectAuthorityElementByUserId(@Param("userId") String userId, @Param("groupId") String groupId);

    List<Element> selectAuthorityMenuElementByUserIdAndGroupId(@Param("userId") String userId, @Param("menuId") String menuId, @Param("groupId") String groupId);

    List<Element> selectAuthorityElementByClientId(@Param("clientId") String clientId);

    List<Element> selectAllElementPermissions();
}