package com.thekthuser.listviewdemo;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.graphics.Bitmap;
import java.io.InputStream;
import android.graphics.BitmapFactory;

import android.util.Log;


//This class is from https://stackoverflow.com/questions/22212463/android-imageview-load-image-from-url
class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            //Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon;
    }

    protected void onPostExecute(Bitmap result) {
    bmImage.setImageBitmap(result);
    }
}
