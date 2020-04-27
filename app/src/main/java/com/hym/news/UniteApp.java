package com.hym.news;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hym.news.db.DBManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class UniteApp extends Application {

    //创建volle有需要的请求对垒
    private static RequestQueue httpQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(this);
        httpQueue = Volley.newRequestQueue(this);
        initImageLoader(getApplicationContext());//初始化imageloader
    }

    public static RequestQueue getHttpQueue(){
        return httpQueue;
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration builder = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(builder);

    }
}
