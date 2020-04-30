package com.hym.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.hym.news.controller.ProgressBarController;
import com.hym.news.widget.H5NoNetView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DescActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.desc_iv_back)
    ImageView mDescIvBack;
    @BindView(R.id.desc_webviewcontainer)
    RelativeLayout mDescWebviewcontainer;
    @BindView(R.id.desc_nonetview)
    H5NoNetView mDescNonetview;
    @BindView(R.id.desc_progressBar)
    ProgressBar mDescProgressBar;
    private String url;

    private Timer hideProgressBarTimer;
    private HideBarTimeTask hideBarTimeTask;
    private String blackUrl = "about:blank";//浏览器空白页


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        ButterKnife.bind(this);


//        url = getIntent().getStringExtra("url");
        url = "https://mini.eastday.com/a/200430080345029.html?qid=02263";
//        url = "https://m.baidu.com/";
        if (url != null) {
            url = url.trim();
        }
        initView();
        loadWebView();
    }


    //初始化视图
    protected void initView() {
        WebSettings webseting = webview.getSettings();

        webseting.setSupportZoom(true);
        webseting.setBuiltInZoomControls(true);
        webseting.setDisplayZoomControls(false);
        webseting.setJavaScriptEnabled(true);//设置页面支持js交互
        webseting.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webseting.setLoadWithOverviewMode(true);//缩放至屏幕大小
        webseting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置webview的缓存方式
        webseting.setAllowFileAccess(true);//设置可以访问文件
        webseting.setJavaScriptCanOpenWindowsAutomatically(true);//设置可以打开新窗口
        if (Build.VERSION.SDK_INT >= 19) {
            webseting.setLoadsImagesAutomatically(true); //支持自动加载图片
        } else {
            webseting.setLoadsImagesAutomatically(false);
        }


        // 设置Web视图
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.setDownloadListener(new DownloadListener());

        mDescNonetview.setRefreshListener(new H5NoNetView.HtmlReloadListener() {
            @Override
            public void triggerRefresh() {
                if (netIsAvailable()) {
                    mDescNonetview.setVisibility(View.GONE);
                    webview.setVisibility(View.VISIBLE);
                }
                loadWebView();
            }
        });
    }


    protected void loadWebView() {
        if (url.startsWith("https://") || url.startsWith("http://")) {
            //同步Cookies
            CookieSyncManager.createInstance(this);
            CookieSyncManager.getInstance().sync();
            webview.loadUrl(url);
        } else {
            webview.loadData(url, "text/html", "UTF-8");
        }
    }

    public boolean netIsAvailable() {
        //检测网络是否可用
        ConnectivityManager cwjManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cwjManager.getActiveNetworkInfo() != null && cwjManager.getActiveNetworkInfo().isAvailable();
    }

    private void runTimer(int delay) {
        stopTimer();
        hideProgressBarTimer = new Timer(true);
        hideBarTimeTask = new HideBarTimeTask();
        hideProgressBarTimer.schedule(hideBarTimeTask, delay);
    }

    private void stopTimer() {
        if (hideBarTimeTask != null) {
            hideBarTimeTask.cancel();
            hideBarTimeTask = null;
        }

        if (hideProgressBarTimer != null) {
            hideProgressBarTimer.cancel();
            hideProgressBarTimer.purge();
            hideProgressBarTimer = null;
        }
    }

    private ProgressBarController progressBarController = new ProgressBarController(new ProgressBarController.ControllerListener() {

        @Override
        public void stop() {
            runTimer(500);
        }

        @Override
        public void setProgress(int progress) {
            mDescProgressBar.setProgress(progress);
        }

        @Override
        public void start() {
            if (mDescProgressBar.getVisibility() == View.GONE) {
                mDescProgressBar.setVisibility(View.VISIBLE);
            }
            stopTimer();
        }

    });

    class HideBarTimeTask extends TimerTask {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = 10000;
            webviewHandler.sendMessage(msg);
        }
    }

    private Handler webviewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10000) {
                mDescProgressBar.setVisibility(View.GONE);
                mDescProgressBar.setProgress(0);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview != null && webview.canGoBack() && checkBackUrl(blackUrl)) {
                webview.goBack();
            } else {
                webview.stopLoading();
                finish();
            }

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean checkBackUrl(String url) {
        WebBackForwardList mWebBackForwardList = webview.copyBackForwardList();
        String backUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex() - 1).getUrl();
        //判断是否是空白页
        if (backUrl != null && backUrl.equalsIgnoreCase(url)) {
            return false;
        }
        return true;
    }


    private class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //开始加载，显示进度
            progressBarController.preloading();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            if (failingUrl.equalsIgnoreCase(url) == false) {
                return;
            }

            webview.loadUrl(blackUrl);
            //只有加载完毕才应该调用clearHistory()
            webview.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webview.clearHistory();
                    mDescNonetview.setVisibility(View.VISIBLE);
                    webview.setVisibility(View.GONE);
                }
            }, 500);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //忽略SSL证书错误检测,使用SslErrorHandler.proceed()来继续加载
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //通知程序当前页面加载进度
            progressBarController.setCurrentValue(newProgress);
        }
    }

    private class DownloadListener implements android.webkit.DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            //需要Webview开启下载监听,否则点击下载连接，没有反应
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
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




    @OnClick(R.id.desc_iv_back)
    public void onViewClicked() {
        finish();
    }
}
