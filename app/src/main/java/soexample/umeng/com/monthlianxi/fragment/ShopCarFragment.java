package soexample.umeng.com.monthlianxi.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.mvp.presenter.BaseFragmentPresenter;
import soexample.umeng.com.monthlianxi.mvp.presenter.ShopCarFragmentPresenter;

public class ShopCarFragment extends BaseFragmentPresenter<ShopCarFragmentPresenter> {
    @BindView(R.id.recyc)
    RecyclerView recyclerView;
    @BindView(R.id.go)
    Button button;
    @BindView(R.id.ck_all)
    CheckBox checkBox;
    @BindView(R.id.price)
    TextView textView;

    @Override
    public Class<ShopCarFragmentPresenter> getClassDelegate() {
        return ShopCarFragmentPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        delegate.initView(recyclerView, button, checkBox, textView);
    }

    @Override
    public void getContext(Context context) {
        super.getContext(context);
        delegate.setContext(context);
    }
}
