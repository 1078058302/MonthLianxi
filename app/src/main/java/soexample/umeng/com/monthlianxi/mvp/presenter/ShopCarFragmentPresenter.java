package soexample.umeng.com.monthlianxi.mvp.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.ShopBean;
import soexample.umeng.com.monthlianxi.adapter.ShopAdapter;
import soexample.umeng.com.monthlianxi.mvp.view.AppDelegate;
import soexample.umeng.com.monthlianxi.net.HttpHelper;
import soexample.umeng.com.monthlianxi.net.HttpListener;

public class ShopCarFragmentPresenter extends AppDelegate {
    private RecyclerView recyclerView;
    private Button button;
    private CheckBox checkBox;
    private ShopAdapter shopAdapter;
    private boolean b = true;
    private List<ShopBean.DataBean> data1 = new ArrayList<>();
    private int allnum = 0;
    private double allprice = 0;
    private TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.shopcar_layout;
    }

    @Override
    public void initData() {
        super.initData();
        shopAdapter = new ShopAdapter();
        shopAdapter.setContext(context);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(shopAdapter);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b) {
                    allCheck(true);
                    b = false;
                } else {
                    allCheck(false);
                    b = true;
                }
            }
        });
        doHttp();
    }

    private void allCheck(boolean b) {
        allnum = 0;
        allprice = 0;
        for (int i = 0; i < data1.size(); i++) {
            List<ShopBean.DataBean.ListBean> list = data1.get(i).getList();
            data1.get(i).setCheck(b);
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setStatus(b);
                int num = list.get(j).getNum();
                double price = list.get(j).getPrice();
                double aprice = num * price;
                allprice += aprice;
                allnum += num;
            }
        }
        if (b) {
            textView.setText(allprice + "");
            button.setText("结算(" + allnum + ")");
        } else {
            textView.setText("00.00");
            button.setText("结算(0)");
        }
        shopAdapter.notifyDataSetChanged();
    }

    private void doHttp() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", "71");
        new HttpHelper().get("/product/getCarts", map).result(new HttpListener() {
            @Override
            public void success(String data) {
//                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
                ShopBean shopBean = new Gson().fromJson(data, ShopBean.class);
                data1 = shopBean.getData();
                data1.remove(0);
                shopAdapter.setList(data1);
                method();
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private void method() {

        shopAdapter.result(new ShopAdapter.CallBackListener() {
            @Override
            public void callBack(List<ShopBean.DataBean> list) {
                double allprice = 0;
                int allnum = 0;
                int numAll = 0;
                int num2;
                for (int i = 0; i < list.size(); i++) {
                    List<ShopBean.DataBean.ListBean> list1 = list.get(i).getList();
                    num2 = 0;
                    for (int j = 0; j < list1.size(); j++) {
                        numAll += list1.get(j).getNum();
                        if (list1.get(j).isStatus()) {
                            num2++;
                            double price = list1.get(j).getPrice();
                            int num = list1.get(j).getNum();
                            double v = num * price;
                            allprice += v;
                            allnum += num;
                        }
                    }
                    if (list1.size() == num2) {
                        list.get(i).setCheck(true);
                    } else {
                        list.get(i).setCheck(false);
                    }
                }
                if (allnum < numAll) {
                    checkBox.setChecked(false);
                    b = true;
                } else {
                    checkBox.setChecked(true);
                    b = false;
                }
                shopAdapter.notifyDataSetChanged();
                textView.setText(allprice + "");
                button.setText("结算(" + allnum + ")");
            }
        });
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void initView(RecyclerView recyclerView, Button button, CheckBox checkBox, TextView textView) {
        this.recyclerView = recyclerView;
        this.button = button;
        this.checkBox = checkBox;
        this.textView = textView;
    }
}
