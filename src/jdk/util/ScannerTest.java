package jdk.util;

import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args) {
        //控制台输出
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");  //以回车符做分隔符
        while(sc.hasNext()){
            System.out.println("输出："+sc.next());
        }
    }
}
