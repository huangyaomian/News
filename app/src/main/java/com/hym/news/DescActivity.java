package com.hym.news;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.hym.news.loading.LoadingViewManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    private String url;

    Activity context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        ButterKnife.bind(this);
        context = this;
        url = getIntent().getStringExtra("url");
        //创建webview的设置类，对于属性进行设置
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//设置页面支持js交互
        settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置webview的缓存方式
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置可以打开新窗口
        settings.setLoadsImagesAutomatically(true);//支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");
        LoadingViewManager.with(context).setHintText("加载天气中").setAnimationStyle("BallClipRotatePulseIndicator").build();
        //设置加载的网址
        webview.loadUrl(url);

        //默通过手机流浪器打开网址，为了鞥你改过直接通过webview打开网址，就必须设置一下操作
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //使用webview要价在的url
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                // 加载完成
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //开始加载

            }



        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
