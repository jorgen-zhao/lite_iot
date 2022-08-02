package com.liteiot.api.vo.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class:  DeviceOperationReport
 * <p>
 * Author: zhaoyg
 * Date:   2021/12/16 15:43
 * Desc:   DeviceOperationReport
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceOperationReport {

    /********************************************************************************************
     起始符	   |      IMEI08     |        v	       |   Cmd	 |   设置参数(可变)	| cmdbcc
     ---------------------------------------------------------------------------------------------
     +B1301RSP | 20211003191001 | SorftwareVer    |  命令类型 |      Para        |    BccNb
     *********************************************************************************************/

    /**
     * 下行命令开头关键字：+B1301RSP,。
     * 下行命令结尾：\r\n ,回车换行。
     * SorftwareVer：软件版本号，长度固定1个字节，例如：11,表示1.1,发布版本和DEBUG版本号都是1。
     * Cmd: 具体的命令类型信息，比如设置休眠时间SLPT等。
     * [,Para]：下发命令中携带的设置参数，长度根据具体的命令类型而定，部分命令没有携带设置参数。
     * BccNb: '+'到最后一个‘，’之间的所有字符的BCC校验值，长度固定1个字节。
     */

    private String head;

    /**
     * 设备号
     */
    private String imei;


    /**
     * 版本号
     */
    private Integer version;

    /**
     * 命令类型
     */
    private String commandType;

    /**
     * 参数
     */
    private String commandParam;

    /**
     * 原始报文
     */
    private String originPacket;
}
