package jdk.util;

import java.util.Arrays;

/**
 * 
 * @Title: StringUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>功能概述：JDK 中字符串相关的三个类：String、StringBuffer、StringBuilder                 
 *               <br>区别：
 *               <br>1.String是java的基本类型，使用final修饰是不可变的类,即该字符串对象一旦赋值不可改变；如果使用字符串常量的方式，是比较耗费内存的，
 *               <br>    多个字符串相加会分别产生中间常量存在内存，比如 String str = "a"+"b"+"c" 会在内存生成5个常量，除了相加的三个，另外是ab和abc
 *               <br>2.StringBuffer 是string的缓冲区类，而且是线程安全的；而且提供了很多对字符串操作的方法，比如append方法就是解决string占用内存问题的
 *               <br>3.StringBuilder 根StringBuffer的作用基本上是一样的，只是它是线程不安全的，所以效率更高一些            
 * @Created on 2015年9月15日 下午5:42:52
 * @author yangkai
 */
public class StringUtil {

    public static void main(String[] args) {
        StringMethod();
    }

    /**
     * StringBuffer或者StringBuilder中的方法
     */
   public static void StringbuildMethod(){
       StringBuilder sb = new StringBuilder();
       sb.append("java");
       //insert 向指定位置插入字符串
       sb.insert(0, "hello ");
       System.out.println(sb.toString());
       //使用目标字符串替换起始位置中间的字符串
       sb.replace(5, 6,",");
       System.out.println(sb.toString());
       //反转字符串
       sb.reverse();
       System.out.println(sb.toString());
       //删除指定位置的字符串
       sb.delete(5,6);
       System.out.println(sb.toString());
       System.out.println(sb.length()+"---"+sb.capacity());
       
       
   }
    /**
     * String 类中的常用方法
     */
    public static void StringMethod() {
        String str1 = "hello";
        String str2 = "Java";
        String str3 = "Word";
        String str = str1+str2+str3;
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2).append(str3);
        //比较string和stringbuffer的值
        System.out.println("contentEquals： "+str.contentEquals(sb));
        //找出目标字符出现的第一个位置，没有返回-1
        System.out.println("indexOf："+str.indexOf(2)+"---"+str.indexOf('l'));
        //替换字符串
        System.out.println("replace："+str.replace(str1, "123"));
        //以目标字符串开头，如果是返回true
        System.out.println("startsWith："+str.startsWith("123"));
        //以目标字符串结尾，如果是返回true
        System.out.println("endsWith："+str.endsWith("d"));
        //截取子串
        System.out.println("subString："+str.substring(3,6));
        //转小写
        System.out.println("toLowerCase："+str.toLowerCase());
        //转大写
        System.out.println("toUpperCase："+str.toUpperCase());
        //返回目标小标的字符串
        System.out.println("charAt："+str.charAt(5));
        //字符串转数组
        System.out.println("toCharArray："+Arrays.toString(str.toCharArray()));
        //根据特殊标记截取字符串
        System.out.println("split："+Arrays.toString(str.split("o")));
    }
}
