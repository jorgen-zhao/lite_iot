package com.liteiot.admin.testEntity;

import java.util.TimerTask;

/**
 * Class:  ScheduleTimerTask
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/7 16:55
 * Desc:   ScheduleTimerTask
 */

public class ScheduleTimerTask extends TimerTask {

    private String name;

    public ScheduleTimerTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "is running......" );
    }


}
