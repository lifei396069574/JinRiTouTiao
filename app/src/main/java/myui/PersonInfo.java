package myui;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jinritoutiao.R;

import java.io.ByteArrayOutputStream;

import view.MyImageView;
/**
 * 作者：李飞 on 2017/4/14 10:22
 * 类的用途：
 */
public class PersonInfo extends Fragment implements View.OnClickListener {
    private TextView text_gerenshezhi_wancheng;
    private MyImageView button_gerenshezhi_touxiang;
    private Bitmap mBimap;
    private EditText text_geren_nicheng;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gerenshezhi, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        text_gerenshezhi_wancheng = (TextView) view.findViewById(R.id.text_gerenshezhi_wancheng);

        button_gerenshezhi_touxiang = (MyImageView) view.findViewById(R.id.button_gerenshezhi_touxiang);
        button_gerenshezhi_touxiang.setOnClickListener(this);
        text_gerenshezhi_wancheng.setOnClickListener(this);
        text_geren_nicheng = (EditText) view.findViewById(R.id.text_geren_nicheng);
        text_geren_nicheng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_gerenshezhi_touxiang:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final String[] items = {"照相机", "本地图片", "取消"};

                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                invokecamera();
                                dialog.dismiss();
                                break;
                            case 1:
                                invokeimgages();
                                dialog.dismiss();
                                break;
                            case 2:
                                dialog.dismiss();
                                break;
                        }

                    }
                });

                builder.create().show();

                break;

            case R.id.text_gerenshezhi_wancheng:
                //    mBimap  将照片回传

                String nicheng = text_geren_nicheng.getText().toString();
                if (TextUtils.isEmpty(nicheng)){
                    Toast.makeText(getActivity(),"请填写昵称",Toast.LENGTH_SHORT).show();
                            break;
                }

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("person", Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("is",true);
                editor.putString("nicheng",nicheng);
                editor.putString("zhaopian",convertIconToString(mBimap));
                editor.commit();


                getActivity().finish();

                break;
        }
    }

    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }



    public void invokeimgages() {
        //调用系统相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 200);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 200) {
            //得到像册中图片的地址
            Uri uri = data.getData();
//			img.setImageURI(uri);
            crop(uri);
        } else if (requestCode == 9999) {
            //得到裁剪后的照片
            mBimap = data.getParcelableExtra("data");
            button_gerenshezhi_touxiang.setImageBitmap(mBimap);
        } else if (requestCode == 100) {
            //得到照片
            mBimap = data.getParcelableExtra("data");

            button_gerenshezhi_touxiang.setImageBitmap(mBimap);

            super.onActivityResult(requestCode, resultCode, data);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    private void crop(Uri uri) {

        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //是否裁剪
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别

        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 9999);
    }

    public void invokecamera() {
        //启动照像机组件
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(intent, 100);

    }


    private void submit() {
        // validate
        String nicheng = text_geren_nicheng.getText().toString().trim();
        if (TextUtils.isEmpty(nicheng)) {
            Toast.makeText(getContext(), "昵称", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
