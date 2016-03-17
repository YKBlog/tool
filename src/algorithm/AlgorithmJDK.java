package algorithm;

/**
 * 
 * @Title: AlgorithmJDK.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 *               功能概述： jdk 中的算法函数 <br>
 * @Created on 2015年9月4日 下午6:59:13
 * @author yangkai
 */
public class AlgorithmJDK {

    /**
     * 通过一个数学题展示java 递归的使用 <br>
     * 正规算法题：f(0)=1 f(1)=4 f(n+2)=2*f(n+1)+f(n) <br>
     * 推出f(n)=2*f(n-1)+f(n-2) 
     * @param n
     * @return
     */
    public static int fnRecursion(int n) {
        if (n == 0)
            return 1;
        else if (n == 1)
            return 4;
        else
            return 2 * fnRecursion(n - 1) + fnRecursion(n - 2);
    }
    /**
     * 通过一个数学题展示java 递归的使用 <br>
     * 变相算法题：f(20)=1 f(21)=4 f(n+2)=2*f(n+1)+f(n) <br>
     * 推出f(n)=f(n+2)-2*f(n+1)
     * @param n
     * @return
     */
    public static int fnRecursionCovert(int n) {
        if (n == 20)
            return 1;
        else if (n == 21)
            return 4;
        else
            return fnRecursionCovert(n+2)-2*fnRecursionCovert(n+1);
    }

    /**
     * 递归输出 乘法口诀
     */
    public static void printMulitiplyTable() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < i + 1; j++) {
                System.out.print(i + "*" + j + "=" + i * j + "    ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        System.out.println(fnRecursionCovert(10));
    }
}
