package file.json.json;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonConvertTest {

    @Test
    public void test1() {

        String ss = "分啊苟";
        String str = "分啊苟;富贵个;帅哥个;干热河;谷和;他的;怀抱;";
        System.out.println(str.contains(ss));
        String[] s = str.split(",");
        List<String> arr = Arrays.asList(s);
        JSONObject obj = JsonConvert.generate(arr);
        System.out.println(obj.toString());
        JSONArray json = JSONArray.fromObject(s);
        System.out.println(json);
        List<String> collection = (List<String>) JSONArray.toCollection(json);
        for (String st : collection) {
            System.out.println(st);
        }
        Object[] array = (Object[]) collection.toArray();
        for (Object string : array) {
            String value = (String) string;
            System.out.println(value + "========");
            System.out.println(string);
        }
    }

    public void test2() {
        List<String> lists = new ArrayList<String>();
        lists.add(",");
        lists.add(",");
        lists.add(",");
        // JSONObject jsonObject =JSONObject.fromObject(lists);
        JSONArray jsonArray = JSONArray.fromObject(lists);
        System.out.println(jsonArray.toString());
        List<String> listStr = (List<String>) JSONArray.toCollection(jsonArray);
        System.out.println(listStr);
        /*
         * for(String str:listStr){ System.err.println(str); }
         */
        if (!listStr.contains("yk0")) {
            System.out.println("---");
        }
        if (listStr.contains("yk1")) {
            System.out.println("--=====-");
        }
        /**
         * boolean[] boolArray = new boolean[] { true, false, true }; JSONArray jsonArray = JSONArray.fromObject(boolArray); String s = jsonArray.toString(); System.out.println(s);
         */
    }


    public void test4() {
        String organName = "{互动} CEO、董事长；{行业} CTO;<p'>小百科</p>CMO";
        String[] organNames = organName.replace("；", ";").split(";");
        for (String str : organNames) {
            if (str.contains("{") && str.contains("}")) {
                String replaceFront = str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
                String replaceBack = str.substring(str.indexOf("{") + 1, str.lastIndexOf("}"));
                replaceBack = "<a href='www.baike.com/wiki/" + replaceBack + "'>" + replaceBack + "</a>";
                organName = organName.replaceAll(Pattern.quote(replaceFront), replaceBack);
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(organName.split(";"));
    }

    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String organName = "{百科} CEO、董事长；杨凯 主席;{百科} CEO、董事长";
        String[] strs = organName.replace("；", ";").split(";");
        JSONArray jsonArray1 = JSONArray.fromObject(strs);
        JSONArray jsonArray = JSONArray.fromObject(Arrays.asList(strs));
        System.out.println("jsonArray1:"+jsonArray1);
        System.out.println("jsonArray:"+jsonArray);
        Iterator listStr=jsonArray1.iterator();
        while(listStr.hasNext()){
            String str = (String) listStr.next();
            System.out.println(str);
            
        }
    }
}
