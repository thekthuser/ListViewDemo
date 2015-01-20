package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

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

        ImageView list_image = (ImageView) convertView.findViewById(R.id.list_image);
        new ImageDownloader(list_image).execute("http://a205.phobos.apple.com/us/r30/Purple3/v4/72/4d/ae/724daeb9-07ba-2898-b924-2be4983131c5/mzl.tvvvvggs.53x53-50.png");

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(entry.name);
        title.setTag(entry.entryId);

        return convertView;

    }
}
