package com.hym.news;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.hym.news.bean.TypeBean;
import com.hym.news.fragment.NewsInfoAdapter;
import com.hym.news.fragment.NewsinfoFragment;
import com.hym.news.view.PagerSlidingTabStrip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_tabstrip)
    PagerSlidingTabStrip mMainTabstrip;
    @BindView(R.id.main_iv_add)
    ImageView mMainIvAdd;
    @BindView(R.id.main_tab)
    LinearLayout mMainTab;
    @BindView(R.id.main_vp)
    ViewPager mMainVp;

    List<Fragment> fragmentList;
    List<TypeBean> selectTypeList;
    private NewsInfoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList = new ArrayList<>();
        selectTypeList = new ArrayList<>();

        initPager();//初始化頁面

        //創建適配器對象
        adapter = new NewsInfoAdapter(getSupportFragmentManager(), this, fragmentList, selectTypeList);
        //設置適配器
        mMainVp.setAdapter(adapter);
        //關聯tabstrip和viewPager
        mMainTabstrip.setViewPager(mMainVp);


    }

    private void initPager() {
        List<TypeBean> typeBeanList = TypeBean.getTypeBean();
        selectTypeList.addAll(typeBeanList);
        for (int i = 0; i < selectTypeList.size(); i++) {
            TypeBean typeBean = selectTypeList.get(i);
            NewsinfoFragment newsinfoFragment = new NewsinfoFragment();
            //想fragment當中傳遞數據
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", (Serializable) typeBean);
            newsinfoFragment.setArguments(bundle);
            fragmentList.add(newsinfoFragment);
        }
    }

    @OnClick(R.id.main_iv_add)
    public void onViewClicked() {
    }
}
