package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Class:  WxTemplate
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/29 14:30
 * Desc:   WxTemplate
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxTemplate {

    /**
     * 公众号ID
     */
    private String mpId;

    /**
     * 接受者Id
     */
    private String openId;

    /**
     * 模板Id
     */
    private String templateId;

    /**
     * 跳转连接
     */
    private String url;

    /**
     * 跳转小程序Id
     */
    private String miniProgramId;

    /**
     * 跳转小程序路径
     */
    private String path;

    /**
     * 模板信息
     */
    private Map<String, String> map;
}
