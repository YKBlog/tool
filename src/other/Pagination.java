package other;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/** 分页工具
 * 
 * @author yk
 * @description ：<br>
 * @date 2015年10月19日 */
public class Pagination<T> {

    private int pageSize; // 每页数据
    private int pageCount; // 总页数
    private int pageNow; // 当前页
    private int pageRows; // 总记录数
    private int pageForm; // 每页开始记录数
    private List<T> datas; // list数据
    private String pageHtml; // 分页html代码

    private static int PAGESIZE_DEFAULT = 10;

    public Pagination() {
    }

    public Pagination(int pageSize, int pageRows, int pageNow) {

        if (pageSize < 1) this.pageSize = PAGESIZE_DEFAULT;
        else this.pageSize = pageSize;

        this.pageRows = pageRows;
        this.pageCount = this.pageRows % pageSize == 0 ? this.pageRows / pageSize : pageRows / pageSize + 1;

        if (pageNow <= 1) this.pageNow = 1;
        else if (pageNow >= pageCount) this.pageNow = pageCount;
        else this.pageNow = pageNow;

        this.pageForm = (this.pageNow - 1) * pageSize;

    }

    public int getpageSize() {
        return pageSize;
    }

    public void setpageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageRows() {
        return pageRows;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public int getPageForm() {
        return pageForm;
    }

    public void setPageForm(int pageForm) {
        this.pageForm = pageForm;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String url, String param, String type, int pageNow, int pageCount) {
        StringBuffer html = new StringBuffer();
        int pagePre = pageNow > 1 ? pageNow - 1 : 1;
        int pageNext = pageNow >= pageCount ? pageCount : pageNow + 1;
        String fristPage = "", prePage = "", nextPage = "", lastPage = "";
        if (StringUtils.isNotBlank(type)) { // 通过type的赋值来判断是同步分页还是异步ajax接口分页
            url += "?param=" + param + "&type=" + type + "&pageNow=";
            fristPage = "href=\"" + url + "1\"";
            prePage = "href=\"" + url + pagePre + "\"";
            nextPage = "href=\"" + url + pageNext + "\"";
            lastPage = "href=\"" + url + pageCount + "\"";
        } else {
            fristPage = "href=\"#\" onclick=(" + url + "(1))";
            prePage = "href=\"#\" onclick=(" + url + "(" + pagePre + "))";
            nextPage = "href=\"#\" onclick=(" + url + "(" + pageNext + "))";
            lastPage = "href=\"#\" onclick=(" + url + "(" + pageCount + "))";
        }
        html.append("<div class=\"wrap page\"><div class=\"page_con clearfix\">");
        html.append("<a class=\"active w_90\"" + fristPage + ">首&nbsp;&nbsp;页</a>");
        html.append("<a class=\"active w_90\"" + prePage + ">上一页</a>");

        // 如果页码过多，只显示一部分页码
        int pageEnd = pageNow + 4 >= pageCount ? pageCount : pageNow + 4;
        for (int i = pageNow; i <= pageEnd; i++) {
            String page = "";
            if (StringUtils.isNotBlank(type)) {
                page = "href=\"" + url + i + "\"";
            } else {
                page = "href=\"#\" onclick=(" + url + "(" + i + "))";
            }
            if (i == pageNow) {
                html.append(" <a class=\"active\"" + page + ">" + i + "</a>");
            } else {
                html.append(" <a " + page + ">" + i + "</a>");
            }
        }

        html.append("<a class=\"active w_90\"" + nextPage + ">下一页</a>");
        html.append("<a class=\"active m_r0 w_90\"" + lastPage + ">末&nbsp;&nbsp;页</a>");
        html.append("</div></div>");

        this.pageHtml = html.toString();
    }

    /** 创建分页对象
     * 
     * @param param
     *            参数
     * @param type
     *            参数类型
     * @param pagination
     *            分页对象
     * @param lists
     *            数据列表 */
    public void createPagination(String url, String param, String type, Pagination<T> pagination, List<T> lists) {
        if (lists != null && lists.size() > 0) {
            setDatas(lists);
            setPageHtml(url, param, type, pageNow, pageCount);
        } else {
            setDatas(null);
        }
    }

}
