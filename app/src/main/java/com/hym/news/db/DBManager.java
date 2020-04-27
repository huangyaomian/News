package com.hym.news.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hym.news.bean.TypeBean;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static SQLiteDatabase database;

    public static void initDB(Context context){
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        database = dbOpenHelper.getWritableDatabase();
    }

    //獲取數據庫當中全部行的内容，存儲到集合中
    public static List<TypeBean> getAllTypelist(){
        ArrayList<TypeBean> list = new ArrayList<>();
        Cursor cursor = database.query("itype", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            String isshowstr = cursor.getString(cursor.getColumnIndex("isshow"));
            Boolean isshow = Boolean.valueOf(isshowstr);
            TypeBean typeBean = new TypeBean(id, title, url, isshow);
            list.add(typeBean);
        }
        return list;
    }

    //修改數據庫當中的行信息當中的選中記錄
    public static void updateTypeList(List<TypeBean> typeBeanList){
        for (int i = 0; i < typeBeanList.size(); i++) {
            TypeBean typeBean = typeBeanList.get(i);
            String title = typeBean.getTitle();
            ContentValues values = new ContentValues();
            values.put("isshow",String.valueOf(typeBean.isShow()));
            database.update("itype",values,"title=?",new String[]{title});
        }
    }
}
