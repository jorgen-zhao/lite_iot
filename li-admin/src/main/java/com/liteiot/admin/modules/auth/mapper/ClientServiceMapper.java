package com.liteiot.admin.modules.auth.mapper;

import com.liteiot.admin.modules.auth.entity.ClientService;
import tk.mybatis.mapper.common.Mapper;

public interface ClientServiceMapper extends Mapper<ClientService> {
    void deleteByServiceId(int id);
}