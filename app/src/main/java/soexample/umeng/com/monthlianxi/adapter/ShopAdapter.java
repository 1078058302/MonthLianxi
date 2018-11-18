package soexample.umeng.com.monthlianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.ShopBean;
import soexample.umeng.com.monthlianxi.mvp.view.JisuanView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private List<ShopBean.DataBean> list = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.shop_layout, null);
        ViewHolder holder = new ViewHolder(view);
        holder.textView = view.findViewById(R.id.shopFamily);
        holder.recyclerView = view.findViewById(R.id.recycler1);
        holder.checkBox = view.findViewById(R.id.check);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(list.get(i).getSellerName());
        final List<ShopBean.DataBean.ListBean> shopchild = this.list.get(i).getList();
        ShopChildAdapter adapter = new ShopChildAdapter();
        viewHolder.checkBox.setChecked(list.get(i).isCheck());
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                for (int j = 0; j < shopchild.size(); j++) {
                    if (shopchild.get(j).isStatus()) {
                        shopchild.get(j).setStatus(false);
                    } else {
                        shopchild.get(j).setStatus(true);
                    }
                }
                listener.callBack(list);
                notifyItemChanged(i);
            }
        });
        adapter.setContext(context);
        adapter.setList(shopchild);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(manager);
        viewHolder.recyclerView.setAdapter(adapter);
        adapter.result(new ShopChildAdapter.CallBackListener() {
            @Override
            public void callBack() {
                listener.callBack(list);
                for (int j = 0; j < list.size(); j++) {
                    List<ShopBean.DataBean.ListBean> list1 = ShopAdapter.this.list.get(j).getList();
                    if (list1.isEmpty()) {
                        list.remove(j);
                    }
                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<ShopBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        CheckBox checkBox;
        TextView textView;
        RecyclerView recyclerView;
    }

    private CallBackListener listener;

    public void result(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack(List<ShopBean.DataBean> list);
    }
}
