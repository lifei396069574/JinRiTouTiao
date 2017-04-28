package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/21 21:27
 * 类的用途：
 */

public class CeLaSZ extends Fragment implements View.OnClickListener {

    private ImageView shezhi_xiao;
    private TextView te_yjfg;
    private RelativeLayout shezhi_head;
    private TextView te_lbzy;
    private TextView te_ztdx;
    private TextView te_lbypl;
    private TextView te_fwfwl;
    private TextView te_qlhc;
    private TextView te_tstz;
    private TextView te_hdxj;
    private TextView te_scszf;
    private TextView te_jcbbgx;
    private TextView te_jcyytj;
    private TextView te_sybz;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shezhi, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        shezhi_xiao = (ImageView) view.findViewById(R.id.shezhi_xiao);
        te_yjfg = (TextView) view.findViewById(R.id.te_yjfg);
        shezhi_head = (RelativeLayout) view.findViewById(R.id.shezhi_head);
        te_lbzy = (TextView) view.findViewById(R.id.te_lbzy);
        te_ztdx = (TextView) view.findViewById(R.id.te_ztdx);
        te_lbypl = (TextView) view.findViewById(R.id.te_lbypl);
        te_fwfwl = (TextView) view.findViewById(R.id.te_fwfwl);
        te_qlhc = (TextView) view.findViewById(R.id.te_qlhc);
        te_tstz = (TextView) view.findViewById(R.id.te_tstz);
        te_hdxj = (TextView) view.findViewById(R.id.te_hdxj);
        te_scszf = (TextView) view.findViewById(R.id.te_scszf);
        te_jcbbgx = (TextView) view.findViewById(R.id.te_jcbbgx);
        te_jcyytj = (TextView) view.findViewById(R.id.te_jcyytj);
        te_sybz = (TextView) view.findViewById(R.id.te_sybz);

        shezhi_xiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shezhi_xiao:
                getActivity().finish();
                break;
        }
    }
}
