package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/13 18:18
 * 类的用途：
 */

public class PhoneZhu extends Fragment implements View.OnClickListener {

    private ImageButton zhuce_imageview;
    private TextView zhuce_textview1;
    private TextView zhuce_login;
    private TextView edittext1;
    private EditText edittext2;
    private RadioButton radiobutton;
    private TextView zhuce_xieyi;
    private Button zhuce_button;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuce_layout, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        zhuce_imageview = (ImageButton) view.findViewById(R.id.zhuce_imageview);
        zhuce_textview1 = (TextView) view.findViewById(R.id.zhuce_textview1);
        zhuce_login = (TextView) view.findViewById(R.id.zhuce_textview2);
        edittext1 = (TextView) view.findViewById(R.id.edittext1);
        edittext2 = (EditText) view.findViewById(R.id.edittext2);
        radiobutton = (RadioButton) view.findViewById(R.id.radiobutton);
        zhuce_xieyi = (TextView) view.findViewById(R.id.zhuce_textview3);
        zhuce_button = (Button) view.findViewById(R.id.zhuce_button);

        zhuce_imageview.setOnClickListener(this);
        zhuce_button.setOnClickListener(this);
        zhuce_login.setOnClickListener(this);
        zhuce_xieyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce_imageview:

                FragmentTransaction fragmentTransaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.framelayout,new PhoneLand());
                fragmentTransaction3.commit();
                break;
            case R.id.zhuce_button:
                //下一步

                if (!radiobutton.isChecked() || TextUtils.isEmpty(edittext2.getText().toString())){
                    Toast.makeText(getActivity(),"同意该协议或手机号码为空",Toast.LENGTH_SHORT).show();
                }else {

                    YanZheng yanZheng = new YanZheng();

                    Bundle bundle = new Bundle();
                    bundle.putString("phoneNum",edittext2.getText().toString());
                    yanZheng.setArguments(bundle);

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout,yanZheng);
                    fragmentTransaction.commit();

                }

                break;
            case R.id.zhuce_textview2:
                //登陆
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout,new PhoneLand());
                fragmentTransaction.commit();

                break;
            case R.id.zhuce_textview3:
                //协议
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout,new XieYi());
                fragmentTransaction2.commit();

                break;
        }
    }




}
