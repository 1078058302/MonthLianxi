package soexample.umeng.com.monthlianxi.mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.ShopBean;
import soexample.umeng.com.monthlianxi.adapter.ShopChildAdapter;

public class JisuanView extends RelativeLayout implements View.OnClickListener {
    //http://www.zhaoapi.cn/product/updateCarts?uid=71&sellerid=1&pid=1&selected=1&num=10&token=C642430271C66ED4E1B19DEC1748BECC

    private TextView add;
    private TextView jian;
    private EditText jisuan;
    private ShopChildAdapter shopChildAdapter;
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
    private int position;

    public JisuanView(Context context) {
        super(context);
        init(context);
    }

    public JisuanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public JisuanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Context context;
    private int num;

    private void init(Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.jisuanview, null);
        add = view.findViewById(R.id.add);
        jian = view.findViewById(R.id.jian);
        jisuan = view.findViewById(R.id.edit_jisuan);
        jian.setOnClickListener(this);
        add.setOnClickListener(this);
        jisuan.setOnClickListener(this);
        addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                num++;
                list.get(position).setNum(num);
                jisuan.setText(num+"");
                listener.callBack();
                break;
            case R.id.jian:
                num--;
                list.get(position).setNum(num);
                jisuan.setText(num+"");
                listener.callBack();
                break;
            case R.id.edit_jisuan:
                break;
        }
    }


    public void setText(int num, ShopChildAdapter shopChildAdapter, List<ShopBean.DataBean.ListBean> list, int i) {
        this.num = num;
        this.shopChildAdapter = shopChildAdapter;
        this.list = list;
        this.position = i;
        jisuan.setText(num+"");
    }


    private CallBackListener listener;

    public void result(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}
