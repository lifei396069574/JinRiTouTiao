package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.jinritoutiao.R;

import mhttp.MyHttp;

/**
 * 作者：李飞 on 2017/4/10 09:58
 * 类的用途：
 */

public class TestFragment extends Fragment {

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

              ListView lv  = (ListView) view.findViewById(R.id.lv);
                //  网络请求
              new MyHttp(getActivity(),mContent,lv).getVpData();

              return view;
          }



    @Override
          public void onSaveInstanceState(Bundle outState) {
              super.onSaveInstanceState(outState);
              outState.putString(KEY_CONTENT, mContent);
          }
}
