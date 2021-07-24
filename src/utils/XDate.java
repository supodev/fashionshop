/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vvtvo
 */
public class XDate {
    //Xử lý dữ liệu thời gian dd-MM-yyy
    static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    //chuyển chuỗi thành thời gian
    public static Date toDate(String date, String pattern){
        try {
            formater.applyPattern(pattern);
            java.util.Date date1 = formater.parse(date);
            java.sql.Date myDate = new java.sql.Date(date1.getTime());
            return myDate;
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    //lấy thời gian hiện tại chuyển thành chuỗi dd-MM-yyy
    public static String toString(Date date, String pattern){
        formater.applyPattern(pattern);
        return formater.format(date);
    }
    //bổ sung một con số ngày vào ngày hiện tại (
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    public static Date now() {
        return new Date();
    }
}
