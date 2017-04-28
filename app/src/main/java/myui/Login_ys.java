package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.jinritoutiao.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import bean.Login;
import bean.Login_suc;
import mhttp.MyUri;
import utils.Utils;

/**
 * 作者：李飞 on 2017/4/20 13:08
 * 类的用途：原生登陆
 */

public class Login_ys extends Fragment implements View.OnClickListener {

    private EditText denglu_name;
    private EditText denglu_pwd;
    private EditText denglu_pwd2;
    private EditText denglu_email;
    private Button denglu_zhu;
    private Button denglu_deng;
    private ImageView denglu_fan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.denglu_yuans, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        denglu_name = (EditText) view.findViewById(R.id.denglu_name);
        denglu_pwd = (EditText) view.findViewById(R.id.denglu_pwd);
        denglu_pwd2 = (EditText) view.findViewById(R.id.denglu_pwd2);
        denglu_email = (EditText) view.findViewById(R.id.denglu_email);
        denglu_zhu = (Button) view.findViewById(R.id.denglu_zhu);
        denglu_deng = (Button) view.findViewById(R.id.denglu_deng);
        denglu_fan = (ImageView) view.findViewById(R.id.denglu_fan);

        denglu_zhu.setOnClickListener(this);
        denglu_deng.setOnClickListener(this);
        denglu_fan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.denglu_zhu:

                if (TextUtils.isEmpty(denglu_name.getText().toString())) {
                    Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(denglu_pwd.getText().toString()) || TextUtils.isEmpty(denglu_pwd2.getText().toString())) {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(denglu_email.getText().toString())) {
                    Toast.makeText(getActivity(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.isEmailAddress(denglu_email.getText().toString())) {
                        regis();
                    } else {
                        Toast.makeText(getActivity(), "邮箱格式错误", Toast.LENGTH_SHORT).show();

                    }
                }

                break;
            case R.id.denglu_deng:

                if (TextUtils.isEmpty(denglu_name.getText().toString())) {
                    Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(denglu_pwd.getText().toString()) || TextUtils.isEmpty(denglu_pwd2.getText().toString())) {
                    Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    logIn();
                }
                break;
            case R.id.denglu_fan:
                tiaozhuan(new LandMore());
                break;
        }
    }


    private void logIn() {

        RequestQueue mQueue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest=new StringRequest(Request.Method.POST, MyUri.LINK_MOBILE_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
            /*成功获取到数据之后的处理:将json串转换为对象*/
                        Gson gson=new Gson();
                        Login login = gson.fromJson(response, Login.class);
                        Log.i("iii","登陆"+response);
                        if (login.getCode()==400){
                            Toast.makeText(getActivity(), login.getDatas().getError(), Toast.LENGTH_SHORT).show();

                        }else {
                            if (login.getCode() == 200) {
                                Login_suc login_suc = gson.fromJson(response, Login_suc.class);
                                Login_suc.DatasBean datas = login_suc.getDatas();

                                Toast.makeText(getActivity(), "登陆成功" + datas.getUsername().toString(), Toast.LENGTH_SHORT).show();

                                tiaozhuan(new PersonInfo());

                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //读取失败的处理

            }
        }){
            @Override
            protected Map<String, String> getParams() {

                //在这里设置需要post的参数
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", denglu_name.getText().toString());
                params.put("password", denglu_pwd.getText().toString());
                params.put("client",MyUri.SYSTEM_TYPE);

                return params;
            }
        };

        mQueue.add(stringRequest); //将请求添加到请求队列中

    }

    private void regis() {

        RequestQueue mQueue= Volley.newRequestQueue(getActivity());

        StringRequest stringRequest=new StringRequest(Request.Method.POST, MyUri.LINK_MOBILE_REG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                     /*成功获取到数据之后的处理:将json串转换为对象*/

                        Gson gson=new Gson();
                        Login login = gson.fromJson(response, Login.class);
                        Log.i("iii","注册"+response);
                        if (login.getCode()==400){
                            Toast.makeText(getActivity(), login.getDatas().getError(), Toast.LENGTH_SHORT).show();

                        }else if (login.getCode()==200){
                            Login_suc login_suc = gson.fromJson(response, Login_suc.class);
                            Login_suc.DatasBean datas = login_suc.getDatas();
                            Toast.makeText(getActivity(), "注册成功"+datas.getUsername().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //读取失败的处理

            }
        }){
            @Override
            protected Map<String, String> getParams() {

                //在这里设置需要post的参数
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", denglu_name.getText().toString());
                params.put("password", denglu_pwd.getText().toString());
                params.put("password_confirm", denglu_pwd2.getText().toString());
                params.put("client",MyUri.SYSTEM_TYPE);
                params.put("email",denglu_email.getText().toString());


                return params;
            }
        };

        mQueue.add(stringRequest); //将请求添加到请求队列中

    }

    public void tiaozhuan(Fragment f) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, f);
        fragmentTransaction.commit();
    }

}
