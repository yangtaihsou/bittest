package com.bittest.platform.bg.common.utils;


import org.apache.commons.io.Charsets;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;


/**
 * automation
 * mingfeng.ma
 * bittest123
 * 2018/7/2
 */
public class EncryptUtil {
    static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * RSA 公钥加密
     *
     * @param encryptStr 需要加密的字符串
     * @param publicKey  公钥
     * @return
     */
    public static byte[] encryptRSA(String encryptStr, String publicKey) {
        try {
            Key key = EncryptUtil.getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encry = cipher.doFinal(encryptStr.getBytes(Charsets.UTF_8));
            return Base64.encodeBase64(encry);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 获取public公钥
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static void main(String[] args) {

    }

}
