package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.Toast;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
        //ListView listView = getListView();
        ListView listView = (ListView) findViewById(R.id.list);

        /*LayoutInflater inflater = getLayoutInflater();
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.refresh_footer, listView, false);
        listView.addFooterView(footer, null, false);*/

        listView.setAdapter(adapter);

        //ListView listview = getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
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

        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "test refresh", Toast.LENGTH_LONG).show();
                //finish();
                //startActivity(getIntent());
            }
        });

    }
}
