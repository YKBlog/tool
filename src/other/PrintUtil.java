package other;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;


/** 打印相关工具
 * 
 * @author yk
 * @description ：<br>
 * @date 2015年10月12日 */
public class PrintUtil {

    public static int HTTP_CONNECTION_TIMEOUT; // url连接超时时间15s
    public static int HTTP_SO_TIMEOUT; // 读取数据超时时间15s
    
    public static void responseText(HttpServletResponse response, String result, String callback) {
        // 指定内容类型
        response.setContentType("text/html;charset=UTF-8");
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expirse", 0);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (StringUtils.isNotBlank(callback)) out.print(callback + "(" + result + ")");
            else out.print(result);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) out.close();
        }
    }

    

    /**
     * 获取get请求
     * @param url
     * @param callType
     * @return
     */
    public static String callByGet(String url) {
        String result = "";
        GetMethod getMethod = new GetMethod(url);
        try {
            HttpClient httpClient = new HttpClient();
            HttpConnectionManagerParams httpParam = httpClient.getHttpConnectionManager().getParams();
            httpParam .setConnectionTimeout(HTTP_CONNECTION_TIMEOUT);
            httpParam.setSoTimeout(HTTP_SO_TIMEOUT);
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                InputStream inputStream = getMethod.getResponseBodyAsStream();
                if (inputStream != null) 
                    result =  IOUtils.toString(inputStream, "utf-8");
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return result;
    }
}
