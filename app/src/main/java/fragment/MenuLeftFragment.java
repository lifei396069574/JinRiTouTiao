package fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.Main2Activity;
import com.example.administrator.jinritoutiao.R;

import java.util.ArrayList;
import java.util.List;

import bean.Bean;

/**
 * 作者：李飞 on 2017/4/8 11:27
 * 类的用途：
 */


public class MenuLeftFragment extends Fragment implements View.OnClickListener {
    private ListView mListView;
    private List<Bean> mList=new ArrayList<>();
    private TextView mLandmore;
    private RelativeLayout mR1;
    private RelativeLayout mR2;
    private ImageView mTouxiang;
    private TextView mNi;
    private SharedPreferences mPerson;
    private Button mNight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_menu, container, false);

        mR1 = (RelativeLayout) view.findViewById(R.id.cela_title);
        mR2 = (RelativeLayout) view.findViewById(R.id.cela_title2);
        mTouxiang = (ImageView) view.findViewById(R.id.image_cehua_touxiang);
        mTouxiang.setOnClickListener(this);
        mNi = (TextView) view.findViewById(R.id.text_cela_nicheng);

        mPerson = getActivity().getSharedPreferences("person", getActivity().MODE_WORLD_READABLE | getActivity().MODE_WORLD_WRITEABLE);

        panduantouxian(mR1, mR2, mTouxiang, mNi);

        Log.i("iii","走了一次");

        return view;
    }

    private void panduantouxian(RelativeLayout r1, RelativeLayout r2, ImageView touxiang, TextView ni) {

        boolean is = mPerson.getBoolean("is", false);
        Log.i("iii",is+"");
        String nicheng = mPerson.getString("nicheng", "昵称");
        String tu = mPerson.getString("zhaopian", "");
        Bitmap bitmap = convertStringToIcon(tu);
        if (is){
            r1.setVisibility(View.GONE);
            r2.setVisibility(View.VISIBLE);
            touxiang.setImageBitmap(bitmap);
            ni.setText(nicheng);
        }else {
            r2.setVisibility(View.GONE);
            r1.setVisibility(View.VISIBLE);
        }
    }


    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initData();

        mListView.setAdapter(new MyBaseAdapter());

    }
    private void initData() {
        mList.add(new Bean(R.mipmap.dynamicicon_leftdrawer,"好友动态"));
        mList.add(new Bean(R.mipmap.topicicon_leftdrawer,"我的话题"));
        mList.add(new Bean(R.mipmap.ic_action_favor_on_normal,"收藏"));
        mList.add(new Bean(R.mipmap.activityicon_leftdrawer,"活动"));
        mList.add(new Bean(R.mipmap.sellicon_leftdrawer,"商城"));
        mList.add(new Bean(R.mipmap.feedbackicon_leftdrawer,"反馈"));
        mList.add(new Bean(android.R.drawable.sym_action_chat,"我要爆料"));


    }

    private void initView() {

        mListView = (ListView)getView().findViewById(R.id.main_listview);
        mLandmore = (TextView) getView().findViewById(R.id.text_landmore);
        mNight = (Button) getView().findViewById(R.id.night);
        mLandmore.setOnClickListener(this);
        mNight.setOnClickListener(this);

    }
    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView ==null){
                convertView=View.inflate(getActivity(),R.layout.lv_item,null);
                viewHolder=new ViewHolder();
                viewHolder.mImageView= (ImageView) convertView.findViewById(R.id.lv_image);
                viewHolder.mTextView= (TextView) convertView.findViewById(R.id.lv_text);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mList.get(position).getName());
            viewHolder.mImageView.setImageResource(mList.get(position).getImage());
            return convertView;
        }
    }
    class ViewHolder{
        TextView mTextView;
        ImageView mImageView;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.text_landmore:
                startActivity(new Intent(getActivity(), Main2Activity.class));
                break;
            case R.id.image_cehua_touxiang:

                Log.i("fff","点击了");
               //清空 存储
                SharedPreferences.Editor editor = mPerson.edit();
                editor.clear();
                editor.commit();
                panduantouxian(mR1, mR2, mTouxiang, mNi);
                break;
            case R.id.night:
                //夜间模式
                int i = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (i==Configuration.UI_MODE_NIGHT_NO){
                    mNight.setText("白天模式");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    mNight.setText("夜间模式");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }


                break;
        }
    }



    @Override
    public void onPause(){
        super.onPause();
    //    Log.i("iii","onPause");
    }

    @Override
    public void onResume(){
        super.onResume();
     //   Log.i("iii","onResume");

        panduantouxian(mR1, mR2, mTouxiang, mNi);

    }




}

