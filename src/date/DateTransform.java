package date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换工具
 * 
 * @Title: TransformDateUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 * @Created on 2014-4-4 下午06:50:51
 * @author 杨凯
 */
public class DateTransform {

    /**
     * 直接将当前时间只按日期(时间为0)作为mysql时间戳字段的条件 最终返回时间类型java.sql.Date
     */
    public void transformCurDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        java.sql.Date timePara = null;
        try {
            timePara = new java.sql.Date(new Date().getTime());
            System.out.println(timePara);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将java的当前时间转成指定格式(yyyy-MM-01 00:00:00")作为mysql时间戳字段的条件 最终返回时间类型java.sql.Date
     */
    public static void transformCurYearMon() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(new Date()).concat("-01 00:00:00");
        java.sql.Date timePara = null;
        try {
            timePara = new java.sql.Date(format.parse(time).getTime());
            System.out.println(timePara);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将java的当前时间转成Timestamp作为mysql时间戳字段的条件 最终返回时间类型java.sql.Timestamp
     */
    public static void testData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Timestamp date = java.sql.Timestamp.valueOf("2012-12-12 01:12:11");
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理当前时间只按日期(时间为0) 最终返回时间类型java.util.Date
     */
    public static void dataTest() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(new Date());
            Date date = format.parse(time.concat(" 00:00:00"));
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String转化为Timestamp
     */
    public static void StringTransformTimestamp() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        Timestamp ts = Timestamp.valueOf(time);
        System.out.println(ts);
        System.out.println(ts.getTime());
    }
    /**
     * String转化为Timestamp
     */
    public static void timestampStringTransform() {
        String time = "1395549540000";
                                // 1395549540
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.valueOf(time));
        String dateTime = df.format(date);
        Timestamp ts =Timestamp.valueOf(dateTime);
        System.out.println(ts);
        Integer tt = Integer.valueOf("1395549540")*1000;
        Long tts = Long.valueOf("1395549540".concat("000"));
        System.out.println(tts);
        
    }

    /**
     * Timestamp转化为String
     */
    public static void TimestampTransformString() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒
        Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
        String str = df.format(now);
    }

    /**
     * 将java的当前时间转成指定格式(yyyy-MM-01 00:00:00")作为mysql时间戳字段的条件 最终返回时间类型java.sql.Date
     */
    public static void transformCurYearMonDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(new Date()).concat("-01 00:00:00");
        String sql = "SELECT * from xxx WHERE time > '" + time + "'";
        // 可以在此执行上面的sql
    }

    public static void main(String[] args) {
        StringTransformTimestamp();
        System.out.println("--------------");
        timestampStringTransform();
    }
}
