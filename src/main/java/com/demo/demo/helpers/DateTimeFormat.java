package com.demo.demo.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormat {
    
    public static String format(Date date){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return fmt.format(date);
    }
}
