package com.liteiot.common.packet;

import com.sun.xml.internal.fastinfoset.util.CharArrayString;
import io.netty.buffer.ByteBufUtil;

/**
 * Class:  HexDumpMsgFormat
 * <p>
 * Author: Chen
 * Date:   2019/6/4 18:34
 */

public class HexDumpMsgFormat {

    private static final String HEX = "0123456789ABCDEF";

    /**
     * 十六进制字符串转为十进制字节数组
     *
     * @param value
     * @return
     */
    public static byte[] formatStringToBytes(String value) {
        CharSequence charSequence = new CharArrayString(value, true);
        return ByteBufUtil.decodeHexDump(charSequence);
    }

    /**
     * 十进制字节数组转为十六进制字节数组
     */
    public static byte[] desByte2hexBytes(byte[] desBytes) {
        int length = desBytes.length;
        byte[] hexBytes = new byte[length << 1];
        int pos;
        for (int i = 0; i < length; i++) {
            pos = 2 * i;
            hexBytes[pos] = HEX.getBytes()[(desBytes[i] & 0xf0) >> 4];
            hexBytes[pos + 1] = HEX.getBytes()[desBytes[i] & 0x0f];
        }
        return hexBytes;
    }

    /**
     * 十六进制字符串转10进制字节数组
     */
    public static byte[] hexStr2DesBytes(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        int k = 0;
        for (int i = 0; i < len; i++) {
            byte high = (byte) (Character.digit(hex.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hex.charAt(k + 1), 16) & 0xff);
            result[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return result;
    }

}
