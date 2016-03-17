package other;

import org.apache.commons.lang.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 获取拼音工具
 * 
 * @Title: PinyinUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2015年2月5日 下午3:09:59
 * @author yangkai
 */
public class PinyinUtil {

    /**
     * 获取传入的汉语字符串的拼音字符串
     * 
     * @author yangkai
     * @param str
     * @param length
     * @return
     */
    public static String getPinYin(String str, int length) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(str)) {
            boolean flag = false;
            if (str.length() > length)
                flag = true;
            for (int i = 0; i < str.length(); i++) {
                String[] strPinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i));
                if (strPinyin == null || strPinyin.length == 0 || strPinyin[0] == null)
                    sb.append(str.charAt(i));
                for (int n = 0; n < strPinyin[0].length() - 1; n++) {
                    sb.append(strPinyin[0].charAt(n));
                    if (flag)
                        break;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 分中英文截取，英文按占一个字符，中文按占两个字符 
     * 方法一
     * 
     * @param str
     *            截取字符串
     * @param num
     *            截取字符串长度
     * @return
     */
    public static String splitStr(String str, int num) {
        StringBuffer sb = new StringBuffer(num);
        int count = 0;
        int strLength = str.length();
        if (StringUtils.isNotBlank(str) && strLength > 0) {
            for (int i = 0; i < strLength; i++) {
                int asciiCode = str.codePointAt(i);
                if (asciiCode >= 0 && asciiCode <= 255)
                    count += 1;
                else
                    count += 2;
                if (count <= num)
                    sb.append(str.charAt(i));
            }
        }
        return sb.toString().length() < strLength ? sb.toString().concat("...") : sb.toString();
    }

    /**
     * 分中英文截取，英文按占一个字符，中文按占两个字符 
     * 方法二
     * 
     * @param str
     *            截取字符串
     * @param num
     *            截取字符串长度
     * @return
     */
    public static String subStr(String str, int max) {
        int sum = 0;
        if (str != null && str.length() > max) {
            StringBuilder sb = new StringBuilder(max);
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i);
                if ((c & 0xff00) != 0)
                    sum += 2;
                else
                    sum += 1;
                if (sum <= max)
                    sb.append((char) c);
                else
                    break;
            }
            return sb.append("...").toString();
        } else
            return str != null ? str : "";
    }

    public static void main(String[] args) {
        // System.out.println(getPinYin("杨凯", 1));
        String str = " tfboys123我注定负你二部";
        String result = splitStr(str, 20);
        System.out.println(result);
        String result1 = subStr(str, 10);
        System.out.println(result1);

    }

}
