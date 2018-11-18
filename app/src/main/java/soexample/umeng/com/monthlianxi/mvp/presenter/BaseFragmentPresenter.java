package soexample.umeng.com.monthlianxi.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.monthlianxi.mvp.view.AppDelegate;

public abstract class BaseFragmentPresenter<T extends AppDelegate> extends Fragment {
    public T delegate;
    private Unbinder bind;

    public abstract Class<T> getClassDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            delegate = getClassDelegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        delegate.create(inflater, container, savedInstanceState);
        View view = delegate.rootView();
        bind = ButterKnife.bind(this, view);
        initView();
        getContext(getActivity());
        return view;
    }

    public void initView() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        delegate.initData();
    }

    public void getContext(Context context) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        delegate.destroy();
    }
}

