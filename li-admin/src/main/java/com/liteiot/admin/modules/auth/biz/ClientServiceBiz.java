package com.liteiot.admin.modules.auth.biz;

import com.liteiot.admin.modules.auth.entity.ClientService;
import com.liteiot.admin.modules.auth.mapper.ClientServiceMapper;
import com.liteiot.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * 客户服务基类
 */
@Service
public class ClientServiceBiz extends BaseBiz<ClientServiceMapper, ClientService> {
}
