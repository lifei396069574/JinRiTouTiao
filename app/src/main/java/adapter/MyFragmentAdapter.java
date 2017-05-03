package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import fragment.TestFragment;

/**
 * 作者：李飞 on 2017/4/12 13:58
 * 类的用途：
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<String> mList_title;
    private List<String> mList_uri;

    public MyFragmentAdapter(FragmentManager fm,  List<String> mList_uri , List<String> mList_title  ) {
        super(fm);
        this.mList_title=mList_title;
        this.mList_uri=mList_uri;
    }
    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(mList_uri.get(position % mList_uri.size()));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title.get(position % mList_title.size()).toUpperCase();
    }


    @Override
    public int getCount() {
        return mList_uri.size();
    }

}
