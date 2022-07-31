package com.liteiot.admin.configuration;

import com.liteiot.admin.modules.auth.interceptor.UserAuthRestInterceptor;
import com.liteiot.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * web config
 *
 * @Desc: web配置：配置拦截器以及需要拦截的路径以及不需要拦截的路径
 */
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(getUserAuthRestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(getExcludePathPatterns());
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }


    /**
     * 需要用户和服务认证判断的路径
     *
     * @return
     */
    @Deprecated
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/element/**",
                "/gateLog/**",
                "/group/**",
                "/role/**",
                "/menu/**",
                "/user/**",
                "/deviceInfo/**",
                "/upgrader/**",
                "/upgradeRecord/**",
                "/upgradeTask/**",
                "/batch/**",
                "/packetInfo/**",
                "/alarmThreshold/**",
                "/dashboard/**",
                "/alarm/**",
                "/api/permissions",
                "/api/user/un/**",
                "/miniGroup/**",
                "/miniProgram/**",
                "/groupAlarm/**",
                "/platformUser/**",
                "/deviceExtraConfig/**",
                "/batchOperation/**",
                "/systemConfig/**",
                "/platformVersion/**",
                "/bi/**",
                "/monitor/**",
                "locationRectify/**",
        };
        Collections.addAll(list, urls);
        return list;
    }


    /**
     * 不需要用户和服务认证判断的路径
     *
     * @return
     */
    private ArrayList<String> getExcludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/miniProgram/user/register",
                "/miniProgram/user/wxRegister",
                "/miniProgram/user/bindNotify",
                "/constantsConfig/getTotalDeviceTypes",
                "/jwt/token",
                "/client/userPubKey",
                "/captcha",
                "/api/packetInfo/**",
                "/api/log/save",
                "/jwt/miniLogin",
                "/openapi/getDeviceInfos"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
