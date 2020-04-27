package com.hym.news.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hym.news.bean.NewsURL;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, "info.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table itype(id integer primary key,title varchar(10) unique not null,url text not null,isshow varchar(10) not null)";
        sqLiteDatabase.execSQL(sql);
        String insertSql = "insert into itype values(?,?,?,?)";
        sqLiteDatabase.execSQL(insertSql,new Object[]{1,"頭條", NewsURL.headline_url,"true"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{2,"社會", NewsURL.society_url,"true"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{3,"國内", NewsURL.home_url,"true"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{4,"國際", NewsURL.internations_url,"true"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{5,"娛樂", NewsURL.entertainment_url,"false"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{6,"體育", NewsURL.sport_url,"false"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{7,"軍事", NewsURL.military_url,"false"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{8,"科技", NewsURL.science_url,"false"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{9,"財經", NewsURL.finance_url,"false"});
        sqLiteDatabase.execSQL(insertSql,new Object[]{10,"時尚", NewsURL.fashion_url,"false"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
