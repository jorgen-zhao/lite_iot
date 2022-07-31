package com.liteiot.admin.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class:  WxMpStatisticsConfiguration
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/29 18:01
 * Desc:   WxMpStatisticsConfiguration
 */

@Component
@ConfigurationProperties(prefix = "wx.register")
public class WxMpUserSuccessRegisterConfiguration extends BaseWxMpConfiguration {

}
