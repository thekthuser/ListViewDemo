package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

import android.widget.Toast;

public class ViewListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_list_item);

        //TextView id = (TextView) findViewById(R.id.display_id);
        ImageView image = (ImageView) findViewById(R.id.view_list_image);

        Bundle b = getIntent().getExtras();
        //String sId = i.getStringExtra("id");
        //String[] image_url = i.getStringArrayExtra("images");
        //String image_url = i.getStringExtra("image_url");
        //String asdf = image_url[0].url;
        //String asdf = i.getStringExtra("name");
        //Toast.makeText(getApplicationContext(), asdf, Toast.LENGTH_LONG).show();


        EntryImage asdf = b.getParcelable("com.thekthuser.listviewdemo.EntryImage");
        String image_url = asdf.url;


        new ImageDownloader(image).execute(image_url);


        //id.setText(sId);
    }
}
