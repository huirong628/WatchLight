package com.qihoo360.antilostwatch.light.ui.postlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qihoo360.antilostwatch.light.R;
import com.qihoo360.antilostwatch.light.data.bean.PostBean;

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.post_title_tv);
            holder.summary = (TextView) convertView.findViewById(R.id.post_summary_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PostBean bean = (PostBean) getItem(position);
        holder.title.setText(bean.getTitle());
        holder.summary.setText(bean.getSummary());
        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView summary;
    }
}
