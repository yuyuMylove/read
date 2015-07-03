package com.yey.read.me.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yey.read.R;

/**
 * Created by cm_pc2 on 2015/7/1.
 */
public class VIPPrivilegesAdapter extends BaseAdapter {
    private Context context;
    private int[] icons;
    private String[] privileges;
    public VIPPrivilegesAdapter(int[] icons, String[] privileges, Context context) {
        this.context=context;
        this.icons=icons;
        this.privileges=privileges;
    }

    @Override
    public int getCount() {
        return privileges.length;
    }

    @Override
    public Object getItem(int position) {
        return privileges[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_vip_privilege,null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_vip_privilege);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_vip_privilege);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(privileges[position]);
        viewHolder.icon.setImageResource(icons[position]);
        return convertView;
    }

    private class ViewHolder {
        ImageView icon;
        TextView name;
    }
}
