package com.thekthuser.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class BaseActivity extends Activity
{
    private MenuHandler mHandler;

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mHandler = new MenuHandler(this);
        }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            mHandler.onCreateOptionsMenu(menu);
            return true;
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            mHandler.onOptionsItemSelected(item);
            return true;
        }

}
