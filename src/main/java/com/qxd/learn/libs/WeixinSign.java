package com.qxd.learn.libs;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class WeixinSign {

    // 与接口配置信息中的Token要一致
    private static String token = "kankan";

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        System.out.println("--------------------------------------kankans-------------------------------------");
        if (StringUtils.isNotBlank(signature) && StringUtils.isNotBlank(timestamp) && StringUtils.isNotBlank(nonce)){

            String[] arr = new String[] { token, timestamp, nonce };
            // 将token、timestamp、nonce三个参数进行字典序排序
            Arrays.sort(arr);
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                content.append(arr[i]);
            }
            MessageDigest md = null;
            String tmpStr = null;

            try {
                //md = MessageDigest.getInstance("SHA-1");
                // 将三个参数字符串拼接成一个字符串进行sha1加密
                //System.out.println(content.toString().getBytes());
                //byte[] digest = md.digest(content.toString().getBytes());
                //System.out.println("digest :"+digest);
                //System.out.println("sha1 :"+sha1(content.toString()));

                //tmpStr = byteToStr(digest);
                //System.out.println("tmpStr :"+tmpStr);
                tmpStr = sha1(content.toString());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            content = null;

            // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
//            return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
            return tmpStr != null ? tmpStr.equals(signature) : false;

        }
        return false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * sha1加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String data) throws NoSuchAlgorithmException {
        //加盐   更安全一些
//        data += "lyz";
        //信息摘要器                                算法名称
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();

        //创建一个40位长度的字节数组
        char[] chs = new char[len*2];
        //循环20次
        for(int i=0,k=0;i<len;i++) {
            byte b3 = b2[i];//获取摘要计算后的字节数组中的每个字节
            // >>>:无符号右移
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }

        //字符数组转为字符串
        return new String(chs);
    }

}
