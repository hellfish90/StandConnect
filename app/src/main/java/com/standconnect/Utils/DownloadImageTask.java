package com.standconnect.Utils;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Marc on 24/11/15.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
    /** The system calls this to perform work in a worker thread and
     * delivers it the parameters given to AsyncTask.execute() */

    ImageView imageV;

    public void loadImageView(ImageView imageV){
        this.imageV = imageV;
    }

    private Drawable loadImageFromNetwork(String url) {
        try {
            InputStream is = (InputStream) new URL(url+".jpg").getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

    protected Drawable doInBackground(String... urls) {
        return loadImageFromNetwork(urls[0]);
    }

    /** The system calls this to perform work in the UI thread and delivers
     * the result from doInBackground() */
    protected void onPostExecute(Drawable result) {
        imageV.setImageDrawable(result);
    }
}