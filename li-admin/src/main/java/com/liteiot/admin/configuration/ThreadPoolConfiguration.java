package com.liteiot.admin.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Data
@Component
@Slf4j
public class ThreadPoolConfiguration {


    //    @Value("${server.thread.pool.core:50}")
    private int corePoolSize = 50;

    //    @Value("${server.thread.pool.maxQueue:1500}")
    private int queueCapacity = 1500;

    //    @Value("${server.thread.pool.pool:500}")
    private int maxPoolSize = 500;


    @Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程池参数
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setKeepAliveSeconds(10);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.setTaskDecorator(runnable -> () -> {
            try {
                runnable.run();
                if (runnable instanceof Future) {
                    ((Future) runnable).get();
                }
            } catch (InterruptedException e) {
                log.error("InterruptedException:{}", e.getMessage());
            } catch (ExecutionException e) {
                log.error("ExecutionException:{}", e.getMessage());
            } catch (Exception e) {
                log.error("Exception:{}", e.getMessage());
            }
        });
        taskExecutor.initialize();

        return taskExecutor;
    }

    /**
     * 默认全局的定时器线程池
     */
    @Bean("scheduledExecutorService")
    public ScheduledExecutorService getScheduledExecutorService() {
        return Executors.newScheduledThreadPool(corePoolSize);
    }
}
