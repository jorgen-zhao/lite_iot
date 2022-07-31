package com.liteiot.admin.modules.admin.rpc;

import com.liteiot.admin.modules.admin.biz.GateLogBiz;
import com.liteiot.admin.modules.admin.entity.GateLog;
import com.liteiot.api.vo.log.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 保存网关日志
 */
@RequestMapping("/api")
@RestController
@Slf4j
public class LogRest {
    @Autowired
    private GateLogBiz gateLogBiz;

    @RequestMapping(value = "/log/save", method = RequestMethod.POST)
    public void saveLog(@RequestBody LogInfo info) {
        GateLog log = new GateLog();
        BeanUtils.copyProperties(info, log);
        log.setCrtTime(new Date(info.getCrtTime()));
        gateLogBiz.insertSelective(log);
    }
}
