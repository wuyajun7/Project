package com.eshow.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eshow.R;

import java.util.ArrayList;
import java.util.List;

import eshow.customviews.viewpage.NoSlideViewPager;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private final String TITLE_HOME = "首页";
    private final String TITLE_MY = "我de";

    private LinearLayout layout_pub_title;
    private TextView public_center_title;

    private LinearLayout tab_home_layout,
            tab_add_layout,
            tab_my_layout;
    private ImageView tab_home_img,
            tab_my_img;

    private NoSlideViewPager mViewPager;

    private TestFragment oneFragment;
    private Test1Fragment threeFragment;

    private List<Fragment> fragmentList;

    //当前选中的项
    private int currenttab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();

        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));
    }

    private void initViews() {
        layout_pub_title = (LinearLayout) findViewById(R.id.home_title_layout);
        public_center_title = (TextView) layout_pub_title.findViewById(R.id.public_center_title);
        public_center_title.setVisibility(View.VISIBLE);
        public_center_title.setText("首页");

        mViewPager = (NoSlideViewPager) findViewById(R.id.viewpager);

        tab_home_layout = (LinearLayout) findViewById(R.id.tab_home_layout);
        tab_add_layout = (LinearLayout) findViewById(R.id.tab_add_layout);
        tab_my_layout = (LinearLayout) findViewById(R.id.tab_my_layout);

        tab_home_layout.setOnClickListener(this);
        tab_add_layout.setOnClickListener(this);
        tab_my_layout.setOnClickListener(this);

        tab_home_img = (ImageView) findViewById(R.id.tab_home_img);
        tab_my_img = (ImageView) findViewById(R.id.tab_my_img);
    }

    private void initDatas() {
        fragmentList = new ArrayList<Fragment>();

        oneFragment = new TestFragment();
        threeFragment = new Test1Fragment();

        fragmentList.add(oneFragment);
        fragmentList.add(threeFragment);
    }

    /**
     * 定义自己的ViewPager适配器。
     * 也可以使用FragmentPagerAdapter。关于这两者之间的区别，可以自己去搜一下。
     */
    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * 每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
         */
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);//这句话要放在最前面，否则会报错
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab) {
                return;
            }
            currenttab = currentItem;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home_layout:
                changeView(0);
                break;
            case R.id.tab_add_layout:
                break;
            case R.id.tab_my_layout:
                changeView(1);
                break;
            default:
                break;
        }
    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab) {
        if (desTab == 0) {
            tab_home_img.setImageResource(R.drawable.tab_home_down);
            tab_my_img.setImageResource(R.drawable.tab_my_up);
            public_center_title.setText(TITLE_HOME);

            oneFragment.onResume();
        } else if (desTab == 1) {
            tab_home_img.setImageResource(R.drawable.tab_home_up);
            tab_my_img.setImageResource(R.drawable.tab_my_down);
            public_center_title.setText(TITLE_MY);

            threeFragment.onResume();
        }

        mViewPager.setCurrentItem(desTab, true);
    }

}
