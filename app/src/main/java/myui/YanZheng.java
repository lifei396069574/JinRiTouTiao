package myui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.R;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import view.LinEditetext;

/**
 * 作者：李飞 on 2017/4/14 09:51
 * 类的用途：
 */

public class YanZheng extends Fragment implements View.OnClickListener {
    private ImageView zhuce_fanhui;
    private RelativeLayout relative1;
    private TextView phone_text;
    private TextView phone_text1;
    private RelativeLayout linewtext1;
    private LinEditetext account_et;
    private Button chongxinfasong;
    private RelativeLayout relative11;
    private LinEditetext password_et;
    private Button denglu;
    private int time =60;
    private ProgressDialog dialog;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (time<1){
                chongxinfasong.setEnabled(true);
                chongxinfasong.setText("重新发送");
                time=60;
            }else{
                time--;
                chongxinfasong.setText("重新发送"+time+"s");
                sendEmptyMessageDelayed(0,1000);
            }
        }
    };
    private String mPhoneNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phonezhuce, null);

        initView(view);

        timeBack();

        SMSSDK.registerEventHandler(ev);
        //发送验证
        getSecurity();

        return view;
    }
    private void initView(View view) {
        zhuce_fanhui = (ImageView) view.findViewById(R.id.zhuce_fanhui);
        relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        phone_text = (TextView) view.findViewById(R.id.phone_text);
        phone_text1 = (TextView) view.findViewById(R.id.phone_text1);
        linewtext1 = (RelativeLayout) view.findViewById(R.id.linewtext1);
        account_et = (LinEditetext) view.findViewById(R.id.account_et);
        chongxinfasong = (Button) view.findViewById(R.id.chongxinfasong);
        relative11 = (RelativeLayout) view.findViewById(R.id.relative11);
        password_et = (LinEditetext) view.findViewById(R.id.password_et);
        denglu = (Button) view.findViewById(R.id.denglu);

        chongxinfasong.setOnClickListener(this);
        denglu.setOnClickListener(this);
        zhuce_fanhui.setOnClickListener(this);

        Bundle bundle = getArguments();
        mPhoneNum = bundle.getString("phoneNum");
        phone_text1.setText(mPhoneNum);

    }

    private void timeBack() {
        chongxinfasong.setEnabled(false);
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chongxinfasong:
                SMSSDK.registerEventHandler(ev);
                //发送验证
                getSecurity();
                //倒计时重新开始
                timeBack();

                break;
            case R.id.denglu:   //跳转页面
              testSecurity();

                //       tiaozhuan(new PersonInfo());
                break;
            case R.id.zhuce_fanhui:

                tiaozhuan(new PhoneZhu());

                break;
        }
    }

    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("TAG", "提交验证码成功" + data.toString());
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    String phone = (String) mData.get("phone");//返回用户注册的手机号
                    Log.e("TAG", country + "====" + phone);
                    if (phone.equals(mPhoneNum)) {
                        getActivity().runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
                            @Override
                            public void run() {
                                showDailog("恭喜你！通过验证");
                                dialog.dismiss();
                                // 记住密码   将账号密码存在数据库中 ？


                                tiaozhuan(new PersonInfo());

                 //    Toast.makeText(MainActivity.this, "通过验证", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDailog("验证失败");
                                dialog.dismiss();
                                //     Toast.makeText(MainActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
                    Log.e("TAG", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    private void showDailog(String text) {
        new AlertDialog.Builder(getActivity())
                .setTitle(text)
                .setPositiveButton("确定", null)
                .show();
    }
    /**
     * 获取验证码
     *
     * @param
     */
    public void getSecurity() {
        //发送短信，传入国家号和电话---使用SMSSDK核心类之前一定要在MyApplication中初始化，否侧不能使用
        if (TextUtils.isEmpty(mPhoneNum)) {
            Toast.makeText(getActivity(), "号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            SMSSDK.getVerificationCode("+86", mPhoneNum);
            Toast.makeText(getActivity(), "发送成功:" + mPhoneNum, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 向服务器提交验证码，在监听回调中判断是否通过验证
     *
     * @param
     */
    public void testSecurity() {
        String security = account_et.getText().toString();
        if (!TextUtils.isEmpty(security)) {
            dialog = ProgressDialog.show(getActivity(), null, "正在验证...", false, true);
            //提交短信验证码
            SMSSDK.submitVerificationCode("+86", mPhoneNum, security);//国家号，手机号码，验证码
//            Toast.makeText(this, "提交了注册信息:" + number, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "验证码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //要在activity销毁时反注册，否侧会造成内存泄漏问题
        SMSSDK.unregisterAllEventHandler();
    }

    public void tiaozhuan(Fragment f){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,f);
        fragmentTransaction.commit();
    }


}
