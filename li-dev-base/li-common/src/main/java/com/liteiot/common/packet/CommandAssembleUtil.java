package com.liteiot.common.packet;

import com.liteiot.common.bmsenum.CommandCode;
import com.liteiot.common.constant.PacketConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * Class:  CommandAssembleUtil
 * <p>
 * Author: zhaoyg
 * Date:   2021/9/9 9:46
 * Desc:   指令下发组装工具
 */

public class CommandAssembleUtil {

    /**
     * 组装下发OTA升级指令报文
     *
     * @param originFileName
     * @param fileSize
     * @param bccCheck
     * @param md5
     * @return
     * @Desc: +CPOTA,pname,fsize,pnumb,psize,bcc[pnumb],md5,cmdbcc\r\n
     */
    public static String assembleOTACommand(String originFileName, long fileSize, String bccCheck, String md5) {
        String fileName = originFileName.split(".bin")[0];
        final String comma = ",";
//        final String head = "CPOTA";
        final String head = PacketConstants.CPOTA;
        final String psize = String.valueOf(1024);
        String fsize = String.valueOf(fileSize);
        long fileCapacity = (fileSize % 1024 == 0 ? fileSize / 1024 : fileSize / 1024 + 1);
        String pnumb = String.valueOf(fileCapacity);

        String otaCommand = head + comma + fileName + comma + fsize + comma + pnumb + comma + psize + comma + bccCheck + comma + md5;
        // 直接获OTA指令的数组,进行BCC校验
        String cmdcc = BytesUtil.bytesXORCheck(otaCommand.getBytes());
        return otaCommand + comma + cmdcc;
    }

    /**
     * 起始关键字	软件版本	Cmd	设置参数(可变)	BCC校验	结束符
     * +BDG01	SorftwareVer	命令类型	Para	BccNb	\r\n
     * +BDG01,SorftwareVer,Cmd[,Para],BccNb\r\n
     *
     * @Desc: + 与 \r\n 会在下发指令的时候自动添加
     */
    public static String assembleNorthCommand(String command, int softVersion, String param) {
//        String head = "BDG01";
        String head = PacketConstants.DEVICE_COMMAND_HEAD;
        String comma = ",";
        String completeCommand = head + comma + softVersion + comma + command;
        if (StringUtils.isNotBlank(param)) {
            completeCommand += comma + param;
        }

        String cmdcc = BytesUtil.bytesXORCheck(completeCommand.getBytes());
        return completeCommand + comma + cmdcc;
    }

    /**
     * 组装激活指令
     * +BDG01,10,OPERASTA,1,53\r\n
     *
     * @param softVersion
     * @return 示例：+BDG01,10,OPERASTA,1,53\r\n
     * 响应：+BDG01RSP,0860527055265319,10,CHKDVISTA,1,78
     */
    public static String assembleActivateCommand(int softVersion) {
        return assembleNorthCommand(PacketConstants.ACTIVATED, softVersion, 1 + "");
    }

    /**
     * 组装库存指令
     *
     * @param softVersion
     * @return 示例：+BDG01,10,STOCK,01\r\n
     * 响应：+BDG01RSP,0860527055265319,10,STOCK,78\r\n
     */
    public static String assembleStockCommand(int softVersion) {
        return assembleNorthCommand(CommandCode.STOCK, softVersion, "");
    }

    /**
     * 组装更新设备位置指令
     *
     * @param version
     * @return
     * @Format: +BDG01,版本号,UPDLOC,BccNb\r\n
     * @Desc: +BDG01,10,UPDLOC,53\r\n
     */
    public static String assembleUPDLOCCommand(int version) {
        return assembleNorthCommand(CommandCode.UPDLOC, version, "");
    }

    /**
     * 组装设备休眠时间间隔指令
     *
     * @param version   版本号
     * @param sleepTime 休眠时间间隔
     * @return
     * @Format: +BDG01,版本号,SLEEP,休眠时间间隔,BccNb\r\n
     * @Desc: +BDG01,06,SLPT,180,48\r\n
     */
    public static String assembleSLPTCommand(int version, int sleepTime) {
        return assembleNorthCommand(CommandCode.SLPT, version, sleepTime + "");
    }

    /**
     * 组装查询设备休眠时间间隔指令
     *
     * @param version 版本号
     * @return
     * @Format: +BDG01,版本号,CHKSLPT,BccNb\r\n
     * @Desc: +BDG01,10,CHKSLPT,53\r\n
     */
    public static String assembleCHKSLPTCommand(int version) {
        return assembleNorthCommand(CommandCode.CHKSLPT, version, "");
    }

    /**
     * 组装设置演示数据上报指令
     *
     * @param version      版本号
     * @param openDemoDate 是否开启演示数据上报 1 开启 0 关闭
     * @return
     * @Format: +BDG01,版本号,DDEREPOPEN,开启/关闭,BccNb\r\n
     * @Desc: +BDG01,10,DDEREPOPEN,1,4A\r\n
     */
    public static String assembleDDEREPOPENCommand(int version, int openDemoDate) {
        return assembleNorthCommand(CommandCode.DDEREPOPEN, version, openDemoDate + "");
    }

    /**
     * 组装设备持续更新坐标指令
     *
     * @param version 版本号
     * @param open    是否开启功能 1 开启 0 关闭
     * @return
     * @Format: +BDG01,版本号,SETUPDLOCCNTU,开启/关闭,40
     * @Desc: +BDG01,10,SETUPDLOCCNTU,1,40
     */
    public static String assembleSETUPDLOCCNTUCommand(int version, int open) {
        return assembleNorthCommand(CommandCode.SETUPDLOCCNTU, version, open + "");
    }

    /**
     * 组装快速上报指令
     *
     * @param version 版本号
     * @param open    是否开启功能 1 开启 0 关闭
     * @Format: +BDG01,版本号,SENDQKLFUNSTA,开启/关闭,40
     * @Desc: +BDG01,10,SENDQKLFUNSTA,1,40
     */
    public static String assembleSENDQKLFUNSTACommand(int version, int open) {
        return assembleNorthCommand(CommandCode.SENDQKLFUNSTA, version, open + "");
    }
}
