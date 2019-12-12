package java.CSP.csp201712;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class testScanner {
    public static void main(String[] args) throws ParseException {
     //   Scanner scanner = new Scanner(System.in);
//        String x = scanner.next();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        String str = "201810060113";
//        Date now = new Date();
        Date now = df.parse(str);
      //  DateFormat df1 = DateFormat.getDateInstance();

        //long nowTime = now.getTime();
        //System.out.println("日期是：" + now +" " + "秒数是："+nowTime);
        //Date nowlast = new Date(nowTime);
        //System.out.println(week -1);
        System.out.println(now);

        Date now1 = new Date();
//        System.out.println(now1);
//        getMonthFromDate(now1);
//        System.out.println(getDayNumFromDate(now));
        System.out.println(now1);
        getHoursFromDate(now1);

        System.out.println(addZero(getDayNumFromDate(now)));
    }


    //获取一分钟后的日期
    public static Date getOneMinuteAferDate(Date date){
        long endSecond = date.getTime() + 60 * 1000;
//        DateFormat df = new SimpleDateFormat("yyyymmddHHMM");
        return new Date(endSecond);
    }
    //获取星期几
    public static String getWeekFromDate(Date date) {
        Calendar cal = Calendar.getInstance(); //获取一个日历
        Date now = new Date();
        cal.setTime(now);
        Integer week = cal.get(Calendar.DAY_OF_WEEK);
        return  week.toString();
    }
    //根据日期返回日号 20160601 -->1； 20161011  -->11
    public static String getDayNumFromDate(Date date) {
        Integer dayNum = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dayNum = cal.get(Calendar.DAY_OF_MONTH);
        return dayNum.toString();
    }
    //根据日期返回月号
    public static String getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer month = cal.get(Calendar.MONTH) + 1;
        return month.toString();
    }
    //根据日期返回分钟数
    public static String getMinutesFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer minutes = cal.get(Calendar.MINUTE);
        return minutes.toString();
    }
    //根据日期返回小时数
    public static String getHoursFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer hours = cal.get(Calendar.HOUR);
        return hours.toString();
    }
    //给月份,日号等加零
    public static String addZero(String date) {
        if (date.length() < 2) {
            date = "0" + date ;
        }
        return date;
    }
//    开始进行算法
    public static void doMain(String beginTime, String endTime,
                              ArrayList<InputMsg> InputMsgs) throws ParseException {
        long beginSecondTime = toDateFromString(beginTime).getTime();
        long endSecondTime = toDateFromString(endTime).getTime();
        for (Date currentSecondTime = toDateFromString(beginTime);
             currentSecondTime.getTime() < endSecondTime;
             currentSecondTime =
                     getOneMinuteAferDate(toDateFromString(beginTime))) {
            for (InputMsg inputMsg: InputMsgs) {
                if (inputMsg.minutes.equals(getMinutesFromDate(currentSecondTime))
                && inputMsg.hours.equals(getHoursFromDate(currentSecondTime))
                && inputMsg.dayNum.equals(getDayNumFromDate(currentSecondTime))
                && inputMsg.month.equals(getMonthFromDate(currentSecondTime))
                && inputMsg.weekDay.equals(getWeekFromDate(currentSecondTime))){
                    System.out.println(toStringFromDate(currentSecondTime) + " " + inputMsg.command);
                }
            }
        }
    }
    //把字符串日期转化为日期
    public static Date toDateFromString(String timeString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMM");
        return dateFormat.parse(timeString);
    }
    public static String toStringFromDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMM");
        return dateFormat.format(date);
    }
    static class InputMsg{
        public String minutes;
        public String hours;
        public String dayNum;
        public String month;
        public String weekDay;
        public String command;
    }
}
