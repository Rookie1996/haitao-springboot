package com.xjr.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class DateCrossUtils {

    //String startStr = "2019-01-25 00:00:00";
    //String endStr = "2019-01-28 23:59:59";

    /**
     * 前两个参数是用来校验的时间段
     * @param startTime - A
     * @param endTime - B
     * @param sqlStart - a
     * @param sqlEnd - b
     * @return 返回false表示没有交叉,返回true表示有时间端重合
     * @throws ParseException
     */
    public static Boolean isDateCross(String startTime, String endTime, String sqlStart, String sqlEnd) throws ParseException {

        if(startTime == null || endTime == null
                || startTime.equals("") || endTime.equals("")){
            return false;
        }
        Date A = SqlDate.convertDateFromString(startTime);
        Date B = SqlDate.convertDateFromString(endTime);
        Date a = SqlDate.convertDateFromString(sqlStart);
        Date b = SqlDate.convertDateFromString(sqlEnd);

        // 默认条件： a<b,A<B
        if(b.getTime()<A.getTime() || a.getTime()>B.getTime()){
            return false;
        }

        // 返回true表示存在交叉
        return true;

//        if (startSQL.getTime() < startDate.getTime() && startDate.getTime() < endSQL.getTime() && endSQL.getTime() < endDate.getTime()) {
//           /* 请输入开始时间：
//            2019-01-22 00:00:00
//            请输入结束时间：
//            2019-01-26 23:59:59
//            前交叉*/
//            System.out.println("前交叉");
//            return true;
//        }
//        if (startDate.getTime() < startSQL.getTime() && endSQL.getTime() < endDate.getTime()) {
//            System.out.println("中间交叉");
//            return true;
//        }
//        if (startDate.getTime()<startSQL.getTime()&&startSQL.getTime() < endDate.getTime() && endDate.getTime() < endSQL.getTime()) {
//            System.out.println("后交叉");
//            return true;
//        }
//        if(startSQL.getTime()<startDate.getTime()&&endDate.getTime()<endSQL.getTime()){
//            System.out.println("外交叉");
//            return true;
//        }

    }

    public static void main(String[] args) throws ParseException {
        String startStr = "2021/3/24 00:01";
        String endStr = "2021/3/24 00:02";

        String start = "2021-03-24 00:05:00";
        String end = "2021-03-25 00:00:00";

        System.out.println(isDateCross(startStr,endStr,start,end));

      /*  请输入开始时间：
        2019-01-22 00:00:00
        请输入结束时间：
        2019-01-30 23:59:59
        前交叉
        后交叉*/
    }

}
