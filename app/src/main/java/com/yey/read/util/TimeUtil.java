package com.yey.read.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunnie on 15/6/1.
 */
public class TimeUtil {
    //获取当前时间yyyy-MM-dd HH:MM
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH");
        Date date = new Date();
        return format.format(date);
    }

    public static String getCurrentTimeYMD() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    //获取当前时间yyyy-MM-dd HH:MM
    public static String getYMDHMS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }

    public static String getYMDHM() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return format.format(date);
    }

    public static String getYMDHMSS() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }

    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        return format.format(new Date(time));
    }

    public static String getMoreTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }


    public static String getYMDTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }


    public static String getHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }


    public static String getChatTime(String time) {

        String result = "";
        try {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(time);
            long timesamp = date.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            result = "";
            Date today = new Date(System.currentTimeMillis());
            Date otherDay = new Date(timesamp);
            int temp = Integer.parseInt(sdf.format(today))
                    - Integer.parseInt(sdf.format(otherDay));

            switch (temp) {
                case 0:
                    result = "今天 " + getHourAndMin(timesamp);
                    break;
                case 1:
                    result = "昨天 " + getHourAndMin(timesamp);
                    break;
                case 2:
                    result = "前天 " + getHourAndMin(timesamp);
                    break;

                default:
                    result = getYMDhm(time);
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getChatTime2(String time) {

        String result = "";
        try {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(time);
            long timesamp = date.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            result = "";
            Date today = new Date(System.currentTimeMillis());
            Date otherDay = new Date(timesamp);
            int temp = Integer.parseInt(sdf.format(today))
                    - Integer.parseInt(sdf.format(otherDay));

            switch (temp) {
                case 0:
                    result = "今天 " + getHourAndMin(timesamp);
                    break;
                case 1:
                    result = "昨天 " + getHourAndMin(timesamp);
                    break;
                case 2:
                    result = "前天 " + getHourAndMin(timesamp);
                    break;

                default:
                    result = time;
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getRecentTime(String time) {

        String result = "";
        try {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(time);
            long timesamp = date.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            result = "";
            Date today = new Date(System.currentTimeMillis());
            Date otherDay = new Date(timesamp);
            int temp = Integer.parseInt(sdf.format(today))
                    - Integer.parseInt(sdf.format(otherDay));

            switch (temp) {
                case 0:
                    result = "今天 " + getHourAndMin(timesamp);
                    break;
                case 1:
                    result = "昨天 " + getHourAndMin(timesamp);
                    break;
                case 2:
                    result = "前天 " + getHourAndMin(timesamp);
                    break;

                default:
                    result = getYMDTime(timesamp);
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getChatTime(long timesamp) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天 " + getHourAndMin(timesamp);
                break;
            case 1:
                result = "昨天 " + getHourAndMin(timesamp);
                break;
            case 2:
                result = "前天 " + getHourAndMin(timesamp);
                break;

            default:
                result = getTime(timesamp);
                break;
        }

        return result;
    }


    public static String getYMD(String date) {
        try {
            Date now = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            now = f.parse(date);

            SimpleDateFormat f2 = new SimpleDateFormat("yyyy年MM月dd日");
            String nowtime = f2.format(now);
            return nowtime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getYMDhm(String date) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = f.parse(date);
            SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String nowtime = f2.format(now);
            return nowtime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getGrowRecentTime(String time) {

        String result = "";
        try {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(time);
            long timesamp = date.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd");
            result = "";
            Date today = new Date(System.currentTimeMillis());
            Date otherDay = new Date(timesamp);
            int temp = Integer.parseInt(sdf.format(today))
                    - Integer.parseInt(sdf.format(otherDay));

            switch (temp) {
                case 0:
                    result = "今天";
                    break;
                default:
                    result = getGrowYMDTime(timesamp);
                    break;
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getGrowYMDTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String growtime = format.format(new Date(time));
        return growtime.substring(growtime.indexOf("-") + 1).replace("-", "月");
    }

    public static String getGrowDayYMDTime(String time) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = f.parse(time);
            SimpleDateFormat f2 = new SimpleDateFormat("HH:mm");
            String nowtime = f2.format(now);
            return nowtime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
