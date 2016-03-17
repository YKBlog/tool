package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * @Title: DateUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 * <br>
 *               时间工具类
 * @Created on 2015年4月9日 下午6:00:26
 * @author yangkai
 */
public class DateUtil {

    /**
     * string 转 date
     * 
     * @param time
     * @param format
     * @return
     */
    public static Date stringToDate(String time, String format) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            return formater.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * date 转 string
     * 
     * @param time
     * @param format
     * @return
     */
    public static String dateToString(Date time, String format) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            return formater.format(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 比较传入时间是否在规定时间内
     * 
     * @param currentDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean compareDate(Date currentDate, Date startDate, Date endDate) {
        long sl = currentDate.getTime() - startDate.getTime();
        long el = endDate.getTime() - currentDate.getTime();
        if (sl >= 0 && el > 0) {
            return true;
        }
        return false;
    }

    /**
     * 比较传入时间在规定时间前后中
     * 
     * @param currentDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static int compareDate2(Date currentDate, Date startDate, Date endDate) {
        int mark = 0;
        long sl = currentDate.getTime() - startDate.getTime();
        long el = endDate.getTime() - currentDate.getTime();
        if (sl >= 0 && el >= 0)
            mark = 1; // 内
        if (sl < 0)
            mark = 2; // 前
        if (el < 0)
            mark = 3; // 后
        return mark;
    }

    /**
     * 得到明天
     * 
     * @author yangkai
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(cal.DATE, 1);
        return cal.getTime();
    }

    /**
     * calendar
     */
    public static SimpleDateFormat monthFormatter = new SimpleDateFormat("yyyy-MM");

    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 指定时间几天之后
     * 
     * @param d
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date addDate(Date d, long day) {

        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);

    }

    /**
     * 获取当前时间"yy-MM-dd"
     * 
     * @return
     */
    public static String getCurrenDate() {
        String date = dateFormatter.format(new Date());
        return date;
    }

    /**
     * 根据时间获取时间"yy-MM-dd"
     * 
     * @return
     */
    public static String getCurrenDate(Date time) {
        String date = dateFormatter.format(new Date());
        return date;
    }

    /**
     * 获取当前时间 yyyy-MM
     * */
    public static String getCurrenMonth(Date date) {
        String month = monthFormatter.format(date);
        return month;
    }

    /**
     * 获取当前时间下一个月 yyyy-MM
     * */
    public static String getNextMonth(Date date, int month) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + month);
        String months = monthFormatter.format(cl.getTime());
        return months;
    }

    /**
     * 获取当前时间 yyyy-MM-dd hh-mm-ss
     * */
    public static String getDate(Date date) {
        String months = dateTimeFormatter.format(date);
        return months;
    }

    /**
     * 获取昨天；返回字符串 yyyy-MM-dd
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return getCurrenDate(cal.getTime());
    }

    /**
     * 获取昨天；返回date yyyy-MM-dd
     */
    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 获取每个月第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取每个月最后一天
     * 
     * @param date
     * @return
     */
    public static Date getLastlyDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 获取星期
     * 
     * @author yangkai
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期六", "星期五", "星期四", "星期三", "星期二", "星期一" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 判读时间，返回boolean
     * 
     * @author yangkai
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean judgeDate(String startDate, String endDate) {

        boolean status = Boolean.FALSE;
        Date date = new Date();
        Date sdate = stringToDate(startDate, "yyyy-MM-dd HH:mm:ss");
        Date edate = stringToDate(endDate, "yyyy-MM-dd HH:mm:ss");
        if (date.after(sdate) && date.before(edate)) {
            status = Boolean.TRUE;
        }
        return status;
    }

    /**
     * 目标时间减去天数
     * 
     * @param target
     * @param days
     * @return
     */
    public Date cutDays(Date target, int days) {
        long msPerDay = 1000 * 60 * 60 * 24; // 一天的毫秒数
        long msTarget = target.getTime(); // 返回从一个特定的日期（1970。。）到现在经过的毫秒数。
        long msSum = msTarget - (msPerDay * days);
        Date result = new Date();
        result.setTime(msSum); // 根据毫秒设置日期
        return result;
    }

    /**
     * 目标时间加上天数
     * 
     * @param target
     * @param days
     * @return
     */
    public Date addDays(Date target, int days) {
        long msPerDay = 1000 * 60 * 60 * 24; // 一天的毫秒数
        long msTarget = target.getTime(); // 返回从一个特定的日期（1970。。）到现在经过的毫秒数。
        long msSum = msTarget + (msPerDay * days);
        Date result = new Date();
        result.setTime(msSum); // 根据毫秒设置日期
        return result;
    }

    public static String timestampConvertDate(long timestamp) {
        Date date = new Date(timestamp);
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static void main(String[] args) {
        // System.out.println(judgeDate("2015-03-10 00:00:00","2015-03-11 00:00:00"));
        System.out.println(timestampConvertDate(1438331036000L));
    }
}
