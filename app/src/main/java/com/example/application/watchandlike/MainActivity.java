package com.example.application.watchandlike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.application.datamanager.DataManager;
import com.example.application.requestmanager.GetAsyncJsonArray;
import com.example.application.utilsmanager.ClientUtilsManager;

import org.json.JSONArray;

public class MainActivity extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getAsyncJsonArray();
            }
        });
    }

    private void getAsyncJsonArray() {

        if (ClientUtilsManager.isNetConnectionAvailable(MainActivity.this)) {
            new GetAsyncJsonArray(MainActivity.this, new GetAsyncJsonArray.GetJsonArrayDelegate() {
                public void GetJsonArray(JSONArray jsonArray) {
                    DataManager.getInstance().setJsonArray(jsonArray.toString());
                    startActivity(new Intent(MainActivity.this, RatePicturesActivity.class));
                }
            }).execute(ClientUtilsManager.HTTPS_URL);

        } else {
            ClientUtilsManager.PopUpDialog(MainActivity.this, ClientUtilsManager.MESSAGE_NO_INTERNET_WARNING);
        }
    }

}



