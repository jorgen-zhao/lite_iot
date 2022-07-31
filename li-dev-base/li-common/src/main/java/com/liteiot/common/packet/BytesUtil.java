package com.liteiot.common.packet;
/*
 *  Class:  BytesUtil
 *
 *  Author: Chen
 *  Date:   2019/4/11 10:30
 */

import com.sun.xml.internal.fastinfoset.util.CharArrayString;
import io.netty.buffer.ByteBufUtil;
import lombok.Getter;

import java.math.BigInteger;


public class BytesUtil {

    public static int bytesCopy(byte[] source, int offset, byte[] dest) {
        return bytesCopy(source, offset, dest, 0, dest.length);
    }

    private static int bytesCopy(byte[] source, int offset, byte[] dest, int destPos, int length) {
        System.arraycopy(source, offset, dest, 0, length);
        return offset + length;
    }

    public static String bytesToStr(byte[] source, boolean addZero) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : source) {
            if (addZero) {
                if ((b & 0xff) >= 0 && (b & 0xff) < 10 && stringBuilder.length() != 0) {
                    stringBuilder.append("0");
                }
            }
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static String readCharsToStr(byte[] source, int start, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < length + start; i++) {
            stringBuilder.append((char) source[i]);
        }
        return stringBuilder.toString();
    }

//    public static String readBytesToHexStr(byte[] source, int start, int length) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = start; i < length + start; i++) {
//            stringBuilder.append(BCDtoString(source[i]));
//        }
//        return stringBuilder.toString();
//    }


    public static int readS16bit(byte[] code, int index) {
        return code[index] << 8 | code[index + 1] & 255;
    }

    public static int readS24bit(byte[] code, int index) {
        return code[index] << 16 | (code[index + 1] & 0xFF) << 8 | code[index + 2] & 0xFF;
    }

    public static int read32bit(byte[] code, int index) {
        return code[index] << 24 | (code[index + 1] & 255) << 16 | (code[index + 2] & 255) << 8 | code[index + 3] & 255;
    }

    public static long read40bit(byte[] code, int index) {
        return code[index] << 32 | code[index + 1] << 24 | (code[index + 2] & 255) << 16 | (code[index + 3] & 255) << 8 | code[index + 4] & 255;
    }

    public static long read48bit(byte[] code, int index) {
        return code[index] << 40 | code[index + 1] << 32 | code[index + 2] << 24 | (code[index + 3] & 255) << 16 | (code[index + 4] & 255) << 8 | code[index + 4] & 255;
    }

    public enum Radix {
        DEC(10),
        HEX(16);

        @Getter
        private int val;

        Radix(int value) {
            this.val = value;
        }

    }

    public static int readCharsToInt(byte[] source, int index, int length, Radix radix) {
        int result = 0;
        for (int i = 0; i < length; i++) {
            int val = (source[i + index] & 0xff);
            val = val <= 57 ? val - '0' : val <= 70 ? val - 55 : val - 87;
            result += Math.pow(radix.getVal(), length - i - 1) * val;
        }
        return result;
    }

    public static int findByteIndex(byte[] source, char target) {

        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 10进制数字转换16进制字符串
     *
     * @param msg
     * @return
     */
    public static String bytesToHexString(byte[] msg) {
        StringBuilder hexStr = new StringBuilder();
        for (byte b : msg) {
            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1) {
                hexStr.append("0");
            }
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (3 - i) * 8;
            value += (bytes[i] & 0xFF) << shift;
        }
        return value;
    }

    public static int[] hexStringToIntArray(String s) {
        int len = s.length();
        int[] b = new int[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (int) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }


    public static byte[] hexToByteArr(String hexStr) {
        char[] charArr = hexStr.toCharArray();
        byte btArr[] = new byte[charArr.length / 2];
        int index = 0;
        for (int i = 0; i < charArr.length; i++) {
            int highBit = hexStr.indexOf(charArr[i]);
            int lowBit = hexStr.indexOf(charArr[++i]);
            btArr[index] = (byte) (highBit << 4 | lowBit);
            index++;
        }
        return btArr;
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

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
     * OXR校验
     * 在线BBC校验: http://www.ip33.com/bcc.html
     *
     * @param bytes
     * @return
     */
    public static String bytesXORCheck(byte[] bytes) {
        int nAll = 0;
        for (int nTemp : bytes) {
            nAll = nAll ^ nTemp;
        }
        return String.format("%02X", nAll & 0xff);
    }


    /**
     * 16进制转十进制单精度数据
     *
     * @param hexStr
     * @return
     */
    public static float hexToFloat(String hexStr) {
        return Float.intBitsToFloat(Integer.valueOf(hexStr.trim(), 16));
    }
}
