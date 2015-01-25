package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

public class ViewListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_list_item);

        //TextView id = (TextView) findViewById(R.id.display_id);
        ImageView image = (ImageView) findViewById(R.id.view_list_image);

        Bundle b = getIntent().getExtras();
        Entry entry = b.getParcelable("Entry");
        String image_url = entry.images[2].url; //assuming the third image exists and is largest


        new ImageDownloader(image).execute(image_url);
    }
}
