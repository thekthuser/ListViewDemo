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

import org.json.JSONObject;

import com.squareup.okhttp.*;
import java.io.IOException;

import android.util.Log;

import android.widget.Toast;

public class MainActivity extends Activity {

    private final OkHttpClient client = new OkHttpClient();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final MainActivity ma = this;

        Request request = new Request.Builder()
            .url("http://www.something.com/")
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException throwable) {
                throwable.printStackTrace();
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Log.i("QQQQQQQQQQQQQQQQQQQQQQQQ", response.body().string());
                final String asdf = "asdf";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final ObjectItem[] items = new ObjectItem[1];
                        items[0] = new ObjectItem(0, asdf);

                        CustomArrayAdapter adapter = new CustomArrayAdapter(ma, R.layout.list_item,
                            items);
                        ListView listView = (ListView) findViewById(R.id.list);


                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
                                //String display = ((TextView) view).getText().toString();
                                //String display = view.getText().toString();
                                //String display = Integer.toString(position);
                                //String display = items[position].title;
                                //Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
                                //String item_id = view.getTag().toString();
                                //String item_id = ((TextView) view).getTag().toString();
                                String item_id = Integer.toString(items[position].itemId);
                                Intent i = new Intent(getApplicationContext(), ViewListItem.class);
                                i.putExtra("id", item_id);
                                startActivity(i);
                            }
                        });

                        Button refresh = (Button) findViewById(R.id.refresh);
                        refresh.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "test refresh", 
                                    Toast.LENGTH_LONG).show();
                                //finish();
                                //startActivity(getIntent());
                            }
                        });
                    }
                });
            }
        });
    }
}
