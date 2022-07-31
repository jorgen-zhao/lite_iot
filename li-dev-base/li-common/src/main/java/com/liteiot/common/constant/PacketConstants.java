package com.liteiot.common.constant;

/**
 * Class:  PacketConstants
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/22 14:53
 * Desc:   报文常量
 */

public class PacketConstants {

    /**
     * 前缀
     */
    public static final String PREFIX = "68";

    /**
     * 后缀
     */
    public static final String SUFFIX = "7E7E";

    /**
     * 下发OTA升级指令报文头
     */
    public static final String CPOTA = "CPOTA";

    /**
     * +ReqCOTAP: 子包请求上行命令
     * +COTAPERR:
     * 1. OTACMDERR: 升级命令校验错误
     * 2. PATCHERR: 子包错误
     * +COTAOVER: 子包接受完成通知
     */
    public static final String REQCOTAP = "+ReqCOTAP";

    public static final String COTAPERR = "+COTAPERR";

    /**
     * OTA升级包之间标识符
     */
    public static final String REQCOTAGAP = "ReqCOTAGap";

    /**
     * 升级命令校验错误
     */
    public static final String OTACMDERR = "OTACMDERR";

    /**
     * 子包错误
     */
    public static final String PATCHERR = "PATCHERR";

    /**
     * 子包接受完成通知
     */
    public static final String COTAPOVER = "+COTAPOVER";


    /**
     * 设备状态指令常量
     * 激活状态： ACTIVATED  -- 变量名称定义为 ACTIVATED 方便阅读, 实际下发的报文 OPERASTA
     */
    public static final String ACTIVATED = "OPERASTA";

    /**
     * 设备状态指令常量
     * 库存状态： STOCK
     */
//    public static final String STOCK = "STOCK";

    /**
     * 设备更新位置指令常量
     */
//    public static final String UPDLOC = "UPDLOC";

    /**
     * 设备休眠时间设置指令常量
     * 设备打卡时间间隔设置指令常量
     */
//    public static final String SLPT = "SLPT";

    /**
     * 查询设备休眠时间间隔指令常量
     * 查询设备打卡时间间隔指令常量
     */
//    public static final String CHKSLPT = "CHKSLPT";

    /**
     * 可扩展演示数据上报指令常量
     */
//    public static final String DDEREPOPEN = "DDEREPOPEN";

    /**
     * 设备持续更新位置指令常量
     */
//    public static final String SETUPDLOCCNTU = "SETUPDLOCCNTU";

    /**
     * 设备下发指令头常量
     */
    public static final String DEVICE_COMMAND_HEAD = "BDG01";

    /**
     * 设备上报指令头常量
     */
    public static final String DEVICE_RESP_HEAD = "+BDG01RSP";


    public static final String REPORT_PACKET = "REPORT_PACKET";
    public static final String COMMAND_RESP = "COMMAND_RESP";
    public static final String IOT_REQUEST = "IOT_REQUEST";

    public static final String KAFKA_CONSUMER_GROUP_ID = "KAFKA_CONSUMER_GROUP_ID";
}
