package com.thekthuser.listviewdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import android.widget.Toast;

public class MainListActivity extends ListActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        /*String[] items = {
            "item1", 
            "item2", 
            "item3"
        };*/

        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.title, items));
        ObjectItem[] items = new ObjectItem[1];
        items[0] = new ObjectItem(0, "item1");

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.list_item, items);
        //ListView listview = new ListView(this);
        ListView listview = getListView();
        listview.setAdapter(adapter);

        //ListView listview = getListView();
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String display = ((TextView) view).getText().toString();
                //Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
                String item_id = view.getTag().toString();
                //String item_id = ((TextView) view).getTag().toString();
                Intent i = new Intent(getApplicationContext(), ViewListItem.class);
                i.putExtra("id", item_id);
                startActivity(i);
            }
        });

    }
}
