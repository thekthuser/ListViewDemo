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

import android.util.Log;
import android.widget.Toast;

public class ViewSavedList extends BaseActivity {

    private MenuHandler mHandler;
    private Activity activity;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_saved_list);
        ActionBar actionBar = getActionBar();
        actionBar.setSubtitle(R.string.view_saved_list);
        mHandler = new MenuHandler(this);
        activity = mHandler.getActivity();
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
