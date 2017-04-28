package myui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.R;

import static com.example.administrator.jinritoutiao.R.id.editText3;
import static com.example.administrator.jinritoutiao.R.id.editText4;

/**
 * 作者：李飞 on 2017/4/13 16:02
 * 类的用途：
 */

public class PhoneLand extends Fragment implements View.OnClickListener {
    private ImageView button_landmore_xiao;
    private TextView textView;
    private TextView textView2;
    private Button button;
    private EditText edit_phone;
    private EditText edit_pwd;
    private int mNum;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_phone, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        button_landmore_xiao = (ImageView) view.findViewById(R.id.button_landmore_xiao);
        textView = (TextView) view.findViewById(R.id.textView1_phone);
        textView2 = (TextView) view.findViewById(R.id.textView2_phone);
        button = (Button) view.findViewById(R.id.button_phone_login);

        button_landmore_xiao.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        button.setOnClickListener(this);
        edit_phone = (EditText) view.findViewById(editText3);
        edit_phone.setOnClickListener(this);
        edit_pwd = (EditText) view.findViewById(editText4);
        edit_pwd.setOnClickListener(this);


        Bundle arguments = getArguments();
        mNum = arguments.getInt("num");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_landmore_xiao:
                if (mNum==1){
                    LandMore phoneMore = new LandMore();
                    Bundle bundle = new Bundle();
                    bundle.putInt("num",2);
                    phoneMore.setArguments(bundle);
                    tiaozhuan(new LandMore());
                }else if (mNum==2){
                    PhoneZhu phoneZhu = new PhoneZhu();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("num",1);
                    phoneZhu.setArguments(bundle2);
                    tiaozhuan(phoneZhu);
                }


                break;
            case R.id.button_phone_login:
                //添加判断
                 if (edit_phone.getText().toString().trim().length()!=11){
                     Toast.makeText(getActivity(),"请输入11位的手机号",Toast.LENGTH_SHORT).show();
                 }else {
                     //登陆模块

                     SharedPreferences sharedPreferences = getActivity().getSharedPreferences("person", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                     SharedPreferences.Editor editor = sharedPreferences.edit();

                     editor.putBoolean("is",true);
                     editor.commit();
                 }

                break;
            case R.id.textView1_phone:
                //注册
                PhoneZhu phoneZhu = new PhoneZhu();
                Bundle bundle = new Bundle();
                bundle.putInt("num",3);
                phoneZhu.setArguments(bundle);
                tiaozhuan(phoneZhu);

                break;
            case R.id.textView2_phone:
                //忘记密码
                tiaozhuan(new ForgotPwd());
                break;
        }
    }

    public void tiaozhuan(Fragment f) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, f);
        fragmentTransaction.commit();
    }

}
