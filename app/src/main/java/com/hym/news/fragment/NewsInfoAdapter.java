package com.hym.news.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.hym.news.bean.TypeBean;

import java.util.List;

public class NewsInfoAdapter extends FragmentStatePagerAdapter {
    Context context;
    List<Fragment> fragmentList;
    List<TypeBean> selectTypeList;

    public NewsInfoAdapter(FragmentManager fm,Context context, List<Fragment> fragmentList, List<TypeBean> selectTypeList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.selectTypeList = selectTypeList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    //返回指定位置的標題
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        TypeBean typeBean = selectTypeList.get(position);
        String title = typeBean.getTitle();
        return title;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
