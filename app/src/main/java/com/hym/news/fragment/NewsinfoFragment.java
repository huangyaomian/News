package com.hym.news.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hym.news.DescActivity;
import com.hym.news.R;
import com.hym.news.bean.InfoBean;
import com.hym.news.bean.TypeBean;
import com.hym.news.bean.WYInfoBean;
import com.hym.news.view.MyListView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class NewsinfoFragment extends BaseFragment {


    @BindView(R.id.news)
    XRecyclerView newsInfo;
//    MyListView newsInfo;

    private String url;
    //listview的数据源
    List<WYInfoBean.T1348647853363Bean> mData;
//    private InfoItemAdapter adapter;
    private FeedBackAdapter adapter;
    private int curr = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsinfo, container, false);
        ButterKnife.bind(this,view);
        newsInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();
        TypeBean typeBean = (TypeBean) bundle.getSerializable("type");
        url = typeBean.getUrl();
        Log.d("hymmm",url);
        mData = new ArrayList<>();

        initXRL();

        //创建listview的适配器对象
//        adapter = new InfoItemAdapter(getActivity(), mData);
        adapter = new FeedBackAdapter(getActivity(), mData, new FeedBackAdapter.onItemClickLiseter() {
            @Override
            public void onClick(String url) {
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        newsInfo.setAdapter(adapter);

       /* newsInfo.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        loadData(url);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        newsInfo.onRefreshComplete();
                    }
                }.execute(null, null, null);
            }
        });*/

//        setListener();
        initLoadData(url+getPager(curr)+".html");
        return view;
    }

    private void initXRL() {
        //设置是否允许下拉刷新
        newsInfo.setPullRefreshEnabled(true);
        //设置是否允许上拉加载
        newsInfo.setLoadingMoreEnabled(true);

        //设置上拉下拉刷新的样式
        newsInfo.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        newsInfo.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //設置間隔線
        newsInfo.addItemDecoration(new MyDecoration());

//        xRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
//        View header = LayoutInflater.from(context).inflate(R.layout.accident_ammt_item,(ViewGroup)view.findViewById(android.R.id.content),false);
//        xRecyclerView.addHeaderView(header);

        //设置上下拉的文字提示语
        newsInfo.getDefaultFootView().setLoadingHint("自定义加载中提示");
        //设置XRecyclerView加载到最后一页再上拉的提示信息
        newsInfo.getDefaultFootView().setNoMoreHint("加载完毕");
        //设置XRecyclerView下拉刷新时间提示
        newsInfo.getDefaultRefreshHeaderView().setRefreshTimeVisible(false);

        newsInfo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            //下拉刷新
            public void onRefresh() {
                //当下拉刷新的时候，重新获取数据，所有curr要变回0，并且把集合list清空
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //当前页码
                        curr = 1;

                        /**加载数据处理**/
                        initLoadData(url+getPager(curr)+".html");
                        newsInfo.refreshComplete();
                    }
                },2000);

            }
            @Override
            //上拉加载
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curr++;//当前页码
                        /**加载more数据处理**/
                        initLoadData(url+getPager(curr)+".html");
                        newsInfo.loadMoreComplete();
                    }
                },2000);

            }
        });

    }



    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.d("hymmm",response);
        WYInfoBean infoBean = new Gson().fromJson(response, WYInfoBean.class);
//        InfoBean infoBean = new Gson().fromJson(response, InfoBean.class);
       /* if (infoBean.getError_code() == 10012){
            Toast.makeText(getContext(),infoBean.getReason(),Toast.LENGTH_SHORT).show();
        }else {
            List<InfoBean.ResultBean.DataBean> data = infoBean.getResult().getData();
            if (curr == 1) {
                mData.clear();//確保每次添加數據源進去的時候前面的是空的
            }
            //添加到数据源当中
            mData.addAll(data);
            //提示adapter数据源发生变化了，更新数据
            adapter.notifyDataSetChanged();
        }*/

        List<WYInfoBean.T1348647853363Bean> data = infoBean.getT1348647853363();
        if (data == null || data.size() == 0) {
            Toast.makeText(getActivity(),"暂时没有更多数据",Toast.LENGTH_SHORT).show();
        }else {
            if (curr == 1) {
                mData.clear();//確保每次添加數據源進去的時候前面的是空的
            }
            //添加到数据源当中
            mData.addAll(data);
            //提示adapter数据源发生变化了，更新数据
            adapter.notifyDataSetChanged();
        }

    }

    //按照页数返回对应的请求参数
    private String getPager(int curr){
        int begin = 0;
        int end = 0;
        if (curr==1) {
            return 0 + "-" + curr + "0";
        }else {
            begin = (curr - 1) * 10;
            return begin + "-" + curr + "0";
        }
    }


    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
