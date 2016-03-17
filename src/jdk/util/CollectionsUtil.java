package jdk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @Title: CollectionsUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 *               功能概述： JDK collections工具类的使用 <br>
 * @Created on 2015年9月14日 下午1:58:54
 * @author yangkai
 */
public class CollectionsUtil {

    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(6);
        list.add(-2);
        list.add(0);
        list.add(3);
        list.add(-9);
        System.out.println(list);
        Collections.reverse(list); // 翻转数据元素
        System.out.println(list);
        Collections.sort(list); // 按自然数排序
        System.out.println(list);
        int max = Collections.max(list);
        System.out.println(max);
        int min = Collections.min(list);
        System.out.println(min);
        int index = Collections.binarySearch(list, -9);   //二分法查找指定元素的位置
        System.out.println(index);
        Collections.shuffle(list); // 将数据元素洗牌操作
        System.out.println(list);
        Collections.fill(list, "yk");  //将整个list替换成指定元素
        System.out.println(list);
    }
}
