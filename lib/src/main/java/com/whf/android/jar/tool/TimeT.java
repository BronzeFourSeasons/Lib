package com.whf.android.jar.tool;

import com.whf.android.jar.LogT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * time tools
 *
 * @author wang.hai.fang
 * @since 2.5.0
 */
public final class TimeT {

    /* The last time you clicked */
    private static long exitTime = 0;


    /**
     * Prevent multiple clicks in one second
     *
     * @param time 1000:A second
     * @return true:Not even click
     */
    public static boolean onButtonFrequency(int time) {
        if ((System.currentTimeMillis() - exitTime) > time) {
            exitTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get current timestamp
     *
     * @return 1473585692339:2016-09-11 17:21:32
     */
    public static long newTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * present time
     *
     * @return 1473585692339:2016-09-11 17:21:32
     */
    public static String newTime() {
        Long l = newTimeStamp();
        return formatData(l);
    }

    /**
     * Time cut is converted to time
     *
     * @param timeStamp 1414994 617
     * @return yyyy-MM-dd
     */
    public static String formatData(long timeStamp) {
        Double s = Math.pow(10, 10);
        String dataFormat = "yyyy-MM-dd HH:mm:ss";
        if (timeStamp == 0) {
            return "";
        } else if (timeStamp > s && timeStamp < s * 10) {
            timeStamp = timeStamp * 1000;
        }
        Date d = new Date(timeStamp);
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        String result = format.format(d);
        LogT.i("Time:" + result);
        return result;
    }

    /**
     * Year
     *
     * @return 2017
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Month
     *
     * @return 0
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    /**
     * Month
     *
     * @return 1
     */
    public static int getYue() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * Day
     *
     * @return 12
     */
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Hour
     *
     * @return 13
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Minute
     *
     * @return 00
     */
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

}

