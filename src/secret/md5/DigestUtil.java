/*
 * Created on 2005-5-8
 */
package secret.md5;

import java.security.MessageDigest;

/**
 * md5加密工具
 * 
 * @Title: DigestUtils.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2014-4-4 下午06:51:31
 * @author 杨凯
 */
public class DigestUtil {

    /**
     * Get digest string of input string.
     * 
     * @param input
     *            input string
     * @return digest string
     * @throws GenericException
     */
    public static String digest(String input) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] bDigests = md.digest(input.getBytes("UTF-8"));

            return byte2hex(bDigests);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byte2hex(byte[] b) {

        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * To check if it is equal between two digest sting.
     * 
     * @param digesta
     * @param digestb
     * @return compare result
     * @throws GenericException
     */
    public static boolean isEqual(String digesta, String digestb) throws Exception {
        try {

            return MessageDigest.isEqual(digesta.toUpperCase().getBytes(), digestb.toUpperCase().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 加密后解密
    public static String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    public static void main(String[] ar) throws Exception {
        String docCache = digest("ARTVIEW:".concat("100000000000"));
        System.out.println(docCache);
    }

    public static void categoryMd5() throws Exception {
        String title = "杨凯";
        String docmd5 = digest(("doc" + title).toUpperCase());
        String catName = "日本动画电影";
        String cadNameMd5 = digest(catName.toUpperCase().concat("FENLEI_DOCS_VIEW"));
        System.out.println(cadNameMd5);
        
    }

}
