package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jinritoutiao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.JsonBean;

/**
 * date:${DATA}
 * author:孟淑英
 * function:
 */
public class MyListViewAdapter extends BaseAdapter{
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    private final Context mContext;
    private final List<JsonBean.ResultBean.DataBean> mList;

    public MyListViewAdapter(Context context, List<JsonBean.ResultBean.DataBean> list){
        mContext =context;
        mList =list;
    }
    @Override
    public int getCount(){
        return mList.size();
    }
    @Override
    public Object getItem(int position){
        return mList.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;

    }
    @Override
    public int getViewTypeCount() {
        return 3;}
    @Override
    public int getItemViewType(int position){
         int index=position%3;
         switch(index){
             case 0:
                 return TYPE_1;
             case 1:
                 return TYPE_2;
             case 2:
                 return TYPE_3;
         }

        return super.getItemViewType(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        int itemViewType = getItemViewType(position);
        ViewHolder1 viewholder1;
        ViewHolder2 viewholder2;
        ViewHolder3 viewholder3;
        switch(itemViewType){
            case TYPE_1:
              if(convertView==null){
                  convertView=View.inflate(mContext, R.layout.title1_layout,null);
                  viewholder1=new ViewHolder1();
                  viewholder1.imageview1= (ImageView) convertView.findViewById(R.id.title1_imageview1);
                  viewholder1.imageview2= (ImageView) convertView.findViewById(R.id.title1_imageview2);
                  viewholder1.imageview3= (ImageView) convertView.findViewById(R.id.title1_imageview3);
                  viewholder1.textview1= (TextView) convertView.findViewById(R.id.title1_textview1);
                  viewholder1.textview2= (TextView) convertView.findViewById(R.id.title1_textview2);
                  convertView.setTag(viewholder1);
              }else{
                  viewholder1= (ViewHolder1) convertView.getTag();
               }
                ImageLoader.getInstance().displayImage(mList.get(position).getThumbnail_pic_s(),viewholder1.imageview1);
                ImageLoader.getInstance().displayImage(mList.get(position).getThumbnail_pic_s02(),viewholder1.imageview2);
                ImageLoader.getInstance().displayImage(mList.get(position).getThumbnail_pic_s03(),viewholder1.imageview3);
                viewholder1.textview1.setText(mList.get(position).getTitle());
                viewholder1.textview2.setText(mList.get(position).getAuthor_name());
                break;
            case TYPE_2:
                if(convertView==null){
                    convertView=View.inflate(mContext, R.layout.title2_layout,null);
                    viewholder2=new ViewHolder2();
                    viewholder2.imageview1= (ImageView) convertView.findViewById(R.id.title2_imageview);
                    viewholder2.textview1= (TextView) convertView.findViewById(R.id.title2_textview1);
                    viewholder2.textview2= (TextView) convertView.findViewById(R.id.title2_textview2);
                    viewholder2.textview3= (TextView) convertView.findViewById(R.id.title2_textview3);
                    convertView.setTag(viewholder2);
                }else{
                    viewholder2= (ViewHolder2) convertView.getTag();
                }
                ImageLoader.getInstance().displayImage(mList.get(position).getThumbnail_pic_s(),viewholder2.imageview1);
                viewholder2.textview1.setText(mList.get(position).getTitle());
                viewholder2.textview2.setText(mList.get(position).getAuthor_name());
                viewholder2.textview3.setText(mList.get(position).getDate());
                break;
            case TYPE_3:
                if(convertView==null){
                    convertView=View.inflate(mContext, R.layout.title3_layout,null);
                    viewholder3=new ViewHolder3();
                    viewholder3.imageview1= (ImageView) convertView.findViewById(R.id.title3_imageview);
                    viewholder3.textview1= (TextView) convertView.findViewById(R.id.title3_textview1);
                    viewholder3.textview2= (TextView) convertView.findViewById(R.id.title3_textview2);
                    viewholder3.textview3= (TextView) convertView.findViewById(R.id.title3_textview3);

                    convertView.setTag(viewholder3);
                }else{
                    viewholder3= (ViewHolder3) convertView.getTag();
                }
                ImageLoader.getInstance().displayImage(mList.get(position).getThumbnail_pic_s02(),viewholder3.imageview1);
                viewholder3.textview1.setText(mList.get(position).getTitle());
                viewholder3.textview2.setText(mList.get(position).getAuthor_name());
                viewholder3.textview3.setText(mList.get(position).getDate());

                break;
        }
           return convertView;
    }
    class ViewHolder1{
        TextView textview1;
        TextView textview2;
        ImageView imageview1;
        ImageView imageview2;
        ImageView imageview3;
     }
    class ViewHolder2{
        TextView textview1;
        TextView textview2;
        TextView textview3;
        ImageView imageview1;
     }
     class ViewHolder3{
         TextView textview1;
         TextView textview2;
         TextView textview3;
         ImageView imageview1;
      }

}
