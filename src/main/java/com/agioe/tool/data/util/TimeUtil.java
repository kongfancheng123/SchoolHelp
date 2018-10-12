package com.agioe.tool.data.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author yshen
 * @since 2018/3/29
 */
public class TimeUtil {


    /**
     * 时间戳转换为<code>Date</code>
     *
     * @param timestamp
     * @return
     */
    public static Date getDateFromTimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }


    /**
     * 时间戳转换为数据库的<code>Timestamp</code>
     *
     * @param timestamp
     * @return
     */
    public static Timestamp getTimestampFromTimestamp(long timestamp) {
        return new Timestamp(timestamp * 1000);
    }


    /**
     * 时间格式化
     *
     * @return 返回格式化后的时间字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 根据传入的年月日创建一个日期对象。
     *
     * @param year  年份。
     * @param month 月份。
     * @param day   天。
     * @return 一个日期对象。
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

//    /**
//     * 获取两个日期间所有月份
//     *
//     * @param start 起始月
//     * @param end   结束月
//     * @return
//     * @throws ParseException
//     */
//    public static List<String> getBetweenMonths(String start, String end) throws ParseException {
//        List<String> result = new ArrayList<>();
//        //格式化为年月
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//
//        Calendar min = Calendar.getInstance();
//        Calendar max = Calendar.getInstance();
//
//        min.setTime(sdf.parse(start));
//        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
//
//        max.setTime(sdf.parse(end));
//        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
//
//        Calendar curr = min;
//        while (curr.before(max)) {
//            result.add(sdf.format(curr.getTime()));
//            curr.add(Calendar.MONTH, 1);
//        }
//        return result;
//    }

//    /**
//     * 获取两个日期之间的日期
//     * 包含开始结束时间
//     *
//     * @param start 开始日期
//     * @param end   结束日期
//     * @return 日期集合
//     */
//    public static List<Date> getBetweenDates(Date start, Date end) {
//        List<Date> result = new ArrayList<>();
//        result.add(start);
//        Calendar tempStart = Calendar.getInstance();
//        tempStart.setTime(start);
//        tempStart.add(Calendar.DAY_OF_YEAR, 1);
//
//        Calendar tempEnd = Calendar.getInstance();
//        tempEnd.setTime(end);
//        while (tempStart.before(tempEnd)) {
//            result.add(tempStart.getTime());
//            tempStart.add(Calendar.DAY_OF_YEAR, 1);
//        }
//        result.add(end);
//        return result;
//    }


//    /**
//     * 时间格式化
//     * 传入格式2017-08
//     * 输出格式2017年8月
//     *
//     * @return 返回格式化后的时间字符串
//     */
//    public static String formatMonth(String mothDate) {
//        String result;
//        String[] split = mothDate.split("-");
//        if (split[1].substring(0, 1).equals("0")) {
//            result = split[0] + "年" + split[1].substring(1, 2) + "月";
//        } else {
//            result = split[0] + "年" + split[1] + "月";
//        }
//        return result;
//    }

    /**
     * 字符串转为日期
     */
    public static Date stringToDate(String str) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        if (str != null) {
            Date date = simpleDateFormat.parse(str);
            return date;
        } else {
            Date date = new Date();
            return date;
        }


    }

}
