package com.liteiot.api.vo.timeout;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Class:  OTATimeoutEvent
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/22 15:59
 * Desc:   OTATimeoutEvent
 */

public class OTATimeoutEvent extends ApplicationEvent {

    @Getter
    private String timeoutType;

    @Getter
    private long timeout;

    @Getter
    private String imei;

    public OTATimeoutEvent(Object source, String timeoutType, long timeout, String imei) {
        super(source);
        this.timeoutType = timeoutType;
        this.timeout = timeout;
        this.imei = imei;
    }
}
