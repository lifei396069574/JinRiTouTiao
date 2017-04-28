package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/13 18:43
 * 类的用途：
 */

public class XieYi extends Fragment implements View.OnClickListener {
    private ImageView xianyi_imageview1;
    private TextView zhuce_textview1;
    private ImageView xianyi_imageview2;
    private WebView xianyi_webview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xieyi_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        xianyi_imageview1 = (ImageView) view.findViewById(R.id.xianyi_imagebutton1);
        zhuce_textview1 = (TextView) view.findViewById(R.id.zhuce_textview1);
        xianyi_imageview2 = (ImageView) view.findViewById(R.id.xianyi_imagebutton2);
        xianyi_webview = (WebView) view.findViewById(R.id.xianyi_webview);

        xianyi_imageview1.setOnClickListener(this);
        xianyi_imageview2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xianyi_imagebutton1:

                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout,new PhoneZhu());
                fragmentTransaction2.commit();
                break;
            case R.id.xianyi_imagebutton2:

                break;
        }
    }
}
