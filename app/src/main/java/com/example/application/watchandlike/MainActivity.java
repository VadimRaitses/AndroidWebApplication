package com.example.application.watchandlike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.application.requestmanager.AsyncDownloadJsonArrayTask;
import com.example.application.utilsmanager.ClientUtilsManager;


import org.json.JSONArray;

public class MainActivity extends Activity  {
    private static final String HTTPS_URL = "https://api-mobile.home24.com/api/v1/articles?";
    private static final String MESSAGE_NO_INTERNET_WARNING = "Sorry, but you have no connection to Intenret";
    private static final String MESSAGE_NO_DATA = "Sorry,currently no data to download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getAsyncJsonArray();


            }
        });
    }

    private void getAsyncJsonArray() {


        //In this part of code i pass activity by three different ways
        //this when im not in anonymous method, or when i going to display some dialog using context view and app token  of application
        //MainActivity.this - when in anonymous method context
        //getApplicationContext when i need context only
        if (ClientUtilsManager.isNetConnectionAvailable(getApplicationContext())) {
            new AsyncDownloadJsonArrayTask(this, new AsyncDownloadJsonArrayTask.GetJsonArrayDelegate() {
                public void GetJsonArray(JSONArray jsonArray) {
                    if(jsonArray!=null) {
                        //when Json arrived from async request we can start new activivty
                        startActivity(new Intent(getApplicationContext(), RatePicturesActivity.class).putExtra("jsonData",jsonArray.toString()));

                    }
                    else ClientUtilsManager.PopUpDialog(MainActivity.this,MESSAGE_NO_DATA);
                }
            }).execute(HTTPS_URL);

        } else {
            ClientUtilsManager.PopUpDialog(this,MESSAGE_NO_INTERNET_WARNING);
        }
    }


}



