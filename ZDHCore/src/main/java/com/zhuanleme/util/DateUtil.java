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
    private static String DATE_FORMAT     = "yyyy-MM-dd";

    //时间类型格式
    private static String TIME_FORMAT     = "HH:mm:ss";

    private static ThreadLocal<SimpleDateFormat> threadDateTime = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> threadDate     = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> threadTime     = new ThreadLocal<>();

    private static SimpleDateFormat DateTimeInstance(){
        SimpleDateFormat simpleDateFormat = threadDateTime.get();
        if(null == simpleDateFormat){
            simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            threadDateTime.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    private static SimpleDateFormat DateInstance(){
        SimpleDateFormat simpleDateFormat = threadDate.get();
        if(null == simpleDateFormat){
            simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            threadDate.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }
    private static SimpleDateFormat TimeInstance(){
        SimpleDateFormat simpleDateFormat = threadTime.get();
        if(null == simpleDateFormat){
            simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
            threadTime.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static String DateTime(){
        return DateTimeInstance().format(new Date());
    }

    /**
     * 将指定的时间格式化成字符串返回
     * @param date
     * @return
     */
    public static String DateTime(Date date){
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
     * @return
     */
    public static String Date(){
        return DateInstance().format(new Date());
    }

    /**
     * 将日期转换为字符串
     * @param date
     * @return
     */
    public static String Date(Date date){
        return DateInstance().format(date);
    }

    /**
     * 将字符串格式时间转换为时间类型
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date Date(String dateString) throws ParseException {
        return DateInstance().parse(dateString);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String Time(){
        return TimeInstance().format(new Date());
    }

    /**
     *将指定时间转换为字符串
     * @param date
     * @return
     */
    public static String Time(Date date){
        return TimeInstance().format(date);
    }

    /**
     * 将指定字符串转换为时间类型
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date Time(String dateString) throws ParseException {
        return TimeInstance().parse(dateString);
    }

    /**
     * 在当前时间的基础上加或减去year年
     * @param year
     * @return
     */
    public static Date year(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,year);
        return calendar.getTime();
    }

    /**
     * 指定的时间上加或减去几年
     * @param date
     * @param year
     * @return
     */
    public static Date year(Date date, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 当前时间加上或者减去 几月
     * @param month
     * @return
     */
    public static Date month(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 将指定时间加或者减去几月
     * @param date
     * @param month
     * @return
     */
    public static Date month(Date date, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 将当前时间减或加上几天
     * @param day
     * @return
     */
    public static Date day(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 将指定时间加上或者减去几天
     * @param date
     * @param day
     * @return
     */
    public static Date day(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        calendar.getTime();
        return calendar.getTime();
    }

    /**
     * 在当前时间的基础上加或减去几小时 支持浮点数
     * @param hour
     * @return
     */
    public static Date hour(float hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, (int) (hour * 60));
        return calendar.getTime();
    }

    /**
     * 指定时间加或减几小时 支持浮点数
     * @param date
     * @param hour
     * @return
     */
    public static Date hour(Date date, float hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, (int)hour * 60);
        return calendar.getTime();
    }

    /**
     * 获取当前时间加或减几分钟
     * @param minute
     * @return
     */
    public static Date  Minute(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 指定时间 加或减几分钟
     * @param date
     * @param minute
     * @return
     */
    public static Date  Minute(Date date , int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 是否是时间类型
     * @param date
     * @return
     */
    public static boolean isDate(String date){
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
     * @param date1
     * @param date2
     * @return
     */
    public static long Subtract(Date date1, Date date2){
        long cha = (date2.getTime() - date1.getTime()) / 1000;
        return cha;
    }

    /**
     * 时间差单位 秒
     * @param date1
     * @param date2
     * @return
     */
    public static long Subtract(String date1, String date2){
        long rs = 0 ;
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
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMinute(Date date1, Date date2){
        long cha = (date2.getTime() - date1.getTime()) ;
        return (int)cha / (1000 * 60);
    }
    /**
     * 时间差单位 分钟
     * @param date1
     * @param date2
     * @return
     */
    public static int SubtractMinute(String date1, String date2){
        int rs = 0 ;
        try {
            Date start = DateTimeInstance().parse(date1);
            Date end = DateTimeInstance().parse(date2);
            long cha = (end.getTime() - start.getTime()) / 1000;
            rs = (int)cha / (60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
}
