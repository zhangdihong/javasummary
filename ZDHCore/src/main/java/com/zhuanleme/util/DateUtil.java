package com.zhuanleme.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Project: com.zhuanleme.util</p>
 * <p>Title: DateUtil.java</p>
 * <p/>
 * <p>Description: DateUtil </p>
 * <p/>
 * <p>Copyright: Copyright (c) 2015 </p>
 * <p/>
 * <p>Company: 华炜云商科技有限公司 www.hwtech.cc</p>
 *
 * @author zhangdihong
 * @version 1.0
 * @date 2015/9/7
 */
public class DateUtil {

    //日期时间类型格式
    private static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //日期类型格式
    private static String DATE_FORMAT = "yyyy-MM-dd";
    //时间类型格式
    private static String TIME_FORMAT = "HH:mm:ss";
    private static ThreadLocal<SimpleDateFormat> threadDateTime = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> threadDate = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> threadTime = new ThreadLocal<>();

    private static SimpleDateFormat DateTimeInstance() {
        SimpleDateFormat simpleDateFormat = threadDateTime.get();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            threadDateTime.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    private static SimpleDateFormat DateInstance() {
        SimpleDateFormat simpleDateFormat = threadDate.get();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            threadDate.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    private static SimpleDateFormat TimeInstance() {
        SimpleDateFormat simpleDateFormat = threadTime.get();
        if (null == simpleDateFormat) {
            simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
            threadTime.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String DateTime() {
        return DateTimeInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成字符串返回
     *
     * @param date
     * @return
     */
    public static String DateTime(Date date) {
        return DateTimeInstance().format(date);
    }

    /**
     * 将指定的字符串解析为时间类型
     */
    public static Date DateTime(String dateString) throws ParseException {
        return DateTimeInstance().parse(dateString);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String Date() {
        return DateInstance().format(new Date());
    }

    /**
     * 将日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String Date(Date date) {
        return DateInstance().format(date);
    }

    /**
     * 将字符串格式时间转换为时间类型
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date Date(String dateString) throws ParseException {
        return DateInstance().parse(dateString);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String Time() {
        return TimeInstance().format(new Date());
    }

    /**
     * 将指定时间转换为字符串
     *
     * @param date
     * @return
     */
    public static String Time(Date date) {
        return TimeInstance().format(date);
    }

    /**
     * 将指定字符串转换为时间类型
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date Time(String dateString) throws ParseException {
        return TimeInstance().parse(dateString);
    }

    /**
     * 在当前时间的基础上加或减去year年
     *
     * @param year
     * @return
     */
    public static Date year(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 指定的时间上加或减去几年
     *
     * @param date
     * @param year
     * @return
     */
    public static Date year(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 当前时间加上或者减去 几月
     *
     * @param month
     * @return
     */
    public static Date month(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 将指定时间加或者减去几月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date month(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 将当前时间减或加上几天
     *
     * @param day
     * @return
     */
    public static Date day(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 将指定时间加上或者减去几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date day(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.getTime();
        return calendar.getTime();
    }

    /**
     * 在当前时间的基础上加或减去几小时 支持浮点数
     *
     * @param hour
     * @return
     */
    public static Date hour(float hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, (int) (hour * 60));
        return calendar.getTime();
    }

    /**
     * 指定时间加或减几小时 支持浮点数
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date hour(Date date, float hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, (int) hour * 60);
        return calendar.getTime();
    }

    /**
     * 获取当前时间加或减几分钟
     *
     * @param minute
     * @return
     */
    public static Date Minute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 指定时间 加或减几分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date Minute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 是否是时间类型
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        try {
            DateTimeInstance().parse(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 两个时间的时间差
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long Subtract(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }

    /**
     * 时间差单位 秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long Subtract(String date1, String date2) {
        long rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = cha;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 时间差 分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMinute(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime());
        return (int) cha / (1000 * 60);
    }

    /**
     * 时间差单位 分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMinute(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 时间差 单位小时
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractHour(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime());
        return (int) cha / (1000 * 60 * 60);
    }

    /**
     * 时间差单位 小时
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractHour(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 时间差 单位天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractDay(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime());
        return (int) cha / (1000 * 60 * 60 * 24);
    }

    /**
     * 时间差单位 天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractDay(String date1, String date2) {
        int rs = 0;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int) cha / (60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 时间差 单位月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMonth(String date1, String date2) {
        int result;
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(DateInstance().parse(date1));
            calendar2.setTime(DateInstance().parse(date2));
            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH);
            int year2 = calendar2.get(Calendar.YEAR);
            int month2 = calendar2.get(Calendar.MONTH);
            if (year1 == year2) {
                result = month2 - month1;
            } else {
                result = 12 * (year2 - year1) + month2 - month1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return result;
    }

    /**
     * 时间差 单位月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMonth(Date date1, Date date2) {
        int result;
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int year2 = calendar2.get(Calendar.YEAR);
        int month2 = calendar2.get(Calendar.MONTH);
        if (year1 == year2) {
            result = month2 - month1;
        } else {
            result = 12 * (year2 - year1) + month2 - month1;
        }
        return result;
    }

    /**
     * 两个时间差 年
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractYear(String date1, String date2){
        int result;
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(DateInstance().parse(date1));
            calendar2.setTime(DateInstance().parse(date2));
            int year1 = calendar1.get(Calendar.YEAR);
            int year2 = calendar2.get(Calendar.YEAR);
            result = year2 - year1;
        } catch (ParseException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    /**
     * 时间差 年
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractYear(Date date1, Date date2){
        int result;
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        result = year2 - year1;
        return result;
    }

    /**
     * 返回一个格式化时间差
     * @param date1
     * @param date2
     * @return 几小时 ： 几分钟 ： 几秒钟
     */
    public static String SubStractTime(String date1, String date2){
        String result = "";
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);

            long sss = (end.getTime() - start.getTime()) / 1000;
            int hh = (int) sss / (60 * 60);
            int mm = (int) (sss - hh * 60 * 60) / (60);
            int ss = (int) (sss - hh * 60 * 60 - mm * 60);
            result = hh + ":" + mm + ":" + ss;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
