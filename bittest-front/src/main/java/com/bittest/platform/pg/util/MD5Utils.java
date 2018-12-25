package com.bittest.platform.pg.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 *
 * @author syp
 */
public class MD5Utils {
    public static String md5(String str) {
        return MD5Utils.md5(str, null);
    }

    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str, String encoding) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] data = null;
            if (encoding != null && !encoding.equals("")) {
                data = str.getBytes(encoding);
            } else {
                data = str.getBytes();
            }
            md.update(data);
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String s = "hhrrrr";
        System.out.println(md5(s, "GBK"));
    }
}
