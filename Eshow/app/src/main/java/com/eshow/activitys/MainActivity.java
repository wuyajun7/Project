package com.eshow.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eshow.R;
import com.eshow.adapters.EFragmentStatePagerAdapter;
import com.eshow.interfaces.IMainViewPagerListener;

import java.util.ArrayList;
import java.util.List;

import eshow.customviews.viewpage.NoSlideViewPager;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectView;

/**
 * Created by wuyajun on 15/10/19.
 * Detail:主界面 - 发现、我的
 */
public class MainActivity extends RoboFragmentActivity implements OnClickListener {

    @InjectView(R.id.public_center_title)
    private TextView public_center_title;

    @InjectView(R.id.tab_find_layout)
    private LinearLayout tab_find_layout;
    @InjectView(R.id.tab_add_layout)
    private LinearLayout tab_add_layout;
    @InjectView(R.id.tab_my_layout)
    private LinearLayout tab_my_layout;

    @InjectView(R.id.tab_find_img)
    private ImageView tab_find_img;
    @InjectView(R.id.tab_my_img)
    private ImageView tab_my_img;

    @InjectView(R.id.viewpager)
    private NoSlideViewPager mViewPager;

    private FragmentFind fragmentFind;
    private FragmentMy fragmentMy;

    private List<Fragment> fragmentList;

    private final int findIndex = 0;
    private final int myIndex = 1;
    private int curIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDataSetViews();
    }

    private void initViews() {
        public_center_title.setVisibility(View.VISIBLE);
        public_center_title.setText(getString(R.string.tab_find));

        tab_find_layout.setOnClickListener(this);
        tab_add_layout.setOnClickListener(this);
        tab_my_layout.setOnClickListener(this);
    }

    private void initDataSetViews() {
        fragmentList = new ArrayList<>();

        fragmentFind = new FragmentFind();
        fragmentMy = new FragmentMy();

        fragmentList.add(fragmentFind);
        fragmentList.add(fragmentMy);

        mViewPager.setAdapter(new EFragmentStatePagerAdapter(getSupportFragmentManager(),
                fragmentList,
                iMainViewPagerListener));
    }

    IMainViewPagerListener iMainViewPagerListener = new IMainViewPagerListener() {
        @Override
        public void onChange() {
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == curIndex) {
                return;
            }
            curIndex = currentItem;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_find_layout:
                changeView(findIndex);
                break;
            case R.id.tab_add_layout:
                break;
            case R.id.tab_my_layout:
                changeView(myIndex);
                break;
            default:
                break;
        }
    }

    //手动设置ViewPager要显示的视图
    private void changeView(int tabIndex) {
        if (tabIndex == findIndex) {
            tab_find_img.setImageResource(R.drawable.tab_find_down);
            tab_my_img.setImageResource(R.drawable.tab_my_up);
            public_center_title.setText(getString(R.string.tab_find));

            fragmentFind.onResume();
        } else if (tabIndex == myIndex) {
            tab_find_img.setImageResource(R.drawable.tab_find_up);
            tab_my_img.setImageResource(R.drawable.tab_my_down);
            public_center_title.setText(getString(R.string.tab_my));

            fragmentMy.onResume();
        }
        mViewPager.setCurrentItem(tabIndex, true);
    }

}
