package com.example.user.webapp;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by USER on 10/22/2017.
 */

class ConnectInternetClass extends AsyncTask<String,Void,String> {

    Context ctx;

    public ConnectInternetClass(Context ct) {
        ctx=ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream is;

        try {
            URL myURL = new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection) myURL.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            is = myConn.getInputStream();

            BufferedReader myBuff = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line="";

            while((line = myBuff.readLine()) != null){
                sb.append(line+"  \n");
            }

            myBuff.close();
            is.close();

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error! Try to change protocol to http or https";
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.myText.setText(s);
    }

}
