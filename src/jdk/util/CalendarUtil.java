package jdk.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @Title: CalendarUtil.java
 * @Copyright: Copyright (c) 2005
 * @Description: <br>
 *               <br>功能概述：新版JDK日志工具类                 
 *               <br>            
 * @Created on 2015年9月15日 下午7:28:45
 * @author yangkai
 */
public class CalendarUtil {

    public static void main(String[] args) {
        Calendar cal =  Calendar.getInstance();
        //calendar日历类转date日期类
        System.out.println(cal.getTime()+"---"+cal.getTimeInMillis());
        //取出年月日
        System.out.println(cal.YEAR+"---"+cal.MONTH+"---"+cal.DATE);
        //设置时间
        cal.set(2014,02,06,10,23,20);
        System.out.println(cal.getTime());
        //date日期类转calendar类
        cal.setTime(new Date());
        System.out.println(cal.getTime());
    }
}
