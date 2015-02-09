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
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;


public class ViewSavedList extends BaseActivity {

    private MenuHandler mHandler;
    private Activity activity;

    @Override
    public void onResume() {
        super.onResume();
        onCreate(new Bundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_saved_list);
        ActionBar actionBar = getActionBar();
        actionBar.setSubtitle(R.string.view_saved_list);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        mHandler = new MenuHandler(this);
        activity = mHandler.getActivity();

        final ViewSavedList vsl = this;

        ItemAdapter iAdapter = new ItemAdapter(getApplicationContext());
        iAdapter.open();
        final Entry[] entries = iAdapter.getEntries();
        iAdapter.close();

        CustomArrayAdapter adapter = new CustomArrayAdapter(vsl, R.layout.list_item, entries);
        ListView listView = (ListView) findViewById(R.id.saved_list);
        listView.setAdapter(adapter);

        if (entries.length < 1) {
            TextView saved = (TextView) findViewById(R.id.no_saved_items);
            saved.setVisibility(View.VISIBLE);
        }


        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, 
                int position, long id) {

                Intent i = new Intent(getApplicationContext(), 
                    ViewListItem.class);

                Entry entry = entries[position];
                i.putExtra("Entry", entry);

                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_saved_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_list:
                Intent intent = new Intent(activity, ViewList.class);
                activity.startActivity(intent);
                return true;
            default:
                return mHandler.onOptionsItemSelected(item);
        }
    }
}
