package com.hym.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hym.news.DescActivity;
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
        setListener();
        loadData(url);
        return view;
    }

    //设置listview每一项点击事件的函数
    private void setListener() {
       newsInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               InfoBean.ResultBean.DataBean dataBean = mData.get(position);
               String url = dataBean.getUrl();
               Intent intent = new Intent(getActivity(), DescActivity.class);
               intent.putExtra("url",url);
               startActivity(intent);
           }
       });

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
