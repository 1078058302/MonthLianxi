package soexample.umeng.com.monthlianxi;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import soexample.umeng.com.monthlianxi.mvp.presenter.BaseActivityPresenter;
import soexample.umeng.com.monthlianxi.mvp.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter> {

    @Override
    public Class<MainActivityPresenter> getClassDelegate() {
        return MainActivityPresenter.class;
    }

    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.shopCar)
    TextView shopCar;
    @BindView(R.id.find)
    TextView find;

    @Override
    public void initView() {
        super.initView();
        delegate.initView(viewPager, shopCar, find);
    }

    @OnClick({R.id.shopCar, R.id.find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopCar:
                delegate.ShopCarClick();
                break;
            case R.id.find:
                delegate.FindClick();
                break;
        }

    }


    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
