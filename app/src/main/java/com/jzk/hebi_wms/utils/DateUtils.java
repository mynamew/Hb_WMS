package com.jzk.hebi_wms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期格式转换
 *
 * @author jzk
 * create at: 2018/8/5 15:49
 */
public class DateUtils {
    public static String ms2Date(long _ms) {
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return format.format(date);
    }

    public static String ms2DateOnlyDay(long _ms) {
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    /**
     * 标准时间转换成时间戳
     *
     * @param _data
     * @return
     */
    public static long Date2ms(String _data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(_data);
            return date.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * yyyy-MM-dd  提取年 月  日
     *
     * @param dateStr
     * @return
     */
    public static int[] getYearAndMonthAndDay(String dateStr) {
        int[] dates = new int[3];
        String[] split = dateStr.split("-");
        dates[0] = Integer.parseInt(split[0]);
        dates[1] = Integer.parseInt(split[1]);
        dates[2] = Integer.parseInt(split[2]);
        return dates;
    }

    /**
     * 20180312 转换成 2018-07-12
     *
     * @param date
     * @return
     */
    public static int[] dateExchangeByServerLet(int date) {
        int[] dates=new int[3];
        dates[0]=date/10000;
        dates[1]=date%10000/100;
        dates[2]=date%10000%100;
        return dates;
    }
}
