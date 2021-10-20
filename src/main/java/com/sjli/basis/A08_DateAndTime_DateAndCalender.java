package com.sjli.basis;

/**
 * @Classname A08_DateAndTime_DateAndCalender
 * @Description TODO
 * @Date 2021/8/19 17:53
 * @Created by steven
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间有两种概念，一种是不带日期的时间，例如，12:30:59。
 * 另一种是带日期的时间，例如，2020-1-1 20:21:59，只有这种带日期的时间能唯一确定某个时刻，
 * 不带日期的时间是无法确定一个唯一时刻的。
 *
 * 时区:
 * 一种是以GMT或者UTC加时区偏移表示，例如：GMT+08:00或者UTC+08:00表示东八区。
 *
 * GMT和UTC可以认为基本是等价的，只是UTC使用更精确的原子钟计时，每隔几年会有一个闰秒，
 * 我们在开发程序的时候可以忽略两者的误差，因为计算机的时钟在联网的时候会自动与时间服务器同步时间。
 *
 *另一种是缩写，例如，CST表示China Standard Time，也就是中国标准时间。
 * 但是CST也可以表示美国中部时间Central Standard Time USA，因此，缩写容易产生混淆，我们尽量不要使用缩写。
 *
 *
 * 一套定义在java.util这个包里面，主要包括Date、Calendar和TimeZone这几个类；
 * 一套新的API是在Java 8引入的，定义在java.time这个包里面，
 * 主要包括LocalDateTime、ZonedDateTime、ZoneId等。
 * 为什么会有新旧两套API呢？因为历史遗留原因，旧的API存在很多问题，所以引入了新的API。
 */
public class A08_DateAndTime_DateAndCalender {
    public static void main(String[] args) throws ParseException {
        // 获取当前时间:
        Date date = new Date();
        //注意getYear()返回的年份必须加上1900，getMonth()返回的月份是0~11分别表示1~12月，
        // 所以要加1，而getDate()返回的日期范围是1~31，又不能加1。
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());

        //可以使用SimpleDateFormat对一个Date进行转换。它用预定义的字符串表示格式化：
        //
        //yyyy：年
        //MM：月
        //dd: 日
        //HH: 小时
        //mm: 分钟
        //ss: 秒
        var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("E MMM dd, yyyy");
        System.out.println(sdf.format(date));
        //Date对象有几个严重的问题：它不能转换时区，除了toGMTString()可以按GMT+0:00输出外，
        // Date总是以当前计算机系统的默认时区为基础进行输出。此外，我们也很难对日期和时间进行加减，
        // 计算两个日期相差多少天，计算某个月第一个星期一的日期等。

        //Calendar可以用于获取并设置年、月、日、时、分、秒，它和Date比，
        // 主要多了一个可以做简单的日期和时间运算的功能。
        // 获取当前时间:
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 1 + c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int w = c.get(Calendar.DAY_OF_WEEK);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);
        //注意到Calendar获取年月日这些信息变成了get(int field)，返回的年份不必转换，
        // 返回的月份仍然要加1，返回的星期要特别注意，1~7分别表示周日，周一，……，周六。

        //Calendar只有一种方式获取，即Calendar.getInstance()，而且一获取到就是当前时间。
        // 如果我们想给它设置成特定的一个日期和时间，就必须先清除所有字段：
        // 清除所有:
        Calendar C2 = Calendar.getInstance();
        C2.clear();
        // 设置2019年:
        C2.set(Calendar.YEAR, 2019);
        // 设置9月:注意8表示9月:
        C2.set(Calendar.MONTH, 8);
        // 设置2日:
        C2.set(Calendar.DATE, 2);
        // 设置时间:
        C2.set(Calendar.HOUR_OF_DAY, 21);
        C2.set(Calendar.MINUTE, 22);
        C2.set(Calendar.SECOND, 23);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(C2.getTime()));
        // 2019-09-02 21:22:23

        //TimeZone
        //Calendar和Date相比，它提供了时区转换的功能。时区用TimeZone对象表示：
        TimeZone tzDefault = TimeZone.getDefault(); // 当前时区
        TimeZone tzGMT9 = TimeZone.getTimeZone("GMT+09:00"); // GMT+9:00时区
        TimeZone tzNY = TimeZone.getTimeZone("America/New_York"); // 纽约时区
        System.out.println(tzDefault.getID()); // Asia/Shanghai
        System.out.println(tzGMT9.getID()); // GMT+09:00
        System.out.println(tzNY.getID()); // America/New_York

        // 当前时间:
        Calendar c3 = Calendar.getInstance();
        // 清除所有:
        c3.clear();
        // 设置为北京时区:
        c3.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        // 设置年月日时分秒:
        c3.set(2019, 10 /* 11月 */, 20, 8, 15, 0);
        // 显示时间:
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf.format(c3.getTime()));
        // 2019-11-19 19:15:00
        
        //Calendar也可以对日期和时间进行简单的加减：
        // 当前时间:
        Calendar c4 = Calendar.getInstance();
        // 清除所有:
        c4.clear();
        // 设置年月日时分秒:
        c4.set(2019, 10 /* 11月 */, 20, 8, 15, 0);
        // 加5天并减去2小时:
        c4.add(Calendar.DAY_OF_MONTH, 5);
        c4.add(Calendar.HOUR_OF_DAY, -2);
        // 显示时间:
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d4 = c4.getTime();
        System.out.println(sdf.format(d4));
        // 2019-11-25 6:15:00

        //使用字符串初始化Date

        String dateString ="2018-02-23";
        Date date5= new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        //获取前一日（正确方式）
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date day = sdf1.parse("2017-01-01");

        long ms1 = day.getTime() - 1*24*3600*1000L;
        Date prevDay = new Date(ms1);
        System.out.println(sdf1.format(prevDay));

        //两个日期比大小
        if(date5.before(date)) System.out.println("date5 smaller than date");
        if(date5.after(date)) System.out.println("date5 bigger than date");


        sdf=new SimpleDateFormat("yyyy-MM-dd");
        date5=sdf.parse(sdf.format(date5));
        date=sdf.parse(sdf.format(date));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date5);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

    }

}
