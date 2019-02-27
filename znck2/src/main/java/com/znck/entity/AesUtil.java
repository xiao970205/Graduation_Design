 package com.znck.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * aes加密解密的静态类
 * AesUtil
 * @author 肖舒翔
 * @version 1.0
 *
 */
public class AesUtil {
     private static final String KEY_ALGORITHM = "AES";
     private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
     //默认的加密算法

     /**
      * AES 加密操作
      *
      * @param content 待加密内容
      * @param password 加密密码
      * @return 返回Base64转码后的加密数据
      */
     public static String encrypt(String content, String password) {
         try {
             Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
             // 创建密码器

             byte[] byteContent = content.getBytes("utf-8");

             SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
             
             cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
             // 初始化为加密模式的密码器

             byte[] result = cipher.doFinal(byteContent);
             // 加密

             return Base64.encodeBase64String(result);
             //通过Base64转码返回
         } catch (Exception ex) {
             Logger.getLogger(AesUtil.class.getName()).log(Level.SEVERE, null, ex);
         }

         return null;
     }
     
     /**
      * AES 解密操作
      *
      * @param content
      * @param password
      * @return
      */
     public static String decrypt(String content, String password) {

         try {
             //实例化
             Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

             SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
             
             //使用密钥初始化，设置为解密模式
             cipher.init(Cipher.DECRYPT_MODE, skeySpec);

             //执行操作
             byte[] result = cipher.doFinal(Base64.decodeBase64(content));

             return new String(result, "utf-8");
         } catch (Exception ex) {
             Logger.getLogger(AesUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return null;
     }
}
