package com.yztc.damaiproject.ui.topic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yztc.damaiproject.R;
import com.yztc.damaiproject.image.ImageLoader;
import com.yztc.damaiproject.net.NetConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wanggang on 2017/3/13.
 */

public class DaMaiAdapter extends RecyclerView.Adapter<DaMaiAdapter.DaMaiHolder> {


    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<TopicBean> data;

    public DaMaiAdapter(Context context, ArrayList<TopicBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DaMaiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_topic_damai, parent, false);
        return new DaMaiHolder(view);
    }

    @Override
    public void onBindViewHolder(DaMaiHolder holder, int position) {
        TopicBean bean = data.get(position);
        holder.itemTopicTitle.setText(bean.getN()+bean.getV());

        String i = bean.getI()+"";
        String imageURI = NetConfig.BASR_IMG + i.substring(0, i.length()-2) + "/" + i + "_n.jpg";

        ImageLoader.load(context,imageURI,holder.itemTopicIcon);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class DaMaiHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_topic_icon)
        ImageView itemTopicIcon;
        @BindView(R.id.item_topic_title)
        TextView itemTopicTitle;

        public DaMaiHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
