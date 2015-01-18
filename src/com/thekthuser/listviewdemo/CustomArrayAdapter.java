package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<Entry> {
    Context mContext;
    int layoutResourceId;
    Entry data[] = null;

    public CustomArrayAdapter(Context mContext, int layoutResourceId, Entry[] data) {
        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        Entry entry = data[position];

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(entry.name);
        title.setTag(entry.entryId);

        return convertView;

    }
}
