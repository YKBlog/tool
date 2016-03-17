package algorithm.classic;

import algorithm.AlgorithmUtil;

/**
 * 
 * @Title: BubbleSort.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 *               JAVA六大经典算法<br>
 *               冒泡、选择、快速、递归、希尔、堆
 * @Created on 2015年6月29日 下午12:48:14
 * @author yangkai
 */
public class SortAlgorithm {

    /**
     * 冒泡排序
     * 
     * @return
     */
    public static int[] sortBubble(int[] datas) {
        for (int i = 0; i < datas.length - 1; i++) {
            for (int j = 0; j < datas.length - 1 - i; j++) {
                if (datas[j] > datas[j + 1])
                    AlgorithmUtil.swap(datas, j, j + 1);
            }
        }
        return datas;
    }

    /**
     * 选择排序
     * 
     * @return
     */
    public static int[] sortSelect(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            int index = i;
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] < datas[index])
                    index = j;
            }
            if (i != index)
                AlgorithmUtil.swap(datas, i, index);
        }
        return datas;
    }

    /**
     * 插入排序法
     * 
     * @param datas
     */
    public static int[] sortInsert(int[] datas) {
        for (int i = 1; i < datas.length; i++) {
            int j = i - 1;
            AlgorithmUtil.temp = datas[i];
            for (; j >= 0 && AlgorithmUtil.temp < datas[j]; j--) {
                datas[j + 1] = datas[j];
            }
            datas[j + 1] = AlgorithmUtil.temp;
        }
        return datas;
    }

    /**
     * 快速排序；分割数组
     * 
     * @param datas
     */
    public static int QuickPartition(int[] datas, int left, int right) {
        int pivot = datas[left];
        while (left < right) {
            while (left < right && datas[right] >= pivot) {
                --right;
            }
            if (left != right) { // 前后指针相同不交换
                datas[left] = datas[right]; // 将比枢轴小的元素移到低端，此时right位相当于空，等待低位比pivot  key大的数补上
                while (left < right && datas[left] <= pivot) {
                    ++left;
                }
                datas[right] = datas[left]; // 将比枢轴大的元素移到高端，此时left位相当于空，等待高位比pivot  key小的数补上
            }
        }
        datas[left] = pivot; // 当left == right，完成一趟快速排序，此时left位相当于空，等待pivot  key补上
        return left;
    }

    /**
     * 快速排序；递归返回数组
     * 
     * @param datas
     */
    public static int[] sortQuick(int[] datas, int left, int right) {
        if (left < right) {
            int data = QuickPartition(datas, left, right);
            sortQuick(datas, left, data - 1);
            sortQuick(datas, data + 1, right);
        }
        return datas;
    }

}
