package file.json.gson;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * @Title: Gson.java
 * @Description: <br>
 *               <br>
 * @Created on 2016年3月12日 下午4:08:40
 * @author yangkai
 * @version 1.0
 */
public class MyGson {

    static Gson gson = new Gson();

    public static void test1() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("李坤");
        student1.setBirthDay(new Date());
        student1.setTime(new Timestamp(format.parse("2014-05-05 05:05:05").getTime()));
        // System.out.println("----------简单对象之间的转化-------------");
        // 简单的bean转为json
        String s1 = gson.toJson(student1);
        // System.out.println("简单Bean转化为Json===" + s1);

        // json转为简单Bean
        Student student = gson.fromJson(s1, Student.class);
        // System.out.println("Json转为简单Bean===" + student);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("曹贵生");
        student2.setBirthDay(new Date());
        student2.setTime(new Timestamp(format.parse("2014-06-06 06:05:05").getTime()));

        Student student3 = new Student();
        student3.setId(3);
        student3.setName("柳波");
        student3.setBirthDay(new Date());
        student3.setTime(new Timestamp(format.parse("2014-03-03 03:05:05").getTime()));

        List<Student> list = new ArrayList<Student>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        // System.out.println("----------带泛型的List之间的转化-------------");
        // 带泛型bean的list转化为json
        String s2 = gson.toJson(list);
        System.out.println("带泛型bean的list转化为json==" + s2);

        // json转为带泛型bean的list
        List<Student> retList = gson.fromJson(s2, new TypeToken<List<Student>>() {
        }.getType());
        for (int i=0;i<retList.size();i++) {
            System.out.println(i+1);
            Student stu = retList.get(i);
            System.out.println(stu);
        }
        
        System.out.println("----------------------------------------");
    }

    public void test2() {
        // 带泛型String的list转化为json
        List<String> listStr = new ArrayList<String>();
        listStr.add("yk1");
        listStr.add("yk2");
        listStr.add("yk3");
        String strJson = gson.toJson(listStr);
        System.out.println("=====带泛型string的list转化为json=====" + strJson);
        // json转为带泛型String的list
        /*
         * List<String> strList = gson.fromJson(strJson, new TypeToken<List<String>>() { }.getType()); for (String str : strList) { System.out.println(str); }
         */

    }


    private static void test3() {
        String str = "杨凯";
        String s2 = gson.toJson(str);
        System.out.println(s2);
        String gson =  new Gson().fromJson(s2,new TypeToken<String>() {}.getType());
        System.out.println(gson);
        String ajaxJson = new Gson().toJson(null, Student.class);
        System.out.println(ajaxJson);
        String test = new Gson().fromJson(ajaxJson,new TypeToken<Student>(){}.getType());
        System.out.println(test);
    }
    public static void main(String[] args) throws ParseException {
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("曹贵生");
        student2.setTime(new Timestamp(System.currentTimeMillis()));
        String ajaxJson = new Gson().toJson(student2, Student.class);
        System.out.println(ajaxJson);
        Student stu = new Gson().fromJson(ajaxJson,new TypeToken<Student>(){}.getType());
        System.out.println(stu.toString());
    }

}
