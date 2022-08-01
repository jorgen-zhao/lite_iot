package com.liteiot.gate.service;

import com.liteiot.api.vo.log.LogInfo;

/**
 * 日志保存接口
 */
public interface LogService {
    void saveLog(LogInfo info);
}
