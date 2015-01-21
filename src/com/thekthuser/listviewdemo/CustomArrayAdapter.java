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
        EntryImage[] images = entry.images;

        ImageView list_image = (ImageView) convertView.findViewById(R.id.list_image);
        //assuming images are always in ascending height here
        new ImageDownloader(list_image).execute(images[0].url);

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(entry.name);
        title.setTag(entry.entryId);

        return convertView;

    }
}
