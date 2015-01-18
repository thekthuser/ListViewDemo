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
import org.json.JSONArray;
import org.json.JSONException;

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

        String url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json";
        Request request = new Request.Builder()
            .url(url)
            .build();

            client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                try {
                    JSONObject json_response = new JSONObject(response.body().string());
                    final JSONArray json_entries = new JSONArray(json_response.getJSONObject("feed")
                        .getJSONArray("entry").toString());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int num_entries = json_entries.length();
                            final Entry[] entries = new Entry[num_entries];
                            
                            try {
                                for (int i = 0; i < json_entries.length(); i++) {
                                    JSONObject json_entry = json_entries.getJSONObject(i);
                                    entries[i] = new Entry(i, json_entry.getJSONObject("im:name")
                                        .getString("label"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            CustomArrayAdapter adapter = new CustomArrayAdapter(ma, 
                                R.layout.list_item, entries);
                            ListView listView = (ListView) findViewById(R.id.list);


                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, 
                                    int position, long id) {
                                    //String display = ((TextView) view).getText().toString();
                                    //String display = view.getText().toString();
                                    //String display = Integer.toString(position);
                                    //String display = items[position].title;
                                    //Toast.makeText(getApplicationContext(), display, Toast.LENGTH_LONG).show();
                                    //String item_id = view.getTag().toString();
                                    //String item_id = ((TextView) view).getTag().toString();
                                    String entryId = Integer.toString(entries[position].entryId);
                                    Intent i = new Intent(getApplicationContext(), 
                                        ViewListItem.class);
                                    i.putExtra("id", entryId);
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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
