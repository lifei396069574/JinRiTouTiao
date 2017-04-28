package myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.administrator.jinritoutiao.R;

/**
 * 作者：李飞 on 2017/4/13 19:17
 * 类的用途：
 */

public class ForgotPwd extends Fragment implements View.OnClickListener, TextWatcher {
    private ImageView button_for_xiao;
    private EditText editText;
    private ImageButton button_for_x;
    private Button button_for_xia;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wangjimima, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        button_for_xiao = (ImageView) view.findViewById(R.id.button_for_xiao);
        editText = (EditText)  view.findViewById(R.id.editText);
        button_for_x = (ImageButton)  view.findViewById(R.id.button_for_x);
        button_for_xia = (Button)  view.findViewById(R.id.button_for_xia);

        button_for_xiao.setOnClickListener(this);
        button_for_x.setOnClickListener(this);
        button_for_xia.setOnClickListener(this);
        editText.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_for_xiao:
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.framelayout,new PhoneLand());
                fragmentTransaction2.commit();
                break;
            case R.id.button_for_x:
                    editText.setText("");
                    button_for_x.setVisibility(View.GONE);
                break;
            case R.id.button_for_xia:

                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        button_for_x.setVisibility(View.VISIBLE);
    }
}
