package jdk.util;
/**
 * 
 * @Title: MathUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>功能概述：JDK  math 数学运算工具类的用法              
 *               <br>            
 * @Created on 2015年9月15日 下午5:22:38
 * @author yangkai
 */
public class MathUtil {

    public static void main(String[] args) {
        /**
         * 取整运算
         */
        //向下取整，返回小于目标数的最大正数
        System.out.println("floor：地板，即向下取整【正数】"+Math.floor(1.2)+"---【负数】"+Math.floor(-2.2)); 
        //向上取整，返回大于目标数的最小正数
        System.out.println("ceil：地板，即向上取整【正数】"+Math.ceil(1.2)+"---【负数】"+Math.ceil(-2.2)); 
        //四舍五入取整
         System.out.println("round：四舍五入 "+Math.round(1.2));  
        /**
         * 乘方、开方、指数运算
         */
         //开平方根
         System.out.println("sqrt：开平方根  "+Math.sqrt(8.6));
         //开立方根
         System.out.println("cbrt：开立方根  "+Math.cbrt(8.6));
         //返回欧拉数 e的n次幂
         System.out.println("exp：e的n次幂  "+Math.exp(8.6));
         //乘方
         System.out.println("pow：乘方  "+Math.pow(3,2));
         //计算自然对数
         System.out.println("log：自然对数  "+Math.log(2));
         //计算底数为10对数
         System.out.println("log10：自然对数  "+Math.log10(2));
         //返回参数与1之和的自然对数
         System.out.println("log1p：自然对数  "+Math.log1p(2));
         //按照IEEE 754标准  对两个参数进行余数运算
         System.out.println("IEEEremainder：取余数运算  "+Math.IEEEremainder(3,2));
         /**
          * 符号、大小相关运算
          */
       //绝对值
         System.out.println("abs：绝对值  "+Math.abs(-3.3));
         //计算最大值
         System.out.println("max：最大值  "+Math.max(1.2, 2.3));
         //计算最小值
         System.out.println("min：最小值  "+Math.min(1.2, 2.3));
         //随机数
         System.out.println("random：随机数  "+Math.random());
    }
}
