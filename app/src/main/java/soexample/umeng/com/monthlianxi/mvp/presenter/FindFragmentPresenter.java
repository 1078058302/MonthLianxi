package soexample.umeng.com.monthlianxi.mvp.presenter;

import android.content.Context;

import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.mvp.view.AppDelegate;

public class FindFragmentPresenter extends AppDelegate {
    private Context conText;

    @Override
    public int getLayoutId() {
        return R.layout.find_layout;
    }

    public void setConText(Context conText) {
        this.conText = conText;
    }
}
