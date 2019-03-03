package com.good.comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month0 = cal.get(Calendar.MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份0: " + month0);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow); // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }

    /**
     * 获取 java.util.Date 字符串转时间
     * 
     * @return
     */
    public static java.util.Date paserDate(String datetime) {
        DateFormat df = DateFormat.getDateInstance();
        try {
            return df.parse(datetime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date paserDate(String datetime, String partten) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(partten);
            Date d = sdf.parse(datetime);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间型字符串转成partten形式的字符串
     * 
     * @param datetime
     * @param partten
     * @return
     */
    public static String paserString(String datetime, String partten) {
        try {
            DateFormat df = DateFormat.getDateInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(partten);
            String dateStr = sdf.format(df.parse(datetime));// 指标日期
            return dateStr;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date paserStringToDate(String datetime, String partten) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(partten);
            Date dateStr = sdf.parse(datetime);
            return dateStr;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date paserDate(Date date, String partten) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(partten);
            Date date1 = sdf.parse(sdf.format(date));
            return date1;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String paserString(Date date, String partten) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(partten);
        return sdf.format(date);
    }

    public static int dateDiff(Date startDate, Date endDate) {
        long times = endDate.getTime() - startDate.getTime();
        int dnum = (int) (times / 1000 / 60 / 60 / 24);
        return dnum;
    }

    public static Date getAllocateEndDate() {
        DateFormat df = DateFormat.getDateInstance();
        try {
            return df.parse("9999-12-31");
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getNearbyMonth(String month, int i) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date mydate = formatter.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mydate);
        calendar.add(Calendar.MONTH, i);
        return formatter.format(calendar.getTime());

    }

    /**
     * 取末年某月的最后一天
     * 
     * @param year
     * @param month
     *            - 1
     * @return
     */
    public static int getLastDayOfMonth(String date) {
        Calendar cal = Calendar.getInstance();
        // cal.set(Calendar.YEAR, year);
        // cal.set(Calendar.MONTH, 1);
        cal.setTime(paserDate(date));
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
