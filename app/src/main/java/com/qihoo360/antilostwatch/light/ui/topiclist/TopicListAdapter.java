package com.qihoo360.antilostwatch.light.ui.topiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.mode.bean.TopicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuirongZhang
 * on 2016/11/14.
 */

public class TopicListAdapter extends BaseAdapter {

    private Context mContext;
    private List<TopicBean> mData = new ArrayList<TopicBean>();

    public TopicListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<TopicBean> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.topic_list_item, null);
            holder.name = (TextView) convertView.findViewById(R.id.topic_name_tv);
            holder.desc = (TextView) convertView.findViewById(R.id.topic_desc_tv);
            holder.url = (ImageView) convertView.findViewById(R.id.topic_image_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TopicBean bean = (TopicBean) getItem(position);
        holder.name.setText(bean.getName() + " 第" + position + "条");
        holder.desc.setText(bean.getDesc());
        Glide.with(mContext)
                .load(bean.getImageUrl())
                .fitCenter()
                .crossFade()
                .into(holder.url);
        return convertView;
    }

    static class ViewHolder {
        public TextView name;
        public TextView desc;
        public ImageView url;
    }
}
