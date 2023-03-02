package com.dmt.httpgetpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String sUrl = "https://script.google.com/macros/s/AKfycbyoGS_o_RedSHBz_YnGe5ZE8CNx164RCbY6zQdJgXAd5wovMS_OZ4BpK0OsmOR1iLiT/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    ///////////////////////////////////////
    public void HTTPRequest(View v){
        try { new HttpRequest().execute(sUrl); }catch (Exception e){}
    }
    ///////////////////////////////////////
    public class HttpRequest extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){
            String result;
            String inputLine;
            try {
                URL myUrl = new URL(params[0]);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.connect();
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null){ stringBuilder.append(inputLine); }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();
            } catch(IOException e) {
                e.printStackTrace();
                result = "";
            }
            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            Toast.makeText( getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
    ///////////////////////////////////////
}