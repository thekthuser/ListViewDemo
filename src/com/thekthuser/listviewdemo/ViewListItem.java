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

        TextView title = (TextView) findViewById(R.id.display_title);
        TextView summary = (TextView) findViewById(R.id.display_summary);
        TextView price = (TextView) findViewById(R.id.display_price);
        TextView date = (TextView) findViewById(R.id.display_date);
        ImageView image = (ImageView) findViewById(R.id.view_list_image);

        Bundle b = getIntent().getExtras();
        Entry entry = b.getParcelable("Entry");
        String image_url = entry.images[2].url; //assuming the third image exists and is largest


        title.setText(entry.title);
        summary.setText(entry.summary);
        price.setText("$" + entry.price_amount);
        date.setText("Released on " + entry.release_date_human);
        new ImageDownloader(image).execute(image_url);
    }
}
