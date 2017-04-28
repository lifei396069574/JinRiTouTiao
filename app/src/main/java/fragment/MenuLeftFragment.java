package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.Main2Activity;
import com.example.administrator.jinritoutiao.MainActivity;
import com.example.administrator.jinritoutiao.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Bean;
import utils.UiUtils;

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
    private TextView mNight;
    private ImageView mImage_qq;
    private UMAuthListener mAuthListener;
    private TextView mSetting;
    private TextView mOff_line;


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

        return view;
    }

    private void panduantouxian(RelativeLayout r1, RelativeLayout r2, ImageView touxiang, TextView ni) {

        boolean is = mPerson.getBoolean("is", false);
        String nicheng = mPerson.getString("nicheng", "昵称");
        String tu = mPerson.getString("zhaopian", "");
        Bitmap bitmap = convertStringToIcon(tu);
        String imagehttp = mPerson.getString("image", "");

        if (is){
            r1.setVisibility(View.GONE);
            r2.setVisibility(View.VISIBLE);
            if (!tu.equals("")){
                touxiang.setImageBitmap(bitmap);
            }else {
                ImageLoader.getInstance().displayImage(imagehttp,touxiang);
            }
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("what",position);
                startActivity(intent);
            }
        });

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
        mNight = (TextView) getView().findViewById(R.id.night);
        mImage_qq = (ImageView) getView().findViewById(R.id.image_qq);
        mSetting = (TextView) getView().findViewById(R.id.setting);
        mOff_line = (TextView) getView().findViewById(R.id.off_line);

        mLandmore.setOnClickListener(this);
        mImage_qq.setOnClickListener(this);
        mNight.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mOff_line.setOnClickListener(this);

        login_qq();

    }

    private void login_qq() {
        //第三方登陆
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

                panduantouxian(mR1, mR2, mTouxiang, mNi);

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
        Intent intent = new Intent(getActivity(), Main2Activity.class);
        switch (v.getId()){
            case R.id.text_landmore:
                intent.putExtra("what",8);
                startActivity(intent);
                break;
            case R.id.image_cehua_touxiang:
               //清空 存储
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("person", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("is",false);
                editor.commit();
                panduantouxian(mR1, mR2, mTouxiang, mNi);
                break;
            case R.id.night:
                //夜间模式
                UiUtils.switchAppTheme(getActivity());
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.reload();
                break;
            case R.id.image_qq:
                //第三方登陆
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ ,mAuthListener);
                break;
            case R.id.setting:
                //设置
                intent.putExtra("what",9);
                startActivity(intent);
                break;
            case R.id.off_line:
                //离线下载
                intent.putExtra("what",7);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        panduantouxian(mR1, mR2, mTouxiang, mNi);
    }


}

