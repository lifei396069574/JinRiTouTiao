package mhttp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ListView;

import com.example.administrator.jinritoutiao.R;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import adapter.MyFragmentAdapter;
import adapter.ViewHolder;
import bean.JsonBean;
import bean.NewsBean;
import bean.NewsInfo;
import utils.DbUtils;


/**
 * 作者：李飞 on 2017/4/10 09:37
 * 类的用途：
 */

public class MyHttp {

    private  String urlkey;
    private List<NewsInfo>  list_news ;
    private DbManager mDb;
    private ViewPager vp;
    private SlidingFragmentActivity fcontext;
    private Context context;
    private List<String> mList_title;
    private List<String> mList_uri;
    private ListView lv;


    public MyHttp(SlidingFragmentActivity fcontext , String urlkey  , ViewPager vp){
        this.urlkey=urlkey;
        this.vp=vp;
        this.fcontext=fcontext;
    }

    public MyHttp(Context context, String urlkey, ListView lv) {
        this.context=context;
        this.urlkey=urlkey;
        this.lv=lv;
    }

    public MyHttp(){
    }

    public  void getTitleData(){
        RequestParams params = new RequestParams(MyUri.urlTitle);
        params.addQueryStringParameter("uri",urlkey);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result){
                //解析result
                getNewsInfoData(result);
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    private  void getNewsInfoData(String result) {

        list_news = new ArrayList<NewsInfo>();

        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(result, NewsBean.class);
        List<NewsBean.ResultBean.DateBean> date = newsBean.getResult().getDate();

        mDb = DbUtils.getDaoConfig();

        for (NewsBean.ResultBean.DateBean bean:date){

            NewsInfo newsInfo = new NewsInfo();
            newsInfo.id=bean.getId();
            newsInfo.title=bean.getTitle();
            newsInfo.uri=bean.getUri();
            newsInfo.zhuangt="0";
            if (bean.getTitle().equals("头条") || bean.getTitle().equals("社会") ){
                newsInfo.zhuangt="1";
            }

            list_news.add(newsInfo);

        }


        Log.i("kkk","查询方法");

        try {

            mDb.save(list_news);

        } catch (DbException e) {
            e.printStackTrace();
        }

        //查询方法
        selectDB();

    }

    public void selectDB() {

        mList_title = new ArrayList<String>();
        mList_uri = new ArrayList<String>();

        try {
            List<NewsInfo> all = mDb.findAll(NewsInfo.class);//返回当前表里面的所有数据
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

//    private void getListData(List<NewsBean.ResultBean.DateBean> data) {
//       mList_title = new ArrayList<String>();
//        mList_uri = new ArrayList<String>();
//
//        for (NewsBean.ResultBean.DateBean n : data) {
//
//            mList_title.add(n.getTitle());
//            mList_uri.add(n.getUri());
//
//        }
//
//        setAdapter();
//
//    }


    public void setAdapter(){

        FragmentPagerAdapter adapter = new MyFragmentAdapter(fcontext.getSupportFragmentManager(),mList_uri,mList_title);

        vp.setAdapter(adapter);

    }


    public void  getVpData(){

        RequestParams params = new RequestParams(MyUri.url);
        params.addQueryStringParameter("uri",urlkey);

//      Log.d("zzz","params :"+params.toString());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析result
                //  Log.d("zzz","onSuccess "+result.toString());
                setData(result);
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("zzz","onError ");
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("zzz","onCancelled ");
            }
            @Override
            public void onFinished() {
                Log.d("zzz","onFinished ");
            }
        });
    }
    private void setData(String json) {

        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(json, JsonBean.class);

        List<JsonBean.ResultBean.DataBean> data = jsonBean.getResult().getData();

        lv.setAdapter(new MyAdapter<JsonBean.ResultBean.DataBean>(
                context, data, R.layout.list_item) {
            @Override
            public void convert(final ViewHolder helper, JsonBean.ResultBean.DataBean item) {
                x.image().loadDrawable(item.getThumbnail_pic_s02(),new ImageOptions.Builder().setFadeIn(true).build(), new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {
                        helper.setxUtilsImage(R.id.image).setImageDrawable(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }
                    @Override
                    public void onFinished() {

                    }
                });
                helper.setText(R.id.text_title,item.getTitle());
                helper.setText(R.id.text_butt,item.getAuthor_name());
            }
        });
    }
}
