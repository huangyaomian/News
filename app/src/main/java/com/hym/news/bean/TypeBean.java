package com.hym.news.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TypeBean implements Serializable {

    private static final long serialVersionUID = -1943961352036134112L;
    private int id;
    private String title;
    private String url;
    private boolean isShow;

    public TypeBean(int id, String title, String url, boolean isShow) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.isShow = isShow;
    }

    public TypeBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public static List<TypeBean> getTypeBean(){
        List<TypeBean> mData = new ArrayList<>();
        TypeBean tb1 = new TypeBean(1, "头条",NewsURL.headline_url, true);
        TypeBean tb2 = new TypeBean(1, "社会",NewsURL.society_url, true);
        TypeBean tb3 = new TypeBean(1, "国内",NewsURL.home_url, true);
        TypeBean tb4= new TypeBean(1, "国际",NewsURL.internations_url, true);
        TypeBean tb5 = new TypeBean(1, "娱乐",NewsURL.entertainment_url, true);
        TypeBean tb6 = new TypeBean(1, "体育",NewsURL.sport_url, true);
        TypeBean tb7 = new TypeBean(1, "军事",NewsURL.military_url, true);
        TypeBean tb8 = new TypeBean(1, "科技",NewsURL.science_url, true);
        TypeBean tb9 = new TypeBean(1, "财经",NewsURL.finance_url, true);
        TypeBean tb10 = new TypeBean(1, "时尚",NewsURL.fashion_url, true);

        mData.add(tb1);
        mData.add(tb2);
        mData.add(tb3);
        mData.add(tb4);
        mData.add(tb5);
        mData.add(tb6);
        mData.add(tb7);
        mData.add(tb8);
        mData.add(tb9);
        mData.add(tb10);

        return mData;
    }
}
