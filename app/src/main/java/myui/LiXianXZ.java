package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.R;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import adapter.ViewHolder;
import bean.NewsInfo;
import utils.DbUtils;

/**
 * 作者：李飞 on 2017/4/22 08:33
 * 类的用途：
 */

public class LiXianXZ extends Fragment implements View.OnClickListener {

    private ImageView lxxz_back;
    private TextView lxxz_xz;
    private ListView xiazai_lv;
    private List<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lixianxz, null);

        initView(view);

        selectDB();

        xiazai_lv.setAdapter(new MyAdapter<String>(getActivity(),mList,R.layout.lxxz_lv_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.lxxz_lv_tx,item);
            }
        });

        return view;
    }

    private void selectDB() {

        mList = new ArrayList<String>();

        DbManager db = DbUtils.getDaoConfig();
        try {
            List<NewsInfo> all = db.findAll(NewsInfo.class);
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).zhuangt.equals("1")){
                        mList.add(all.get(i).title);
                }
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        lxxz_back = (ImageView) view.findViewById(R.id.lxxz_back);
        lxxz_xz = (TextView) view.findViewById(R.id.lxxz_xz);
        xiazai_lv = (ListView) view.findViewById(R.id.xiazai_lv);

        lxxz_back.setOnClickListener(this);
        lxxz_xz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lxxz_back:
                getActivity().finish();
                break;
            case R.id.lxxz_xz:

                break;
        }
    }
}
