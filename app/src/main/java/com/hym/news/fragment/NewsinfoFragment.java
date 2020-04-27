package com.hym.news.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hym.news.R;
import com.hym.news.bean.InfoBean;
import com.hym.news.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class NewsinfoFragment extends BaseFragment {


    @BindView(R.id.news)
    ListView newsInfo;

    private String url;
    //listview的数据源
    List<InfoBean.ResultBean.DataBean> mData;
    private InfoItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsinfo, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        TypeBean typeBean = (TypeBean) bundle.getSerializable("type");
        url = typeBean.getUrl();
        Log.d("hymmm",url);
        mData = new ArrayList<>();

        //创建listview的适配器对象
        adapter = new InfoItemAdapter(getActivity(), mData);
        newsInfo.setAdapter(adapter);
        loadData(url);
        return view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        InfoBean infoBean = new Gson().fromJson(response, InfoBean.class);
        List<InfoBean.ResultBean.DataBean> data = infoBean.getResult().getData();
        //添加到数据源当中
        mData.addAll(data);
        //提示adapter数据源发生变化了，更新数据
        adapter.notifyDataSetChanged();
    }
}
