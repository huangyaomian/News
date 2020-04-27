package com.hym.news.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.news.R;
import com.hym.news.bean.InfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

//每一个fragment当中listview的适配器
public class InfoItemAdapter extends BaseAdapter {

    Context context;
    List<InfoBean.ResultBean.DataBean> mData;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public InfoItemAdapter(Context context, List<InfoBean.ResultBean.DataBean> mData) {
        this.context = context;
        this.mData = mData;
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
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_newsfrag_lv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //获取指定位置的数据源
        InfoBean.ResultBean.DataBean dataBean = mData.get(position);
        holder.titleTv.setText(dataBean.getTitle());
        holder.sourceTv.setText(dataBean.getAuthor_name());
        holder.timeTv.setText(dataBean.getDate());

        String pic1 = dataBean.getThumbnail_pic_s();
        String pic2 = dataBean.getThumbnail_pic_s02();
        String pic3 = dataBean.getThumbnail_pic_s03();

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
            imageLoader.displayImage(pic1,holder.iv2,options);
        }

        if (TextUtils.isEmpty(pic3)) {
            holder.iv3.setVisibility(View.GONE);
        }else {
            holder.iv3.setVisibility(View.VISIBLE);
            imageLoader.displayImage(pic1,holder.iv3,options);
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.item_newsfrag_rv_title)
        TextView titleTv;
        @BindView(R.id.item_news_tv_source)
        TextView sourceTv;
        @BindView(R.id.item_news_tv_time)
        TextView timeTv;
        @BindViews({R.id.item_news_iv1,R.id.item_news_iv2,R.id.item_news_iv3})
        ImageView iv1,iv2,iv3;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
