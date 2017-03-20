package com.lbm100.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView image ;


    public  class Download extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

           // http://www.sd.xinhuanet.com/news/yule/2017-03/20/1120656271_14899716921621n.jpeg

            URL url ;
            Bitmap result ;


            try {

                url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();

                InputStream in = httpURLConnection.getInputStream();
                result = BitmapFactory.decodeStream(in);

                return  result;


            }catch (Exception e) {
                System.out.println(e);
                return  null;
            }





        }
    }


    public  void download(View view) {

        Download download = new Download();
        try {
            Bitmap result = download.execute("http://www.sd.xinhuanet.com/news/yule/2017-03/20/1120656271_14899716921621n.jpeg").get();
            image.setImageBitmap(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);
    }
}
