package com.github.xiaogegechen.skinning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> mList;

    public MyAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        String text = mList.get(position);
        TextView textView = convertView.findViewById(R.id.list_view_item_text_view);
        textView.setText(text);
        return convertView;
    }
}
