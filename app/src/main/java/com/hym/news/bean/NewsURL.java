package com.hym.news.bean;

public class NewsURL {
    //公共的key
    public static String key = "5acd1c8e8f15bf81d68a6c6524399905";
    //公共部分
    public static String info_url = "https://v.juhe.cn/toutiao/index?key=" + key + "&type=";
    //头条
    public static String headline_url = info_url +  "top";
    //社会
    public static String society_url = info_url +  "shehui";
    //国内
    public static String home_url = info_url +  "guonei";
    //国际
    public static String internations_url = info_url +  "guoji";
    //娱乐
    public static String entertainment_url = info_url +  "yule";
    //体育
    public static String sport_url = info_url +  "tiyu";
    //军事
    public static String military_url = info_url +  "junshi";
    //科技
    public static String science_url = info_url +  "keji";
    //财经
    public static String finance_url = info_url +  "caijing";
    //时尚
    public static String fashion_url = info_url +  "shishang";

}
