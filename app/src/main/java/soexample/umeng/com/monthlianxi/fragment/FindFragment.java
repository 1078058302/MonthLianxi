package soexample.umeng.com.monthlianxi.fragment;

import android.content.Context;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.monthlianxi.mvp.presenter.BaseFragmentPresenter;
import soexample.umeng.com.monthlianxi.mvp.presenter.FindFragmentPresenter;

public class FindFragment extends BaseFragmentPresenter<FindFragmentPresenter> {

    private Unbinder bind;

    @Override
    public Class<FindFragmentPresenter> getClassDelegate() {
        return FindFragmentPresenter.class;
    }


    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setConText(context);
    }
}
