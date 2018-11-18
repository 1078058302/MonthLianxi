package soexample.umeng.com.monthlianxi.mvp.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.monthlianxi.MainActivity;
import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.fragment.FindFragment;
import soexample.umeng.com.monthlianxi.fragment.ShopCarFragment;
import soexample.umeng.com.monthlianxi.mvp.view.AppDelegate;

public class MainActivityPresenter extends AppDelegate {
    private ViewPager viewPager;
    private TextView shopCar;
    private TextView find;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        fragments.add(new ShopCarFragment());
        fragments.add(new FindFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(((MainActivity) context).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public void initView(ViewPager viewPager, TextView shopCar, TextView find) {
        this.viewPager = viewPager;
        this.shopCar = shopCar;
        this.find = find;
    }

    public void ShopCarClick() {
        viewPager.setCurrentItem(0);
    }

    public void FindClick() {
        viewPager.setCurrentItem(1);
    }
}
