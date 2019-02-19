package aes.java;

public class test {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String key = "qwertyuiasdfghjk";
        String message = "";
        String jiami = AESUtil.encrypt(message, key);
        System.out.println(jiami);
        System.out.println(AESUtil.decrypt(jiami, key));
    }
}
