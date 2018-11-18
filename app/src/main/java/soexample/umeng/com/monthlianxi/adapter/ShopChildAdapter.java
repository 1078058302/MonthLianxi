package soexample.umeng.com.monthlianxi.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.monthlianxi.R;
import soexample.umeng.com.monthlianxi.ShopBean;
import soexample.umeng.com.monthlianxi.mvp.view.JisuanView;

public class ShopChildAdapter extends RecyclerView.Adapter<ShopChildAdapter.ViewHolder> {
    private Context context;
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();

    @NonNull
    @Override
    public ShopChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.recycler_2_1_1, null);
        ViewHolder holder = new ViewHolder(view);
        holder.simpleDraweeView = view.findViewById(R.id.image_2);
        holder.checkBox = view.findViewById(R.id.ck_one);
        holder.title = view.findViewById(R.id.title_2);
        holder.desc = view.findViewById(R.id.desc_2);
        holder.price = view.findViewById(R.id.price_car);
        holder.jisuanView = view.findViewById(R.id.num_car);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopChildAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.price.setText(list.get(i).getPrice() + "");
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.desc.setText(list.get(i).getSubhead());
        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                listener.callBack();
                notifyDataSetChanged();
            }
        });
        viewHolder.checkBox.setChecked(list.get(i).isStatus());
        String images = list.get(i).getImages();
        String replace = images.replace("https", "http");
        String[] split = replace.split("[|]");
        viewHolder.simpleDraweeView.setImageURI(Uri.parse(split[0]));
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isStatus()) {
                    list.get(i).setStatus(false);
                    listener.callBack();
                } else {
                    list.get(i).setStatus(true);
                    listener.callBack();
                }
                notifyItemChanged(i);
            }
        });
        int num = list.get(i).getNum();
        viewHolder.jisuanView.setText(num, this, list, i);
        viewHolder.jisuanView.result(new JisuanView.CallBackListener() {
            @Override
            public void callBack() {
                listener.callBack();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setList(List<ShopBean.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        JisuanView jisuanView;
        CheckBox checkBox;
        TextView title;
        TextView desc;
        TextView price;
        SimpleDraweeView simpleDraweeView;
    }

    private CallBackListener listener;

    public void result(CallBackListener listener) {
        this.listener = listener;
    }

    public interface CallBackListener {
        void callBack();
    }
}
