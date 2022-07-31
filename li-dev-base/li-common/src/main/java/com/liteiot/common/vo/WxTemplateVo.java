package com.liteiot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Class:  WxTemplateVo
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/9 17:53
 * Desc:   WxTemplateVo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxTemplateVo {

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
