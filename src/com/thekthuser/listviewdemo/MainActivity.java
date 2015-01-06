package com.thekthuser.listviewdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity
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
    }
}
