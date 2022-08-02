package com.liteiot.api.vo.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  OTAReport
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/15 14:49
 * Desc:   OTAReport
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTAReport {

    /********************************************************************************************
     起始符	   |   IMEI08     |          v	       |   pcnt	 |   psize	| offset  | cmdbcc
     ---------------------------------------------------------------------------------------------
     +ReqNOTAB | 20211003191001 |         11     |    1   |  1024    |    0	 |   14
     *********************************************************************************************/

    /**
     * +ReqNOTAB:请求OTA子包的命令提示符；
     * v: 版本号
     * pcnt:OTA子包的包号,范围1~pnumb;
     * psize:子包大小,16bit；
     * offset:文件当前的偏移位置，即当前终端接收到的所有子包的大小之和。
     * cmdbcc:命令的BCC校验码,取“+”号到命令最后一个“，”号 之间的数据进行校验。
     */

    private String head;

    /**
     * 设备号
     */
    private String imei;


    /**
     * 版本号
     */
    private String version;

    /**
     * 当前包号
     */
    private int currentNo;

    /**
     * 原始报文
     */
    private String originalMsg;

    public OTAReport(String head, String imei, int index, String originalMsg) {
        this.head = head;
        this.imei = imei;
        this.currentNo = index;
        this.originalMsg = originalMsg;
    }

    public OTAReport(String head, String imei, String originalMsg) {
        this.head = head;
        this.imei = imei;
        this.originalMsg = originalMsg;
    }
}
