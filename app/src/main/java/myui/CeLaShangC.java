package myui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.jinritoutiao.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import adapter.ViewHolder;
import bean.BuBean;
import mhttp.MyHttp;
import utils.NetworkUtils;
import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * 作者：李飞 on 2017/4/23 18:23
 * 类的用途：
 */
public class CeLaShangC extends Fragment implements XListView.IXListViewListener {
    private ImageView shangc_back;
    private XListView shangc_lv;
    private int startNum=0;
    public static List<Integer> list_num =new ArrayList<Integer>();  //唯一
    public static List<BuBean.DataBean> list_bu =new ArrayList<BuBean.DataBean>();
    public static  SharedPreferences.Editor mEditor_json;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shangc, null);

        initView(view);

        initData();

        mSharedPreferences = getActivity().getSharedPreferences("json", Context.MODE_WORLD_READABLE);
        mEditor_json = mSharedPreferences.edit();

        return view;
    }


    private void initView(View view) {
        shangc_back = (ImageView) view.findViewById(R.id.shangc_back);
        shangc_lv = (XListView) view.findViewById(R.id.shangc_lv);

        shangc_lv.setXListViewListener(this);
        shangc_lv.setPullRefreshEnable(true);
        shangc_lv.setPullLoadEnable(true);

        shangc_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void initData() {

        boolean availableByPing = NetworkUtils.isAvailableByPing();
        if (availableByPing){
            new MyHttp(getActivity(), startNum, shangc_lv).getShangCheng();
        }else {
            getDataNoNet();
        }
    }

    private void getDataNoNet() {
        String json = mSharedPreferences.getString(startNum + "", "");
        if (json.equals("")){
            return;
        }
        Gson gson = new Gson();
        BuBean buBean = gson.fromJson(json, BuBean.class);
        List<BuBean.DataBean> data = buBean.getData();

        shangc_lv.setAdapter(new MyAdapter<BuBean.DataBean>(getActivity(),data, R.layout.xlistview_item_sc) {
            @Override
            public void convert(ViewHolder helper, BuBean.DataBean item) {
                helper.setText(R.id.shangc_textview1,item.getTITLE());
                helper.setText(R.id.shangc_textview2,item.getID());
                helper.setImageByUrl(R.id.shangc_imageview, (String) item.getIMAGEURL());
            }
        });
    }

    @Override
    public void onRefresh() {
        startNum=0;
        list_bu.clear();
        list_num.clear();
  //      new MyHttp(getActivity(), startNum, shangc_lv).getShangCheng();
        initData();
        load();
    }

    @Override
    public void onLoadMore() {
        startNum++;
 //       new MyHttp(getActivity(), startNum, shangc_lv).getShangCheng();
        initData();
        load();
    }

    public void load(){
        shangc_lv.stopLoadMore();
        shangc_lv.stopRefresh();

    }


}
