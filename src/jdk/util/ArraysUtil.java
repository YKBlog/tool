package jdk.util;

import java.util.Arrays;
/**
 * 
 * @Title: ArraysUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>功能概述：  Arrays数组工具类使用               
 *               <br> Arrays.toString ： 将数组转换成字符串输出       
 *               <br> Arrays.copyOf： 将一个数组赋值给一个新的数组          
 *               <br> Arrays.copyOfRange：将一个数组的部分范围赋值给一个新的数组           
 *               <br> Arrays.sort ： 数组排序；可以指定范围排序      
 *               <br> Arrays.fill ： 数组补位赋值或者替换元素；可以指定范围补位          
 *               <br> Arrays.binarySearch ： 二分法查找；查找指定元素key的位置          
 *               <br>           
 * @Created on 2015年9月4日 下午5:31:19
 * @author yangkai
 */
public class ArraysUtil {
    
   public static void main(String[] args) {
    int[] array1 = {6,2,3,8,9};
    int[] array2 = {6,2,3,8,9};
    int[] array3 = null;
    boolean flag = Arrays.equals(array1,array2);
    System.out.println("array1与array2："+flag);
    System.out.println("array3声明："+array3);
    array3 = new int[8];
    System.out.println("array3初始化："+Arrays.toString(array3));
    array3 = array2;
    System.out.println("array3赋值array2："+Arrays.toString(array3));
    array3= Arrays.copyOf(array1, 4);
    System.out.println("array3复制array1："+Arrays.toString(array3));
    int[] array4 = new int[5];
    array4 = Arrays.copyOfRange(array1, 1, 4);
    System.out.println("array4复制array1："+Arrays.toString(array4));
    Arrays.fill(array4,0,1,9);
    System.out.println("array4给剩下元素赋值："+Arrays.toString(array4));
    Arrays.sort(array4);
    System.out.println("array4排序："+Arrays.toString(array4));
    int a = Arrays.binarySearch(array1, 3);
    System.out.println("array1中元素3的位置："+a);
    Arrays.sort(array1,0,2);
    System.out.println("array1部分位置排序："+Arrays.toString(array1));
}

}
