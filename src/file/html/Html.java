package file.html;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.AttributePrefixFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

/**
 * @Title: Html.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 *               Html代码过滤工具
 * @Created on 2014-4-4 下午06:53:04
 * @author 杨凯
 */

public class Html {

    /**
     * 得到需要的HTML元素的节点列表(只取第一层)
     */
    public static NodeList filterHtmlElementList(String html, String tagname) {
        NodeList nodelist = getHtmlBody(html);
        TagNameFilter filter = new TagNameFilter(tagname);
        return nodelist.extractAllNodesThatMatch(filter, true);
    }

    /**
     * filterScript去掉html页面的script脚本代码
     * 
     * @param content
     * @return
     */
    public static String filterScript(String html) {
        NodeList nodelist = getHtmlBody(html);
        OrFilter filter = new OrFilter(new TagNameFilter("SCRIPT"), new TagNameFilter("NOSCRIPT"));
        filter = new OrFilter(filter, new AttributePrefixFilter("on"));
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * 
     * <li>过滤Html代码中指定的元素返回Html代码</li> <li>filterHtmlElement</li> <li>@param
     * html <li>@param tagname <li>@return</li>
     * 
     */
    public static String filterHtmlElement(String html, String tagname) {
        NodeList nodelist = getHtmlBody(html);
        TagNameFilter filter = new TagNameFilter(tagname);
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * 
     * <li>过滤Html代码中指定的元素返回纯文本</li> <li>filterHtmlElementToText</li> <li>@param
     * html <li>@param tagname <li>@return</li>
     * 
     */
    public static String filterHtmlElementToText(String html, String tagname) {
        NodeList nodelist = getHtmlBody(html);
        TagNameFilter filter = new TagNameFilter(tagname);
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.asString();
    }

    /**
     * filterScript去掉html页面的script脚本代码
     * 
     * @param content
     * @return
     */
    public static String filterStyle(String html) {
        NodeList nodelist = getHtmlBody(html);
        TagNameFilter filter = new TagNameFilter("STYLE");
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * 
     * <li>TODO</li> <li>filterframe</li> <li>@param html <li>@return</li>
     * 
     */
    public static String filterframe(String html) {
        NodeList nodelist = getHtmlBody(html);
        OrFilter filter = new OrFilter(new TagNameFilter("IFRAME"), new TagNameFilter("NOIFRAME"));
        filter = new OrFilter(filter, new AttributePrefixFilter("on"));
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * 过滤所有的标签，返回纯文本内容
     * 
     * @param html
     * @param type
     *            type=0 仅仅替换所有的'<','>'为'&lt;' '&gt;' type=1
     *            过滤掉所有的'<','>',但保留空白字符 type=2 过滤掉所有的'<','>',且不保留空白字符
     * @return String
     */
    public static String filterAllTag(String html, int type) {
        switch (type) {
            case 0:
                html = StringUtils.replace(html, "<", "&lt;");
                html = StringUtils.replace(html, ">", "&gt;");
                break;
            case 1:
                html = getHtmlBody(html).asString();
                break;
            case 2:
                html = getHtmlBody(html).asString();
                html = html.replaceAll("\\s+", " ");
                break;
            case 3:
                html = filterStyle(html);
                html = filterScript(html);
                html = filterframe(html);
                html = getHtmlBody(html).asString();
                html = html.replaceAll("\\s+", " ");
                html = StringUtils.remove(html, "&nbsp;");
                html = StringUtils.remove(html, "&gt;");
                html = StringUtils.remove(html, "&lt;");
                // html=StringUtils.replaceChars(html,"\t\r\n\b"," ");
                break;
            default:
        }
        return html;
    }

    public static NodeList getHtmlBody(String html) {
        NodeList body = new NodeList();
        if (html != null && !html.trim().equals("")) {
            StringBuffer htmlbuf = new StringBuffer();
            htmlbuf.append("<html><head><title>title</title></head><body>");
            htmlbuf.append(html);
            htmlbuf.append("</body></html>");
            Parser parser = Parser.createParser(htmlbuf.toString(), "UTF-8");
            HtmlPage page = new HtmlPage(parser);
            try {
                parser.visitAllNodesWith(page);
            } catch (ParserException e) {
                e.printStackTrace();
            }
            body = page.getBody();
        }
        return body;
    }

    public static void dealLinks(NodeList nl) {
        TagNameFilter tnf = new TagNameFilter("A");
        nl = nl.extractAllNodesThatMatch(tnf, true);
        for (int i = 0; i < nl.size(); i++) {
            LinkTag link = (LinkTag) nl.elementAt(i);
            if (link.isHTTPLikeLink() && !link.getLinkText().trim().equals("") && link.getLink().indexOf("entryview.do") != -1) {
                NodeList nodelist = new NodeList();
                nodelist.add(new TextNode(link.getLinkText()));
                link.setChildren(nodelist);
                try {
                    link.setAttribute("href", "http://www.google.com/" + URLEncoder.encode(link.getLinkText(), "UTF-8"));
                    link.setAttribute("class", "innerlink");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     * 生成合法Tag getTags
     * 
     * @param str
     * @return
     * 
     */
    public static String getTags(String[] tags) {
        if (tags == null) {
            return "";
        }
        HashSet tagSet = new HashSet();
        StringBuffer strbuf = new StringBuffer();
        for (int i = 0; i < tags.length; i++) {
            String tag = tags[i].trim();
            tag = tag.replaceAll(";", ""); // 去掉";"号
            tag = tag.replaceAll("\\s+", " "); // 去掉多余的空白字符，包括空格、制表符、换页符等等
            if (tag.equals("")) {
                continue;
            }
            if (tagSet.add(tag)) {
                strbuf.append(tag);
                strbuf.append(";");
            }
        }
        return strbuf.toString();
    }

    /**
     * 
     * <li>过滤中文空格</li> <li>filterChineseSpace</li> <li>@param text <li>@return</li>
     * 
     */
    public static String filterChineseSpace(String text) {

        int ret = 1;// 是否有变化1有变化，0无变化
        int len = 0;
        int lentmp = 0;
        int a = 0;
        int b = 0;
        String regEx = "";
        Matcher m = null;
        Pattern p = null;

        while (ret == 1) {
            lentmp = text.length();

            regEx = "(^\\s*)|(\\s*$)";

            p = Pattern.compile(regEx);
            m = p.matcher(text);
            text = m.replaceAll("");
            len = text.length();

            if (lentmp == len) {
                a = 0;// 无半角空格
            } else {
                a = 1;// 有半角空格
            }

            lentmp = text.length();

            regEx = "(^\\u3000*)|(\\u3000*$)"; // 表示一个或多个a

            p = Pattern.compile(regEx);
            m = p.matcher(text);
            text = m.replaceAll("");

            regEx = "(^\\ue4c6*)|(\\ue4c6*$)"; // 表示一个或多个a

            p = Pattern.compile(regEx);
            m = p.matcher(text);
            text = m.replaceAll("");

            len = text.length();

            if (lentmp == len) {
                b = 0;// 无全角空格
            } else {
                b = 1;// 有全角空格
            }
            if (a == 0 && b == 0) {
                ret = 0;// 无变化
            } else {
                ret = 1;// 有变化
            }
        }

        return text;
    }

    /**
     * 条目内部图片数量和外部图片数量
     * 
     * @param content
     *            条目内容
     */
    public static int getPicCount(String content) {
        int returnValue = 0;
        NodeList nodelist = getHtmlBody(content);
        nodelist = nodelist.extractAllNodesThatMatch(new TagNameFilter("IMG"), true);
        if (nodelist != null && nodelist.size() > 0) {
            returnValue = nodelist.size();
        }
        return returnValue;
    }

    /**
     * filterScript去掉html页面的form脚本代码
     * 
     * @param html
     * @return
     */
    public static String filterForm(String html) {
        NodeList nodelist = getHtmlBody(html);
        OrFilter filter = new OrFilter(new TagNameFilter("FORM"), new TagNameFilter("NOFORM"));
        filter = new OrFilter(filter, new AttributePrefixFilter("on"));
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * filterScript去掉html页面的input脚本代码
     * 
     * @param html
     * @return
     */
    public static String filterInput(String html) {
        NodeList nodelist = getHtmlBody(html);
        OrFilter filter = new OrFilter(new TagNameFilter("INPUT"), new TagNameFilter("NOINPUT"));
        filter = new OrFilter(filter, new AttributePrefixFilter("on"));
        NotFilter notfilter = new NotFilter(filter);
        nodelist.keepAllNodesThatMatch(notfilter, true);
        return nodelist.toHtml();
    }

    /**
     * 条目内链
     * 
     * @param content
     * 
     */
    public static int getInnerLink(String content) {
        int returnValue = 0;
        NodeList nl = getHtmlBody(content);
        nl = nl.extractAllNodesThatMatch(new TagNameFilter("A"), true);
        for (int i = 0; i < nl.size(); i++) {
            LinkTag link = (LinkTag) nl.elementAt(i);
            if (link.isHTTPLikeLink() && link.getAttribute("CLASS") != null) {
                if (link.getAttribute("CLASS").toUpperCase().equals("INNERLINK")) {
                    returnValue++;
                }
            }
        }
        return returnValue;
    }

    /**
     * 过滤正文中的所有id
     * 
     * @param input
     * @return
     */
    public static String filterId(NodeList nl) {
        HasAttributeFilter haf = new HasAttributeFilter("id");
        NodeList idnl = nl.extractAllNodesThatMatch(haf, true);
        String tempValue = null;
        for (int i = 0; i < idnl.size(); i++) {
            TagNode node = (TagNode) idnl.elementAt(i);
            tempValue = node.getAttribute("id");
            tempValue = StringUtils.replace(tempValue, "changeTheId-", "");
            node.setAttribute("id", "changeTheId-" + tempValue);
        }
        return nl.toHtml();
    }

    /**
     * 奥运幸运用户抽奖时特殊用户名的处理
     * 
     * @param input
     * @return
     */
    public static String filter(String input)// 传值到这里
    {
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '[':
                    filtered.append("");
                    break;
                case ']':
                    filtered.append("");
                    break;
                case '|':
                    filtered.append("");
                    break;
                case '&':
                    filtered.append("");
                    break;
                case '_':
                    filtered.append("");
                    break;
                case '':
                    filtered.append("");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return (filtered.toString());
    }


    public static void main(String args[]) throws Exception {
        String html = FileUtils.readFileToString(new File("c:\\htmltest.TXT"), "UTF-8");
        html = Html.filterframe(html);
        html = Html.filterInput(html);
        html = Html.filterForm(html);
        System.out.println(html);
        FileUtils.writeStringToFile(new File("c:\\htmltest.TXT"), html, "UTF-8");

    }

}
