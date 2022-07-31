package com.liteiot.admin.modules.auth.biz;

import com.liteiot.admin.modules.auth.entity.Client;
import com.liteiot.admin.modules.auth.entity.ClientService;
import com.liteiot.admin.modules.auth.mapper.ClientMapper;
import com.liteiot.admin.modules.auth.mapper.ClientServiceMapper;
import com.liteiot.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 客户基类
 */
@Service
public class ClientBiz extends BaseBiz<ClientMapper, Client> {
    @Autowired
    private ClientServiceMapper clientServiceMapper;
    @Autowired
    private ClientServiceBiz clientServiceBiz;

    public List<Client> getClientServices(int id) {
        return mapper.selectAuthorityServiceInfo(id);
    }

    public void modifyClientServices(int id, String clients) {
        clientServiceMapper.deleteByServiceId(id);
        if (!StringUtils.isEmpty(clients)) {
            String[] mem = clients.split(",");
            for (String m : mem) {
                ClientService clientService = new ClientService();
                clientService.setServiceId(m);
                clientService.setClientId(id + "");
                clientServiceBiz.insertSelective(clientService);
            }
        }
    }
}