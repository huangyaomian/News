package com.hym.news;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.hym.news.bean.TypeBean;
import com.hym.news.db.DBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {

    @BindView(R.id.add_iv_back)
    ImageView mAddIvBack;
    @BindView(R.id.add_lv)
    ListView mAddLv;

    //數據源
    List<TypeBean> mDatas;
    private AddItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);
        mDatas = DBManager.getAllTypelist();

        //創建適配器對象
        adapter = new AddItemAdapter(this, mDatas);
        mAddLv.setAdapter(adapter);
    }

    @OnClick(R.id.add_iv_back)
    public void onViewClicked() {
        finish();//銷毀當前的actitvy
    }

    @Override
    protected void onPause() {
        super.onPause();
        DBManager.updateTypeList(mDatas);
    }
}
