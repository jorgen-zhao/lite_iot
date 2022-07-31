package com.liteiot.admin.configuration;

/**
 * Class:  BaseWxMpConfiguration
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/29 18:02
 * Desc:   BaseWxMpConfiguration
 */

public class BaseWxMpConfiguration {

    /**
     * 微信公众号的模板Id
     */
    private String templateId;

    /**
     * 模板消息跳转的链接
     */
    private String url;

    /**
     * 小程序的appId
     */
    private String miniProgramId;

    /**
     * 小程序跳转的路径
     */
    private String path;

    /**
     * 微信模板消息推送的REST接口
     */
    private String pushUrl;

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniProgramId() {
        return miniProgramId;
    }

    public void setMiniProgramId(String miniProgramId) {
        this.miniProgramId = miniProgramId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
