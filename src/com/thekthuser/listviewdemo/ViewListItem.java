package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.app.ActionBar;

import android.util.Log;

public class ViewListItem extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_list_item);
        ActionBar actionBar = getActionBar();
        actionBar.setSubtitle(R.string.view_list_item);

        TextView title = (TextView) findViewById(R.id.display_title);
        TextView summary = (TextView) findViewById(R.id.display_summary);
        TextView price = (TextView) findViewById(R.id.display_price);
        TextView date = (TextView) findViewById(R.id.display_date);
        Button share = (Button) findViewById(R.id.share);
        final Button save = (Button) findViewById(R.id.save);
        ImageView image = (ImageView) findViewById(R.id.view_list_image);

        Bundle b = getIntent().getExtras();
        final Entry entry = b.getParcelable("Entry");
        String image_url = entry.images[2].url; //assuming the third image exists and is largest


        ItemAdapter iAdapter = new ItemAdapter(getApplicationContext());
        iAdapter.open();
        if (iAdapter.isEntrySaved(entry.id_id)) {
            save.setText(R.string.delete);
        }
        iAdapter.close();

        title.setText(entry.title);
        summary.setText(entry.summary);
        price.setText("$" + entry.price_amount);
        date.setText("Released on " + entry.release_date_human);
        new ImageDownloader(image).execute(image_url);

        final String subject = "Check out " + entry.name;
        final String body = entry.summary;


        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemAdapter iAdapter = new ItemAdapter(getApplicationContext());
                iAdapter.open();
                if (!iAdapter.isEntrySaved(entry.id_id)) {
                    iAdapter.addEntry(entry);
                    save.setText(R.string.delete);
                } else {
                    iAdapter.removeEntry(entry);
                    save.setText(R.string.save);
                }
                iAdapter.close();
            }
        });
    }
}
