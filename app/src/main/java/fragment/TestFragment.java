package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.jinritoutiao.R;

import mhttp.MyHttp;
import xlistview.bawei.com.xlistviewlibrary.XListView;

/**
 * 作者：李飞 on 2017/4/10 09:58
 * 类的用途：
 */

public class TestFragment extends Fragment implements XListView.IXListViewListener{

      private static final String KEY_CONTENT = "TestFragment:Content";

          public static TestFragment newInstance(String content) {
              TestFragment fragment = new TestFragment();

              StringBuilder builder = new StringBuilder();

              builder.append(content).append(" ");

              builder.deleteCharAt(builder.length() - 1);
              fragment.mContent = builder.toString();

              return fragment;
          }

          private String mContent = "???";

          @Override
          public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);

              if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
                  mContent = savedInstanceState.getString(KEY_CONTENT);
              }
          }

          @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
              View view = inflater.inflate(R.layout.fragment, null);

              XListView lv  = (XListView) view.findViewById(R.id.lv);
              lv.setEnabled(true);
              lv.setPullRefreshEnable(true);
              lv.setPullLoadEnable(true);

                //  网络请求
              new MyHttp(getActivity(),mContent,lv).getVpData();

              return view;
          }



    @Override
          public void onSaveInstanceState(Bundle outState) {
              super.onSaveInstanceState(outState);
              outState.putString(KEY_CONTENT, mContent);
          }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


}
