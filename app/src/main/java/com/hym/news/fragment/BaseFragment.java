package com.hym.news.fragment;

import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hym.news.UniteApp;

import javax.xml.transform.ErrorListener;

public class BaseFragment extends Fragment implements Response.Listener<String>,Response.ErrorListener {

    public void loadData(String url){
        //创建网络请求对象，stringrequst jsonrequest
        StringRequest stringRequest = new StringRequest(url, this, this);
        UniteApp.getHttpQueue().add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //获取网络请求失败时，会请求的函数
    }

    @Override
    public void onResponse(String response) {
        //获取网络请求成功时，会请求的函数
    }
}
