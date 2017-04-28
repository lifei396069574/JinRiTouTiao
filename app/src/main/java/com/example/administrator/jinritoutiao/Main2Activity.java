package com.example.administrator.jinritoutiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.umeng.socialize.UMShareAPI;

import base.BaseFragment;
import myui.CeLaBL;
import myui.CeLaFK;
import myui.CeLaHD;
import myui.CeLaHT;
import myui.CeLaHY;
import myui.CeLaSC;
import myui.CeLaSZ;
import myui.CeLaShangC;
import myui.LandMore;
import myui.LiXianXZ;
import utils.Night_styleutils;

public class Main2Activity extends BaseFragment {

    private int theme = 0;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Intent intent = getIntent();
        int what = intent.getIntExtra("what",8);

        switch (what){
            case 8:  //更多登陆
                initView(new LandMore());
                break;
            case 9: //设置
                initView(new CeLaSZ());
                break;
            case 7: //离线下载
                initView(new LiXianXZ());
                break;
            case 0: //好友动态
                initView(new CeLaHY());
                break;
            case 1: //我的话题
                initView(new CeLaHT());
                break;
            case 2: //收藏
                initView(new CeLaSC());
                break;
            case 3: //活动
                initView(new CeLaHD());
                break;
            case 4: //商城
                initView(new CeLaShangC());
                break;
            case 5: //反馈
                initView(new CeLaFK());
                break;
            case 6: //我要爆料
                initView(new CeLaBL());
                break;




        }





    }

    private void initView(Fragment f) {

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.framelayout,f);
        mFragmentTransaction.commit();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }




}
