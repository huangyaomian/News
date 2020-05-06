package com.hym.news.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hym.news.R;
import com.hym.news.bean.InfoBean;
import com.hym.news.bean.WYInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {
    private Context context;
    List<WYInfoBean.T1348647853363Bean> feedBackBeanList;
//    List<InfoBean.ResultBean.DataBean> feedBackBeanList;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    private onItemClickLiseter mLiseter;

    public FeedBackAdapter(Context context, List<WYInfoBean.T1348647853363Bean> feedBackBeanList, onItemClickLiseter liseter) {
        this.context = context;
        this.feedBackBeanList = feedBackBeanList;
        this.mLiseter = liseter;
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public FeedBackAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newsfrag_lv,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(FeedBackAdapter.ViewHolder holder, int position) {

//        holder.itemView.setTag(position);

        //获取指定位置的数据源
        WYInfoBean.T1348647853363Bean dataBean = feedBackBeanList.get(position);
//        InfoBean.ResultBean.DataBean dataBean = feedBackBeanList.get(position);
        holder.titleTv.setText(dataBean.getTitle());
//        holder.sourceTv.setText(dataBean.getAuthor_name());
        holder.sourceTv.setText(dataBean.getSource());
//        holder.timeTv.setText(dataBean.getDate());
        holder.timeTv.setText(dataBean.getPtime());

       /* String pic1 = dataBean.getThumbnail_pic_s();
        String pic2 = dataBean.getThumbnail_pic_s02();
        String pic3 = dataBean.getThumbnail_pic_s03(); */
        String pic1 = dataBean.getImgsrc();
        String pic2 = dataBean.getImgsrc();
        String pic3 = dataBean.getImgsrc();

        if (TextUtils.isEmpty(pic1)) {
            holder.iv1.setVisibility(View.GONE);
        }else {
            holder.iv1.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic1,holder.iv1,options);
        }

        if (TextUtils.isEmpty(pic2)) {
            holder.iv2.setVisibility(View.GONE);
        }else {
            holder.iv2.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic2,holder.iv2,options);
        }

        if (TextUtils.isEmpty(pic3)) {
            holder.iv3.setVisibility(View.GONE);
        }else {
            holder.iv3.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic3,holder.iv3,options);
        }

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiseter.onClick(dataBean.getUrl());
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return feedBackBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_newsfrag_rv_title)
        TextView titleTv;
        @BindView(R.id.item_news_tv_source)
        TextView sourceTv;
        @BindView(R.id.item_news_tv_time)
        TextView timeTv;
        @BindView(R.id.item_news_iv1)
        ImageView iv1;
        @BindView(R.id.item_news_iv2)
        ImageView iv2;
        @BindView(R.id.item_news_iv3)
        ImageView iv3;
        @BindView(R.id.item_newsfrag_ll)
        LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface onItemClickLiseter{
        void onClick(String url);
    }
}
