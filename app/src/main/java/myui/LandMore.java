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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * 作者：李飞 on 2017/4/13 14:22
 * 类的用途：
 */

public class LandMore extends Fragment implements View.OnClickListener {
    private ImageView button_landmore_xiao;
    private Button button_phone_land;
    private Button button_phone_zhu;
    private ImageView landmore_qq;
    private ImageView landmore_wei;
    private ImageView landmore_ten;
    private ImageView landmore_ren;
    private UMAuthListener mAuthListener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.avtivity_load,null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        button_landmore_xiao = (ImageView)view.findViewById(R.id.button_landmore_xiao);
        button_phone_land = (Button) view.findViewById(R.id.button_phone_land);
        button_phone_zhu = (Button) view.findViewById(R.id.button_phone_zhu);
        landmore_qq = (ImageView) view.findViewById(R.id.landmore_qq);
        landmore_wei = (ImageView) view.findViewById(R.id.landmore_wei);
        landmore_ten = (ImageView) view.findViewById(R.id.landmore_ten);
        landmore_ren = (ImageView) view.findViewById(R.id.landmore_ren);

        button_landmore_xiao.setOnClickListener(this);
        button_phone_land.setOnClickListener(this);
        button_phone_zhu.setOnClickListener(this);
        landmore_qq.setOnClickListener(this);
        landmore_wei.setOnClickListener(this);
        landmore_ten.setOnClickListener(this);
        landmore_ren.setOnClickListener(this);


        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.isOpenShareEditActivity(true);
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
        UMShareAPI.get(getActivity()).setShareConfig(config);
        mAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //授权开始的回调
            }
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();

                String name1 = data.get("name");
                String url = data.get("iconurl");

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("person", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("is",true);
                editor.putString("nicheng",name1);
                editor.putString("image",url);
                editor.commit();

                getActivity().finish();

            }
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText( getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getActivity(), "取消授权", Toast.LENGTH_SHORT).show();
            }
        };



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_landmore_xiao:
                getActivity().finish();
                break;
            case R.id.button_phone_land:

                PhoneLand phoneLand = new PhoneLand();
                Bundle bundle =new Bundle();
                bundle.putInt("num",1);
                phoneLand.setArguments(bundle);
                tiaozhuan(phoneLand);

                break;
            case R.id.button_phone_zhu:

                PhoneZhu phoneZhu = new PhoneZhu();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("num",1);
                phoneZhu.setArguments(bundle2);
                tiaozhuan(phoneZhu);

                break;
            case R.id.landmore_qq:

                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ ,mAuthListener);

                break;
            case R.id.landmore_wei:

                break;
            case R.id.landmore_ten:

                break;
            case R.id.landmore_ren:

                tiaozhuan(new Login_ys());
                break;
        }
    }

    public void tiaozhuan(Fragment f) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, f);
        fragmentTransaction.commit();
    }

}
