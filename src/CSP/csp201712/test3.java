//package CSP.csp201712;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Scanner;
//
//public class test3 {
//    static class InputMsg{
//        public String minutes;
//        public String hours;
//        public String dayNum;
//        public String month;
//        public String weekDay;
//        public String command;
//    }
//
//    public static void main(String[] args) throws IOException, ParseException {
////
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        String beginTime = input.next();
//        String endTime = input.next();
//        ArrayList<InputMsg> inputMsgs = new ArrayList<>();
//        for (int i = 0; i < n ; i++) {
//            InputMsg tab = new InputMsg();
//            tab.minutes = input.next();
//            tab.hours = input.next();
//            tab.dayNum = input.next();
//            tab.month = whichMonth(input.next());
//            tab.weekDay = whichDay(input.next());
//            tab.command = input.next();
//            inputMsgs.add(tab);
//        }
//        doMain(beginTime, endTime, inputMsgs);
//    }
////    过滤输入的月份
//    public static String whichMonth(String input) {
//        if (input.length() <= 2){
//            return input;
//        }
//        else {
//            switch (input){
//                case "Jan":
//                    return "01";
//                case "Feb":
//                    return "02";
//                case "Mar":
//                    return "03";
//                case "Apr":
//                    return "04";
//                case "May":
//                    return "05";
//                case "Jun":
//                    return "06";
//                case "Jul":
//                    return "07";
//                case "Aug":
//                    return "08";
//                case "Sep":
//                    return "09";
//                case "Oct" :
//                    return "10";
//                case "Nov":
//                    return "11";
//                case "Dec":
//                    return "12";
//                 default:
//                     return "00";
//            }
//        }
//
//    }
////    过滤输入的星期
//    public static String whichDay(String input) {
//        if (input.length() <= 2)
//            return input;
//        else{
//            switch (input) {
//                case "Mon":
//                    return "01";
//                case "Tue":
//                    return "02";
//                case "Wed":
//                    return "03";
//                case "Thu":
//                    return "04";
//                case "Fri":
//                    return "05";
//                case "Sat":
//                    return "06";
//                case "Sun":
//                    return "00";
//                default:
//                    return "**";
//            }
//        }
//    }
//
//    //获取一分钟后的日期
//    public static Date getOneMinuteAferDate(Date date){
//        long endSecond = date.getTime() + 60 * 1000;
////        DateFormat df = new SimpleDateFormat("yyyymmddHHMM");
//        return new Date(endSecond);
//    }
//    //获取星期几
//    public static String getWeekFromDate(Date date) {
//        Calendar cal = Calendar.getInstance(); //获取一个日历
//        Date now = new Date();
//        cal.setTime(now);
//        Integer week = cal.get(Calendar.DAY_OF_WEEK);
//        return  week.toString();
//    }
//    //根据日期返回日号 20160601 -->1； 20161011  -->11
//    public static String getDayNumFromDate(Date date) {
//        Integer dayNum = null;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        dayNum = cal.get(Calendar.DAY_OF_MONTH);
//        return addZero(dayNum.toString());
//    }
//    //根据日期返回月号
//    public static String getMonthFromDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer month = cal.get(Calendar.MONTH) + 1;
//        return addZero(month.toString());
//    }
//    //根据日期返回分钟数
//    public static String getMinutesFromDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer minutes = cal.get(Calendar.MINUTE);
//        return addZero(minutes.toString());
//    }
//    //根据日期返回小时数
//    public static String getHoursFromDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer hours = cal.get(Calendar.HOUR);
//        return addZero(hours.toString());
//    }
//    //给月份,日号等加零
//    public static String addZero(String date) {
//        if (date.length() < 2) {
//            date = "0" + date ;
//        }
//        return date;
//    }
//    //    开始进行算法
//    public static void doMain(String beginTime, String endTime,
//                              ArrayList<InputMsg> InputMsgs) throws ParseException {
////        long beginSecondTime = toDateFromString(beginTime).getTime();
//        long endSecondTime = toDateFromString(endTime).getTime();
//        for (Date currentSecondTime = toDateFromString(beginTime);
//             currentSecondTime.getTime() < endSecondTime;
//             currentSecondTime =
//                     getOneMinuteAferDate(toDateFromString(beginTime))) {
//            for (InputMsg inputMsg: InputMsgs) {
//                if (ifequal(inputMsg, currentSecondTime))
//                    System.out.println(toStringFromDate(currentSecondTime) + " "+inputMsg.command);
//            }
//        }
//    }
//    //把字符串日期转化为日期
//    public static Date toDateFromString(String timeString) throws ParseException {
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMM");
//        return dateFormat.parse(timeString);
//    }
////    把日期转化为字符串
//    public static String toStringFromDate(Date date) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHMM");
//        return dateFormat.format(date);
//    }
//
//    public static boolean ifstar(String input, String now) {
//        if (input.equals("*"))
//            return true;
//        else {
//            if (input.equals(now))
//                return true;
//            else
//                return false;
//        }
//    }
//
////    比较日期是否相等
//    public static boolean ifequal(InputMsg inputMsg, Date currentSecondTime) {
//
//        if (ifstar(inputMsg.minutes,getMinutesFromDate(currentSecondTime)) &&
//            ifstar(inputMsg.hours, getHoursFromDate(currentSecondTime)) &&
//            ifstar(inputMsg.dayNum, getDayNumFromDate(currentSecondTime)) &&
//            ifstar(inputMsg.month, getMonthFromDate(currentSecondTime)) &&
//            ifstar(inputMsg.weekDay, getWeekFromDate(currentSecondTime)) ){
//            return true;
//        } else {
//            return  false;
//        }
//    }
//
//}
