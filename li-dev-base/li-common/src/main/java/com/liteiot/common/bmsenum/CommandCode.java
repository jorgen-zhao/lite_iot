package com.liteiot.common.bmsenum;

/**
 * Class:  CommandCode
 * <p>
 * Author: zhaoyg
 * Date:   2022/6/15 16:09
 * Desc:   CommandCode -- 参考数据库 device_command
 */
public interface CommandCode {


    /**
     * 系统复位
     */
    String SYSRST = "SYSRST";

    /**
     * 定位更新
     */
    String UPDLOC = "UPDLOC";

    /**
     * 终端入库
     */
    String STOCK = "STOCK";

    /**
     * 查询休眠时间
     */
    String CHKSLPT = "CHKSLPT";

    /**
     * 查询硬件版本号
     */
    String CHKSERIAL = "CHKSERIAL";

    /**
     * 查询GSensor斜率运动检测参数
     */
    String CHKSLOPPARAM = "CHKSLOPPARAM";

    /**
     * 查询GSensor自由落体检测参数
     */
    String CHKFFALLPARAM = "CHKFFALLPARAM";

    /**
     * 查摇摆模式下倾斜事件参考值
     */
    String CHKSWTREFVAL = "CHKSWTREFVAL";

    /**
     * 查询摇摆运动检测的摇摆半周期
     */
    String CHKHSWCYCLE = "CHKHSWCYCLE";

    /**
     * 查询ICCID
     */
    String CHKICCID = "CHKICCID";

    /**
     * 摇摆次数上报阈值
     */
    String CHKSWINGCNTTHRE = "CHKSWINGCNTTHRE";

    /**
     * 设备位置定时更新间隔
     */
    String CHKLOCTMUDPITV = "CHKLOCTMUDPITV";

    /**
     * 查询定位漂移过滤功能开关状态
     */
    String CHKLDFCLOSE = "CHKLDFCLOSE";

    /**
     * 查询BDGID
     */
    String CHKBDGID = "CHKBDGID";

    /**
     * 查询TTD参数
     */
    String CHKTTDPARAM = "CHKTTDPARAM";

    /**
     * 查询ROTD参数
     */
    String CHKROTDPARAM = "CHKROTDPARAM";

    /**
     * 查询上报固定定位功能状态
     */
    String CHKBDSTOPUPDSTA = "CHKBDSTOPUPDSTA";

    /**
     * 查询生产日志输出关闭状态
     */
    String CHKPRLOGPRINSTA = "CHKPRLOGPRINSTA";

    /**
     * 位移识别上报阈值
     */
    String CHKDSPLREPTHRE = "CHKDSPLREPTHRE";

    /**
     * 设备启用
     */
    String OPERASTA = "OPERASTA";

    /**
     * 上报间隔
     */
    String SLPT = "SLPT";

    /**
     * 设备批次号
     */
    String SERIALNB = "SERIALNB";

    /**
     * 设置软件版本
     */
    String VERCHGE = "VERCHGE";

    /**
     * 任意运动参数
     */
    String SLOPPARAM = "SLOPPARAM";

    /**
     * 跌落参数
     */
    String FFALLPARAM = "FFALLPARAM";

    /**
     * 摇摆半周期设置
     */
    String HSWCYCLE = "HSWCYCLE";

    /**
     * 摇摆次数上报阈值
     */
    String SWINGCNTTHRE = "SWINGCNTTHRE";

    /**
     * 选择域名类型
     */
    String SETDNTYPE = "SETDNTYPE";

    /**
     * 设备定位持续更新
     */
    String SETUPDLOCCNTU = "SETUPDLOCCNTU";

    /**
     * 设备位置定时更新间隔
     */
    String LOCTMUDPITV = "LOCTMUDPITV";

    /**
     * 设备定位漂移过滤开启或关闭
     */
    String LDFCLOSE = "LDFCLOSE";

    /**
     * 倾斜定时检测参数
     */
    String TTDPARAM = "TTDPARAM";

    /**
     * XZ瞬时检测
     */
    String ROTDPARAM = "ROTDPARAM";

    /**
     * 固定定位上报功能开关状态
     */
    String BDSTOPUPDSTA = "BDSTOPUPDSTA";

    /**
     * 生产日志打印关闭状态
     */
    String PRLOGPRINSTA = "PRLOGPRINSTA";

    /**
     * 位移识别上报阈值
     */
    String DSPLREPTHREMM = "DSPLREPTHREMM";

    /**
     * 演示数据上报
     */
    String DDEREPOPEN = "DDEREPOPEN";


    /**
     * 设置温漂参数指令
     */
    String TDCPARAM = "TDCPARAM";

    /**
     * 设备频繁上报指令
     */
    String SENDQKLFUNSTA = "SENDQKLFUNSTA";
}
