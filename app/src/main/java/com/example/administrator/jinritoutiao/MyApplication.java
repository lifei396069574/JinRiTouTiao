package com.example.administrator.jinritoutiao;

import android.app.Application;

import org.xutils.x;

import cn.smssdk.SMSSDK;
import utils.Utils;

/**
 * 作者：李飞 on 2017/4/12 10:43
 * 类的用途：
 */



public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

        x.Ext.init(this);
        x.Ext.setDebug(false);

        SMSSDK.initSDK(this, "1cf762461c1c0", "2096c547b3ae27f8174c08da48421403");

    }

}
