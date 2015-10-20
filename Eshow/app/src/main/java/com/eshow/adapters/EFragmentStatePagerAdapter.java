package com.eshow.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.eshow.interfaces.IMainViewPagerListener;

import java.util.List;

/**
 * Created by wuyajun on 15/10/20.
 * Detail:定义自己的ViewPager适配器,也可以使用FragmentPagerAdapter
 */
public class EFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private IMainViewPagerListener iMainViewPagerListener;

    public EFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public EFragmentStatePagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, IMainViewPagerListener viewPagerListener) {
        super(fragmentManager);
        mFragmentList = fragmentList;
        iMainViewPagerListener = viewPagerListener;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //每次更新完成ViewPager的内容后，调用该接口，此处复写主要是为了让导航按钮上层的覆盖层能够动态的移动
    @Override
    public void finishUpdate(ViewGroup container) {
        //这句话要放在最前面，否则会报错
        super.finishUpdate(container);
        //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应的覆盖层所在的位置
        iMainViewPagerListener.onChange();
    }
}
