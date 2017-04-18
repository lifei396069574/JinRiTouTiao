package com.example.administrator.jinritoutiao;

import android.os.Bundle;

import base.BaseFragment;
import myui.LandMore;
import utils.Night_styleutils;

public class Main2Activity extends BaseFragment {

    private int theme = 0;
    private android.support.v4.app.FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();


    }

    private void initView() {

        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.framelayout,new LandMore());
        mFragmentTransaction.commit();

    }






}
