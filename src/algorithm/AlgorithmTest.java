package algorithm;

import org.junit.Test;

import algorithm.classic.SortAlgorithm;

/**
 * 算法测试
 * @Title: AlgorithmTest.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>
 * @Created on 2015年8月2日 下午2:34:05
 * @author yangkai
 */
public class AlgorithmTest {

    /**
     * 测试算法的效率；执行时间
     * @param datas
     */
    @Test
    public void testTime() {
        int[] datas = new int[10];
        for (int j = 1; j < 1000; j++) {
            datas = AlgorithmUtil.expandArray(datas, j);
        }
        System.out.println(datas.length);
        long startTime = System.currentTimeMillis();
        // AlgorithmClassic.sortBubble(datas);
        SortAlgorithm.sortSelect(datas);
        // AlgorithmClassic.sortInsert(datas);
        long endTime = System.currentTimeMillis();
        System.out.println("time：" + (endTime - startTime));
    }

    /**
     * 测试经典算法
     */
    @Test
    public  void testAlgorithm() {
        int[] datas = { 30, 5, 6, 0, 2, 3, 1,13,7,19,5,20 };
        //datas = AlgorithmClassic.sortSelect(datas);
        datas = SortAlgorithm.sortQuick(datas,0,datas.length-1);
        for (int i = 0; i < datas.length; i++)
            System.out.print(datas[i] + "、");
    }
}
