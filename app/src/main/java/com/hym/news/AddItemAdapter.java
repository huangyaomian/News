package com.hym.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hym.news.bean.TypeBean;


import java.util.List;


public class AddItemAdapter extends BaseAdapter {

    Context context;
    List<TypeBean> mDatas;
    private View converView;

    public AddItemAdapter(Context context, List<TypeBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        converView = LayoutInflater.from(context).inflate(R.layout.item_add_lv, null);
        //初始化conversationtiew當中的控件
        TextView nameTv = converView.findViewById(R.id.item_add_tv);
        ImageView iv = converView.findViewById(R.id.item_add_iv);
        //獲取指定位置的數據
        TypeBean typeBean = mDatas.get(i);
        nameTv.setText(typeBean.getTitle());
        if (typeBean.isShow()) {
            iv.setImageResource(R.mipmap.subscribe_checked);
        }else{
            iv.setImageResource(R.mipmap.subscribe_unchecked);
        }
        //爲了避免用戶全都不選中的情況，不符合新聞的展示習慣，所以前兩個的顯示不需要用戶選擇，每次必選展示
        if (i == 0 || i ==1) {
            iv.setVisibility(View.INVISIBLE);
        }else {
            iv.setVisibility(View.VISIBLE);
            converView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    typeBean.setShow(!typeBean.isShow());
                    if (typeBean.isShow()) {
                        iv.setImageResource(R.mipmap.subscribe_checked);
                    }else {
                        iv.setImageResource(R.mipmap.subscribe_unchecked);
                    }
                }
            });
        }
        return converView;
    }


}
