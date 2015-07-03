package com.yey.read.me.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yey.read.R;

import java.util.List;

public class MeServicesAdapter extends BaseAdapter{

    private Context context;
    private List<Integer> iconList;
    private List<String> textList;
    public MeServicesAdapter(Context context, List<Integer> iconList, List<String> textList) {
        this.context = context;
        this.iconList = iconList;
        this.textList = textList;
    }

    @Override
    public int getCount() {
        return iconList.size();
    }

    @Override
    public Object getItem(int position) {
        return iconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("info","getView");
        ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_me_services, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_me_functions_item);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_me_functions_item);
            viewHolder.info = (TextView) convertView.findViewById(R.id.tv_me_item_info);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.icon.setImageResource(iconList.get(position));
        String texts = textList.get(position);
        viewHolder.name.setText(texts);
        //如果需要，将texts中数据拆分到text和into中
        if(texts.contains("/")){
            String[] arr = texts.split("/");
            viewHolder.name.setText(arr[0]);
            viewHolder.info.setText(arr[1]);
        }
        //最后一个item去下划线
        if(position==getCount()-1){
            View divider = convertView.findViewById(R.id.view_me_item_line);
            divider.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{
        ImageView icon;
        TextView name;
        TextView info;
    }
}