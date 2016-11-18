package com.qihoo360.antilostwatch.light.ui.postlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.mode.bean.PostBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuirongZhang on 2016/10/27.
 */

public class PostListAdapter extends BaseAdapter {

    private Context mContext;
    private List<PostBean> mData = new ArrayList<PostBean>();

    public PostListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<PostBean> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public PostBean getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.post_title_tv);
            holder.summary = (TextView) convertView.findViewById(R.id.post_summary_tv);
            holder.url = (ImageView) convertView.findViewById(R.id.post_image_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PostBean bean = getItem(position);
        if (bean != null) {
            holder.title.setText(bean.getTitle() + " 第" + position + "条");
            holder.summary.setText(bean.getSummary());
            String[] imageUrls = bean.getImageUrl();
            if (imageUrls != null) {
                Glide.with(mContext)
                        .load(imageUrls[0])
                        .fitCenter()
                        .crossFade()
                        .into(holder.url);
            }
        }
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView summary;
        ImageView url;
    }
}
