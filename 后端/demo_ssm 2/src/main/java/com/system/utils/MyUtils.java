package com.system.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

    public static String  getCurrentTime(){

        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String format = sdf.format(date);
        return format;

    }

    /**
     * 获取以当前时间的id
     * @return
     */
    public static Integer  getCurrentTimeForId(){

        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        String format = sdf.format(date);
        return Integer.parseInt(format);
    }


}
