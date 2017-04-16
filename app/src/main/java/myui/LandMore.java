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

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/13 14:22
 * 类的用途：
 */

public class LandMore extends Fragment implements View.OnClickListener {
    private ImageButton button_landmore_xiao;
    private Button button_phone_land;
    private Button button_phone_zhu;
    private ImageButton landmore_qq;
    private ImageButton landmore_wei;
    private ImageButton landmore_ten;
    private ImageButton landmore_ren;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.avtivity_load,null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        button_landmore_xiao = (ImageButton)view.findViewById(R.id.button_landmore_xiao);
        button_phone_land = (Button) view.findViewById(R.id.button_phone_land);
        button_phone_zhu = (Button) view.findViewById(R.id.button_phone_zhu);
        landmore_qq = (ImageButton) view.findViewById(R.id.landmore_qq);
        landmore_wei = (ImageButton) view.findViewById(R.id.landmore_wei);
        landmore_ten = (ImageButton) view.findViewById(R.id.landmore_ten);
        landmore_ren = (ImageButton) view.findViewById(R.id.landmore_ren);

        button_landmore_xiao.setOnClickListener(this);
        button_phone_land.setOnClickListener(this);
        button_phone_zhu.setOnClickListener(this);
        landmore_qq.setOnClickListener(this);
        landmore_wei.setOnClickListener(this);
        landmore_ten.setOnClickListener(this);
        landmore_ren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_landmore_xiao:
                getActivity().finish();
                break;
            case R.id.button_phone_land:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout,new PhoneLand());
                fragmentTransaction.commit();
                break;
            case R.id.button_phone_zhu:
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout,new PhoneZhu());
                fragmentTransaction2.commit();

                break;
            case R.id.landmore_qq:

                break;
            case R.id.landmore_wei:

                break;
            case R.id.landmore_ten:

                break;
            case R.id.landmore_ren:

                break;
        }
    }
}
