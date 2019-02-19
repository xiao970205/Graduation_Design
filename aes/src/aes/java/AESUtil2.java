 package aes.java;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil2 {
     private static final String KEY_ALGORITHM = "AES";
     private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

     /**
      * AES 加密操作
      *
      * @param content 待加密内容
      * @param password 加密密码
      * @return 返回Base64转码后的加密数据
      */
     public static String encrypt(String content, String password) {
         try {
             Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

             byte[] byteContent = content.getBytes("utf-8");

             SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), KEY_ALGORITHM);
             
             cipher.init(Cipher.ENCRYPT_MODE, skeySpec);// 初始化为加密模式的密码器

             byte[] result = cipher.doFinal(byteContent);// 加密

             return bytesToHex(result);//通过Base64转码返回
         } catch (Exception ex) {
             Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
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
             byte[] result = cipher.doFinal(hexToByteArray(content));
             
             return new String(result, "utf-8");
         } catch (Exception ex) {
             Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         return null;
     }
     
     /** 
      * 字节数组转16进制 
      * @param bytes 需要转换的byte数组 
      * @return  转换后的Hex字符串 
      */  
     public static String bytesToHex(byte[] bytes) {  
         StringBuffer sb = new StringBuffer();  
         for(int i = 0; i < bytes.length; i++) {  
             String hex = Integer.toHexString(bytes[i] & 0xFF);  
             if(hex.length() < 2){  
                 sb.append(0);  
             }  
             sb.append(hex);  
         }  
         return sb.toString();  
     }
     
     /** 
      * hex字符串转byte数组 
      * @param inHex 待转换的Hex字符串 
      * @return  转换后的byte数组结果 
      */  
     public static byte[] hexToByteArray(String inHex){  
         int hexlen = inHex.length();  
         byte[] result;  
         if (hexlen % 2 == 1){  
             //奇数  
             hexlen++;  
             result = new byte[(hexlen/2)];  
             inHex="0"+inHex;  
         }else {  
             //偶数  
             result = new byte[(hexlen/2)];  
         }  
         int j=0;  
         for (int i = 0; i < hexlen; i+=2){  
             result[j]=hexToByte(inHex.substring(i,i+2));  
             j++;  
         }  
         return result;   
     }  
     
     /** 
      * Hex字符串转byte 
      * @param inHex 待转换的Hex字符串 
      * @return  转换后的byte 
      */  
     public static byte hexToByte(String inHex){  
        return (byte)Integer.parseInt(inHex,16);  
     }  


}
