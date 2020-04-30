package com.hym.news;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.hym.news.loading.LoadingViewManager;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    private String url;

    Activity context;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        ButterKnife.bind(this);
        // 清缓存和记录，缓存引起的白屏
        webview.clearCache(true);
        webview.clearHistory();




//        url = getIntent().getStringExtra("url");
//        url = "https://mini.eastday.com/a/200430080345029.html?qid=02263";
        url = "http://www.juming.com/broker/?tt=0&t=tiao_chaoshi&ym=www.ttxinwen.com";
        //创建webview的设置类，对于属性进行设置
        WebSettings settings = webview.getSettings();
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);//设置页面支持js交互
        settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);//缩放至屏幕大小
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置webview的缓存方式
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置可以打开新窗口
        if(Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);


        //Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
        //处理http和https混合的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        } else {
            settings.setMixedContentMode(WebSettings.LOAD_NORMAL);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 允许javascript出错
            try {
                Method method = Class.forName("android.webkit.WebView").
                        getMethod("setWebContentsDebuggingEnabled", Boolean.TYPE);
                if (method != null) {
                    method.setAccessible(true);
                    method.invoke(null, true);
                }
            } catch (Exception e) {
                // do nothing
            }
        }
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }*/
//        LoadingViewManager.with(context).setHintText("加载天气中").setAnimationStyle("BallClipRotatePulseIndicator").build();

//        webview.setWebChromeClient(new WebChromeClient());
        //默通过手机流浪器打开网址，为了鞥你改过直接通过webview打开网址，就必须设置一下操作
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (url.startsWith("http://") || url.startsWith("https://")) { // 4.0以上必须要加
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                // 加载完成
                webview.getSettings().setLoadsImagesAutomatically(true);
                webview.setLayerType(View.LAYER_TYPE_HARDWARE,null);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //开始加载

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
                //加载失败调用
                Log.d("hymmm","加载失败了");
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                switch (error.getPrimaryError()) {
                    case SslError.SSL_INVALID: // 校验过程遇到了bug
                    case SslError.SSL_UNTRUSTED: // 证书有问题
                        handler.proceed();
                    default:
                        handler.cancel();
                }
            }
        });




        //设置加载的网址
        webview.loadUrl(url);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webview != null) {
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webview.loadUrl(url);
    }





}
