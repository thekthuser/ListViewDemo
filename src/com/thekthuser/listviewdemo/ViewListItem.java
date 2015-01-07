package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewListItem extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_list_item);

        TextView id = (TextView) findViewById(R.id.display_id);

        Intent i = getIntent();
        String sId = i.getStringExtra("id");

        id.setText(sId);
    }
}
