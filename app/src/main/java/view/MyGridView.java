package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者：李飞 on 2017/4/12 19:50
 * 类的用途：
 */

public class MyGridView extends GridView {
    public MyGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
