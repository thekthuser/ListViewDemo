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
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;
import java.io.IOException;


public class ViewList extends BaseActivity {

    private final OkHttpClient client = new OkHttpClient();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        final ViewList vl = this;

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
                                for (int i = 0; i < num_entries; i++) {
                                    JSONObject json_entry = json_entries.getJSONObject(i);
                                    JSONArray json_images = new JSONArray(json_entry
                                        .getJSONArray("im:image").toString());
                                    int num_images = json_images.length();
                                    EntryImage[] images = new EntryImage[num_images];

                                     for (int j = 0; j < num_images; j++) {
                                         JSONObject json_image = json_images.getJSONObject(j);
                                         //non-saved Entry has id of -1
                                         images[j] = new EntryImage(-1, j, Integer.parseInt(json_entry.getJSONObject("id").getJSONObject("attributes").getString("im:id")),
                                            json_image.getString("label"), 
                                            json_image.getJSONObject("attributes").getInt("height"));
                                    }


                                     entries[i] = new Entry(-1, i, 
                                         json_entry.getJSONObject("im:name").getString("label"),
                                         json_entry.getJSONObject("summary").getString("label"),
                                         json_entry.getJSONObject("im:price")
                                            .getJSONObject("attributes").getString("amount"),
                                         json_entry.getJSONObject("im:price")
                                            .getJSONObject("attributes").getString("currency"),
                                         json_entry.getJSONObject("im:contentType")
                                            .getJSONObject("attributes").getString("term"),
                                         json_entry.getJSONObject("im:contentType")
                                            .getJSONObject("attributes").getString("label"),
                                         json_entry.getJSONObject("rights").getString("label"),
                                         json_entry.getJSONObject("title").getString("label"),
                                         json_entry.getJSONObject("link")
                                            .getJSONObject("attributes").getString("rel"),
                                         json_entry.getJSONObject("link")
                                            .getJSONObject("attributes").getString("type"),
                                         json_entry.getJSONObject("link")
                                            .getJSONObject("attributes").getString("href"),
                                         json_entry.getJSONObject("id").getString("label"),
                                         json_entry.getJSONObject("id").getJSONObject("attributes")
                                             .getInt("im:id"),
                                         json_entry.getJSONObject("id").getJSONObject("attributes")
                                             .getString("im:bundleId"),
                                         json_entry.getJSONObject("im:artist").getString("label"),
                                         json_entry.getJSONObject("im:artist")
                                            .getJSONObject("attributes").getString("href"),
                                         json_entry.getJSONObject("category")
                                             .getJSONObject("attributes").getString("im:id"),
                                         json_entry.getJSONObject("category")
                                             .getJSONObject("attributes").getString("term"),
                                         json_entry.getJSONObject("category")
                                             .getJSONObject("attributes").getString("scheme"),
                                         json_entry.getJSONObject("category")
                                             .getJSONObject("attributes").getString("label"),
                                         json_entry.getJSONObject("im:releaseDate")
                                             .getString("label"),
                                         json_entry.getJSONObject("im:releaseDate")
                                             .getJSONObject("attributes").getString("label"),
                                        images
                                    );

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            CustomArrayAdapter adapter = new CustomArrayAdapter(vl, 
                                R.layout.list_item, entries);
                            ListView listView = (ListView) findViewById(R.id.list);


                            listView.setAdapter(adapter);

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

                            Button refresh = (Button) findViewById(R.id.refresh);
                            refresh.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                    startActivity(getIntent());
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
