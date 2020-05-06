package com.hym.news.fragment;

import android.content.Context;
import android.view.ViewGroup;

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

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 复写destroyItem方法，注释掉销毁item的操作。
        // 如果不注释掉下面这句话，当ViewPager滚动时候，会自动销毁上上一个页面，导致页面再次显示的时候会被重新初始化。
//        super.destroyItem(container, position, object);
    }
}
