package com.thekthuser.listviewdemo;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MenuHandler {
    private Activity activity;

    public MenuHandler (Activity activity) {
        this.activity = activity;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_test:
                viewSavedList();
                return true;
            default:
                return activity.onOptionsItemSelected(item);
        }
    }

    public void viewSavedList() {
        Intent intent = new Intent(activity, ViewSavedList.class);
        activity.startActivity(intent);
    }
}
