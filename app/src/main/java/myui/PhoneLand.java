package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/13 16:02
 * 类的用途：
 */

public class PhoneLand extends Fragment implements View.OnClickListener {
    private ImageButton button_landmore_xiao;
    private TextView textView;
    private TextView textView2;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_phone, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        button_landmore_xiao = (ImageButton) view.findViewById(R.id.button_landmore_xiao);
        textView = (TextView)  view.findViewById(R.id.textView1_phone);
        textView2 = (TextView)  view.findViewById(R.id.textView2_phone);
        button = (Button)  view.findViewById(R.id.button_phone_login);

        button_landmore_xiao.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_landmore_xiao:
                tiaozhuan(new LandMore());
                break;
            case R.id.button_phone_login:
                //添加判断

                tiaozhuan(new PersonInfo());
                break;
            case R.id.textView1_phone:
                //注册
                tiaozhuan(new PhoneZhu());
                break;
            case R.id.textView2_phone:
                //忘记密码
                tiaozhuan(new ForgotPwd());
                break;
        }
    }
    public void tiaozhuan(Fragment f){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,f);
        fragmentTransaction.commit();
    }
}
