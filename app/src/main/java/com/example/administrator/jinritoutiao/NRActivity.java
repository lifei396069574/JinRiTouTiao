package com.example.administrator.jinritoutiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import utils.Night_styleutils;

public class NRActivity extends AppCompatActivity implements View.OnClickListener {

    private int theme = 0;
    private ImageView nr_fanhui;
    private ImageView nr_ziti;
    private RelativeLayout nrr1;
    private TextView nr_xpinglun;
    private ImageView nr_pinglun;
    private ImageView nr_shouc;
    private ImageView nr_fenx;
    private ImageView nr_jueb;
    private LinearLayout nrl1;
    private WebView nr_wv;
    private String mWuri;
    private boolean flag_nr=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        setContentView(R.layout.activity_nr);

        initView();

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        mWuri = intent.getStringExtra("wuri");
        WebSettings settings = nr_wv.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        nr_wv.setWebViewClient(new WebViewClient());
        nr_wv.loadUrl(mWuri);

    }

    private void initView() {
        nr_fanhui = (ImageView) findViewById(R.id.nr_fanhui);
        nr_ziti = (ImageView) findViewById(R.id.nr_ziti);
        nrr1 = (RelativeLayout) findViewById(R.id.nrr1);
        nr_xpinglun = (TextView) findViewById(R.id.nr_xpinglun);
        nr_pinglun = (ImageView) findViewById(R.id.nr_pinglun);
        nr_shouc = (ImageView) findViewById(R.id.nr_shouc);
        nr_fenx = (ImageView) findViewById(R.id.nr_fenx);
        nr_jueb = (ImageView) findViewById(R.id.nr_jueb);
        nrl1 = (LinearLayout) findViewById(R.id.nrl1);
        nr_wv = (WebView) findViewById(R.id.nr_wv);

        nr_xpinglun.setOnClickListener(this);
        nr_fanhui.setOnClickListener(this);
        nr_ziti.setOnClickListener(this);
        nr_pinglun.setOnClickListener(this);
        nr_shouc.setOnClickListener(this);
        nr_fenx.setOnClickListener(this);
        nr_jueb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nr_fanhui:
                    finish();
                break;
            case R.id.nr_ziti:

                break;
            case R.id.nr_pinglun:

                break;
            case R.id.nr_shouc:

                if (flag_nr){
                    nr_shouc.setImageResource(R.mipmap.ic_action_favor_normal);
                    flag_nr=false;
                }else {  //收藏
                    nr_shouc.setImageResource(R.mipmap.ic_action_favor_on_normal);
                    flag_nr=true;

                }

                break;
            case R.id.nr_fenx:
                //分享

                UMWeb web = new UMWeb(mWuri);
                web.setTitle("This is music title");//标题
            //    web.setThumb();  //缩略图
                web.setDescription("my description");//描述

                new ShareAction(NRActivity.this).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QZONE)
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {
                                Toast.makeText(NRActivity.this,"开始",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                Toast.makeText(NRActivity.this,"成功",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                Toast.makeText(NRActivity.this,"失败",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        }).open();

                break;
            case R.id.nr_jueb:

                break;
            case R.id.nr_xpinglun:

                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
