package com.liteiot.admin.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class:  WxInitConfig
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/10 10:35
 * Desc:   WxInitConfig
 */

@Component
@ConfigurationProperties(prefix = "wx.task")
public class WxMpTaskConfiguration extends BaseWxMpConfiguration {


}
