package com.liteiot.admin.modules.auth.controller;

import com.liteiot.admin.modules.auth.biz.ClientBiz;
import com.liteiot.admin.modules.auth.entity.Client;
import com.liteiot.common.msg.ObjectRestResponse;
import com.liteiot.common.rest.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * client  service控制类
 */
@RestController
@RequestMapping("service")
public class ServiceController extends BaseController<ClientBiz, Client> {

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    public ObjectRestResponse modifyUsers(@PathVariable int id, String clients) {
        baseBiz.modifyClientServices(id, clients);
        return new ObjectRestResponse();
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    public ObjectRestResponse<List<Client>> getUsers(@PathVariable int id) {
        return new ObjectRestResponse<List<Client>>().data(baseBiz.getClientServices(id));
    }
}
