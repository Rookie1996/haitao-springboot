package com.xjr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlDate {

    public static String convertStringFromDate(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = simpleDateFormat.format(date);

        return string;
    }

    public static Date convertDateFromString(String string) throws ParseException {

        String pattern = "";
        if(string.contains("/")){
            String arr[] = string.split(" ");
            if(arr.length != 2){
                System.out.println("==================输入时间字符串不合法！");
                return new Date();
            }
            String timearr[] = arr[1].split(":");
            if(timearr.length == 2){
                pattern = "yyyy/MM/dd HH:mm";
            }else if(timearr.length == 3){
                pattern = "yyyy/MM/dd HH:mm:ss";
            }else if(timearr.length == 1){
                pattern = "yyyy/MM/dd HH";
            }

        }else {
            String arr[] = string.split(" ");
            if(arr.length != 2){
                System.out.println("==================输入时间字符串不合法！");
                return new Date();
            }
            String timearr[] = arr[1].split(":");
            if(timearr.length == 2){
                pattern = "yyyy-MM-dd HH:mm";
            }else if(timearr.length == 3){
                pattern = "yyyy-MM-dd HH:mm:ss";
            }else if(timearr.length == 1){
                pattern = "yyyy-MM-dd HH";
            }

        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(string);
        Date sqlDate = simpleDateFormat.parse(simpleDateFormat.format(date));

        return sqlDate;
    }

    // 删除虚拟目录下的文件
//    public static boolean deleteVirtualDirFile(String uploadFilePath){
//
//        if(uploadFilePath == null ||uploadFilePath.equals("")){
//            return false;
//        }
//        try{
//            File imgFile = new File(uploadFilePath);
//            if(imgFile.delete()){
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//
//    }

    public static void main(String[] args) throws ParseException {
        String str = "2021-3-16 10:15:00";
        System.out.println(convertDateFromString(str));
        Date date = new Date();
        System.out.println(convertStringFromDate(date));

        // 测试删除文件方法
//        String addr =  "D:\\video_dev" + "/200822BG13YKY5P0/video" + "/" + "8fb5ff5e695f4bfa8ed9c9b15d2360e4.mp4";
//        boolean f = deleteVirtualDirFile(addr);
//        System.out.println(f?"删除成功！":"删除失败！");
    }
}
