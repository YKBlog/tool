package file.txt;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Title: TxtCompareUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>txt文件对比工具
 *               <br> list集合比较工具
 * @Created on 2014-4-4 下午06:50:28
 * @author 杨凯
 */
public class TxtCompare {

    /**
     * 使用两个list包含来对比
     * 
     * @param input1
     * @param input2
     * @param output1
     * @param output2
     */
    public static void compareTxt(String input1, String input2, String output1, String output2) {

        List<String> listInput1 = TxtReadAndWrite.readTxt(input1);
        List<String> listInput2 = TxtReadAndWrite.readTxt(input2);
        for (String str : listInput1) {
            if (listInput2.contains(StringUtils.upperCase(str)) || listInput2.contains(StringUtils.lowerCase(str)) || listInput2.contains((str))) {
                TxtReadAndWrite.writerTXT(str, output1, true);
            } else {
                TxtReadAndWrite.writerTXT(str, output2, true);
            }
        }
    }

    /**
     * 求两个list的交集 通过求交集的方法找出两个txt的共同部分
     * 
     * @param input1
     * @param input2
     * @param output1
     * @param output2
     */
    public static void interseTxt(String input1, String input2, String output1, String output2) {
        List<String> listInput1 = TxtReadAndWrite.readTxt(input1);
        List<String> listInput2 = TxtReadAndWrite.readTxt(input2);
        if (listInput2.retainAll(listInput1)) {
            System.out.println(listInput2);
            // TxtReadAndWrite.writerTXT(listInput2, output1, true);
        } else {
            // TxtReadAndWrite.writerTXT(listInput2, output2, true);
        }
    }

    /**
     * 求两个list的并集 通过求两个list的并集来合并两个txt
     * 
     * @param input1
     * @param input2
     * @param output1
     * @param output2
     */
    public static void unionTxt(String input1, String input2, String output1, String output2) {
        List<String> listInput1 = TxtReadAndWrite.readTxt(input1);
        List<String> listInput2 = TxtReadAndWrite.readTxt(input2);
        listInput2.removeAll(listInput1); // 移除相同的
        listInput2.addAll(listInput1); // 放入不同的
        System.out.println(listInput2);
    }

    /**
     * 获取两个集合的不同元素
     * 
     * @param collmax
     * @param collmin
     * @return
     */
    @SuppressWarnings( { "unchecked" })
    public static Collection getDiffent(Collection collmax, Collection collmin) {
        // 使用LinkeList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        // 先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        // 直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) == null) {
                csReturn.add(object);
            } else {
                map.put(object, 2);
            }
        }
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                csReturn.add(entry.getKey());
            }
        }
        return csReturn;
    }

    /**
     * 获取两个集合的不同元素,去除重复
     * 
     * @param collmax
     * @param collmin
     * @return
     */
    @SuppressWarnings( {"unchecked"})
    public static Collection getDiffentNoDuplicate(Collection collmax, Collection collmin) {
        return new HashSet(getDiffent(collmax, collmin));
    }
    
    public static void main(String[] args) {
        unionTxt("e:/test/input1.txt", "e:/test/input2.txt", "e:/test/output1.txt", "e:/test/output1.txt");
    }
}
