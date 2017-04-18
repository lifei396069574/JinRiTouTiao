package com.example.administrator.jinritoutiao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentAdapter;
import bean.NewsInfo;
import fragment.MenuLeftFragment;
import mhttp.MyHttp;
import utils.DbUtils;
import utils.NetworkUtils;
import utils.Night_styleutils;

public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {

    private int theme = 0;
    public TabLayout tab;
    public ViewPager vp;
    private ImageButton button_jia;
    private List<String> mList_title;
    private List<String> mList_uri;
    public static boolean isDB=false;
    private BaseUiListener mIUiListener ;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        setNetDialog();

        initRightMenu();

    }

    public void setNetDialog() {

        final MyHttp myHttp = new MyHttp(this, "news", vp);

        //判断 是否 联网
        boolean isCon = NetworkUtils.isConnected();
        if (isCon) {
            myHttp.getTitleData();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("未连接到网络")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setSingleChoiceItems(new String[]{"WiFi", "2G", "3G", "4G"}, 0,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            NetworkUtils.setWifiEnabled(!NetworkUtils.getWifiEnabled());
                                            myHttp.getTitleData();
                                            break;
                                        case 1:
                                            NetworkUtils.setDataEnabled(!NetworkUtils.getDataEnabled());
                                            myHttp.getTitleData();
                                            break;
                                        case 2:
                                            NetworkUtils.setDataEnabled(!NetworkUtils.getDataEnabled());
                                            myHttp.getTitleData();
                                            break;
                                        case 3:
                                            NetworkUtils.setDataEnabled(!NetworkUtils.getDataEnabled());
                                            myHttp.getTitleData();
                                            break;
                                    }
                                    dialog.dismiss();
                                }
                            }
                    ).setNegativeButton("取消", null).show();
        }

    }


    private void initView() {

        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        //指示器与vp 关联
        tab.setupWithViewPager(vp);

        button_jia = (ImageButton) findViewById(R.id.button_jia);
        button_jia.setOnClickListener(this);

        mIUiListener = new BaseUiListener();
    }

    public void initRightMenu() {
        Fragment leftMenuFragment = new MenuLeftFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
//        WindowManager wm = this.getWindowManager();
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
//        int kuan = width/3 *2;
//        //侧滑菜单的宽度
//        menu.setBehindWidth(kuan);
        //剩余主页面的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);
    }

    public void showLeftMenu(View view) {
        getSlidingMenu().showMenu();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_jia:
                startActivity(new Intent(this, PinDao.class));
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.i("fff","onResume   main " +MainActivity.isDB+"");

        if (isDB){
           selectDB();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        MainActivity.isDB=false;

    }

    public void selectDB() {

        mList_title = new ArrayList<String>();
        mList_uri = new ArrayList<String>();

        try {
            List<NewsInfo> all = DbUtils.getDaoConfig().findAll(NewsInfo.class);//返回当前表里面的所有数据
            for (NewsInfo n :all) {
                Log.i("kkk",n.zhuangt);
                if (n.zhuangt.equals("1")){
                    mList_title.add(n.title);
                    mList_uri.add(n.uri);
                }
            }

            setAdapter();

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    public void setAdapter(){

        FragmentPagerAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),mList_uri,mList_title);

        vp.setAdapter(adapter);

    }

    //设置夜间模式
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法  毁掉方法必须写在 activity 中
     * @param requestCode
     * @param resultCode
     * @param data
     */

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        Log.i("jjj","回调");
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {

                Tencent.handleResultData(data, mIUiListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消   在  activity 中完全没用
     */

    public class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
        }
        @Override
        public void onError(UiError uiError) {
        }
        @Override
        public void onCancel() {
        }
    }
}
