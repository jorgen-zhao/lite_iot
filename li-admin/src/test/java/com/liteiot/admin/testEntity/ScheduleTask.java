package com.liteiot.admin.testEntity;

import java.time.LocalDateTime;

/**
 * Class:  ScheduleTask
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/7 16:10
 * Desc:   ScheduleTask
 */

public class ScheduleTask implements Runnable {

    private String name;

    public ScheduleTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " is running... " + Thread.currentThread().getName() + " " + LocalDateTime.now());
    }
}
