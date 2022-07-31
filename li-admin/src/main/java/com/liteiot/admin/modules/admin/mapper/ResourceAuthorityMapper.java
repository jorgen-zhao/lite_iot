package com.liteiot.admin.modules.admin.mapper;

import com.liteiot.admin.modules.admin.entity.ResourceAuthority;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ResourceAuthorityMapper extends Mapper<ResourceAuthority> {
    void deleteByAuthorityIdAndResourceType(@Param("authorityId") String authorityId, @Param("resourceType") String resourceType);
}