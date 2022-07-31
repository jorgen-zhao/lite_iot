package com.liteiot.admin;

import com.liteiot.admin.modules.admin.biz.DeviceInfoBiz;
import com.liteiot.admin.modules.admin.biz.SystemConfigBiz;
import com.liteiot.admin.modules.admin.biz.UserBiz;
import com.liteiot.common.dto.TokenResp;
import com.liteiot.admin.modules.admin.entity.SystemConfig;
import com.liteiot.admin.modules.admin.handler.BatchOperationHandler;
import com.liteiot.admin.modules.admin.handler.MonitorHandler;
import com.liteiot.admin.modules.admin.handler.SystemCountHandler;
import com.liteiot.admin.modules.admin.schedule.ScheduleTaskManager;
import com.liteiot.admin.modules.admin.websocket.WebSocketServerLifecycle;
import com.liteiot.common.redis.DeviceCommandCache;
import com.liteiot.common.vo.command.DeviceCommandVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * Class:  StartupRunner
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/22 16:27
 * Desc:   StartupRunner
 */

@Component
@Slf4j
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private DeviceCommandCache deviceCommandCache;

    @Autowired
    private DeviceInfoBiz deviceInfoBiz;

    @Autowired
    private BatchOperationHandler batchOperationHandler;

    @Autowired
    private SystemConfigBiz systemConfigBiz;

    @Autowired
    private ScheduleTaskManager scheduleTaskManager;

    @Autowired
    private SystemCountHandler systemCountHandler;

    @Autowired
    private WebSocketServerLifecycle webSocketServerLifecycle;

    @Autowired
    private MonitorHandler monitorHandler;

    @Override
    public void run(String... args) throws Exception {

        // 初始化配置项
        initDefaultUserAndRole();
        initUserGroupIds();
        initDeviceCommand();
        initDeviceImportTemplate();
        initTimeoutHandler();
        initSystemMsgCount();
        webSocketServerLifecycle.startUp();
        initAccessToken();
        log.info("系统初始化完成");
    }

    /**
     * 初始化系统接受设备消息数
     */
    private void initSystemMsgCount() {
        boolean whetherCached = deviceCommandCache.cachedSystemReceiveMsgCount();
        if (!whetherCached) {
            long count = systemCountHandler.countSystemReceiveMsg();
            log.info("系统接受设备消息数: {}", count);
            deviceCommandCache.initSystemReceiveMsg(count);
        }
    }

    /**
     * 初始化超时处理
     */
    private void initTimeoutHandler() {
        Example example = new Example(SystemConfig.class);
        example.orderBy("crtTime").desc();
        List<SystemConfig> configs = systemConfigBiz.selectByExample(example);
        if (CollectionUtils.isEmpty(configs)) {
            log.info("系统配置信息不存在，请先配置系统配置信息");
        }
        SystemConfig config = configs.get(0);
        scheduleTaskManager.start(config.getScheduleTime());
        log.info("系统超时处理初始化完成。执行周期: {}小时", config.getScheduleTime());
    }

    /**
     * 默认系统启动时重新生成一下导入模板
     */
    private void initDeviceImportTemplate() {
        batchOperationHandler.generateDeviceImportTemplate();
    }

    /**
     * 缓存设备指令
     *
     * @Desc: 在系统启动时就缓存设备指令，之后新增的设备再进行增加到缓存中即可
     * 原来设计在设备上报的时候缓存，如果设备不上报的情况下，获取不到。
     */
    private void initDeviceCommand() {
        List<String> imeis = deviceInfoBiz.getImeis();
        for (String imei : imeis) {
            // 如果存在，则不再进行添加
            DeviceCommandVO cachedDeviceCommand = deviceCommandCache.getCachedDeviceCommand(imei);
            if (Objects.isNull(cachedDeviceCommand) || StringUtils.isBlank(cachedDeviceCommand.getImei())) {
                deviceCommandCache.cacheDeviceCommand(new DeviceCommandVO(imei));
            }
        }
    }

    /**
     * 初始化系统管理员人员与角色
     */
    private void initDefaultUserAndRole() {
        // TODO
    }

    /**
     * 启动时就开始加载
     * 初始化角色-groupIds
     */
    private void initUserGroupIds() {
        userBiz.initUserGroupIds();
    }

    /**
     * 初始化调用萤石云接口的token
     */
    private void initAccessToken() {
        monitorHandler.initAccessTokenConfig();
        TokenResp.Data token = null;
        try {
            token = monitorHandler.refreshToken();
        } catch (URISyntaxException e) {
            log.info("获取accessToken失败", e);
            e.printStackTrace();
        }
        if (token == null) {
            log.info("获取accessToken失败");
        }
    }
}
