package secret.md5;

import java.security.MessageDigest;

/**
 * MD5 ����
 * 
 * @Title: MD5Digest.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2015��7��8�� ����4:30:50
 * @author yangkai
 */
public class MD5Digest {

    /**
     * �����ַ���md5����
     * 
     * @param source
     * @return
     */
    private static String encryptToMD5(String source) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(source.getBytes("UTF-8"));
            return byte2hex(md5.digest());
        } catch (Exception e) {
            return source;
        }
    }

    /**
     * ������ת�ַ���
     * 
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + "";
        }
        return hs;
    }

    public static void main(String[] args) {
        String test = encryptToMD5("�");
        System.out.println(test);
    }
}
