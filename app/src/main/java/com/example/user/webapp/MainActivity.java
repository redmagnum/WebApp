package com.example.user.webapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.user.webapp.R.id.text;
import static com.example.user.webapp.R.id.textJudul;
import static com.example.user.webapp.R.id.textView;

public class MainActivity extends AppCompatActivity {

    ConnectInternetClass c1;
    static TextView myText;
    ArrayAdapter<CharSequence> list_spinner;
    Spinner spin;
    EditText textIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin = (Spinner) findViewById(R.id.spinn);
        myText = (TextView)findViewById(R.id.myResult);
        textIn = (EditText)findViewById(R.id.inputURL);

        list_spinner = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        list_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(list_spinner);

    }

    public void doSomething(View view) {
        String url, link_url, pro;

        url = textIn.getText().toString();
        pro = spin.getSelectedItem().toString();
        c1 = new ConnectInternetClass(this);

        if (!url.isEmpty()) {
            if (url.contains(".") && !(url.contains(" "))) {
                if (url.split("\\.").length > 1) {
                    if (checkConnection()) {

                        link_url = pro+url;
                        c1.execute(link_url);

                    } else {
                        Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show();
                        myText.setText("No Internet Connection");

                    }
                } else {
                    myText.setText("Unknown domain");
                }

            } else {
                myText.setText("Invalid URL");

            }

        } else {
            myText.setText("URL can\'t empty");
        }


    }

    private boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
