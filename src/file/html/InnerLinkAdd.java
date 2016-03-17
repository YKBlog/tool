package file.html;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @Title: AddInnerLink.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>内链添加、内链替换工具
 * @Created on 2014-4-4 下午06:49:36
 * @author 杨凯
 */
public class InnerLinkAdd {


        
        /**
         * 分析一段文字，添加内链（即：将“{}”转化成链接）
         * 
         * @param content
         * @return
         */
        private String addInnerLinkForContent(String content) {
            if ((StringUtils.isEmpty(content)) || (StringUtils.isBlank(content))) {
                return content;
            }
            String innerLinkUrl = "http://www.google.com/";
            String linkspiltpreg = "\\{([^\\{\\}]+?)\\}";
            Pattern pattern = Pattern.compile(linkspiltpreg);
            Matcher matcher = pattern.matcher(content);
            String matchStr = null;
            while (matcher.find()) {
                matchStr = matcher.group(1);
                if (StringUtils.isNotEmpty(matchStr) && StringUtils.isNotBlank(matchStr)) {
                    try {
                        content = StringUtils.replaceOnce(content, "{".concat(matchStr).concat("}"), "<a href='".concat(innerLinkUrl).concat(URLEncoder.encode(matchStr, "UTF-8")).concat("' class='alinks' target='_blank'>").concat(matchStr).concat("<i></i></a>"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            return content;
        }
        
        /**
         * 对url中文编码
         * @param str
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String chineseToUtf8(String str) throws UnsupportedEncodingException {
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(str);
            StringBuffer finded = new StringBuffer();
            while (m.find()) {
                finded.append(m.group());
            }
            return str.replaceAll(finded.toString(), URLEncoder.encode(finded.toString(), "utf-8"));
        }

}
