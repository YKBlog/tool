package network.http;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 *
 * @Title: SendFormat.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br> 接口写出数据
 * @Created on 2014-7-10 下午3:34:36
 * @author 杨凯
 */
public class SendFormat {

    /**
     * xml 格式数据
     * 
     * @param response
     * @param doc
     */
    public static void outDocToXML(HttpServletResponse response, Document doc) {
        // 以下代码请注意编码顺序
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out = null;
        StringWriter writer = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint(); //
        format.setEncoding("UTF-8");
        XMLWriter xmlwriter = new XMLWriter(writer, format);

        try {
            out = response.getWriter();
            xmlwriter.write(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(writer.toString());
        out.close();
    }

    /**
     * json 数据格式
     * 
     * @param response
     * @param data
     * @param type
     */
    protected void sendData(HttpServletResponse response, Object data) {
        if (data != null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.print(data);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
