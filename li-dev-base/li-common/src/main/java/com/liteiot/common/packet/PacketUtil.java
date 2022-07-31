package com.liteiot.common.packet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class:  PacketUtil
 * <p>
 * Author: zhaoyg
 * Date:   2021/11/5 10:56
 * Desc:   PacketUtil
 */

public class PacketUtil {

    /**
     * 计算一个文件的MD5数值
     *
     * @param path 文件路径
     * @return
     * @Ref: https://blog.csdn.net/mqdxiaoxiao/article/details/88804192
     */
    public static String calculateFileHexMD5(String path) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            fis.close();
            byte[] byteArray = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : byteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().toUpperCase();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算每1024字节长度的BCC校验码
     *
     * @param path 文件路径
     * @return
     * @throws IOException
     */
    public static List<String> calculateEveryPieceBCC(String path) throws IOException {
        List<String> xorCheckList = new ArrayList<>();
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        // 剩余最后一包长度
        int restPacketSize =  (file.length() % 1024) == 0 ? 1024 : (int) (file.length() % 1024);
        // 执行次数
        long executeTimes = file.length() / 1024;
        executeTimes = (restPacketSize == 0 ? executeTimes : executeTimes + 1);

        byte[] buffer = new byte[1024];
        for (long index = 0; index < executeTimes; index++) {
            if (index == (executeTimes - 1)) {
                buffer = new byte[restPacketSize];
            }
            if (fis.read(buffer) != -1) {
                String xorCheck = BytesUtil.bytesXORCheck(buffer);
                xorCheckList.add(xorCheck);
            }
        }

        return xorCheckList;
    }
}
