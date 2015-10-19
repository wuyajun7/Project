package eshow.customviews.viewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wuyajun on 15/10/19.
 * 不可滑动的viewpager
 * scrollble：
 * false 不可滑动
 * true  可以滑动
 */
public class NoSlideViewPager extends ViewPager {

    private boolean scrollble = false;

    public NoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}
