package com.thekthuser.listviewdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.TextView;

import android.widget.Toast;

public class MainListActivity extends ListActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        String[] items = {
            "item1", 
            "item2", 
            "item3"
        };

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.title, items));

        ListView listview = getListView();
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "toast!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
