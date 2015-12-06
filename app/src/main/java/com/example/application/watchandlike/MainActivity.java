package com.example.application.watchandlike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.application.datamanager.DataManager;
import com.example.application.requestmanager.GetAsyncJsonArray;
import com.example.application.utilsmanager.ClientUtilsManager;

import org.json.JSONArray;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        //FIXME : You don't really need "this" , you are still in the scope of a class
        this.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getAsyncJsonArray();
            }
        });
    }

    //FIXME : Void method "Getting" ? no no no... the word "get" and word "set" are reserved for "Getters & Setters".
    //Your naming conventions suppose to be meaningfull !!!
    //Try to read this : https://source.android.com/source/code-style.html
    private void getAsyncJsonArray() {

        //Very good , extracting general purpose action into a helper class is a good decision +1

        //FIXME : Never pass your activity around , you can accidentaly "leak" it.Your method don't need Activity , it needs context.
        //You can pass an app context instead : "getApplicationContext()"
        //Read this for more info : http://android-developers.blogspot.de/2009/01/avoiding-memory-leaks.html

        //Fixme : youd don't need to prefix "this" with "MainActivity" , try to understand scopes of "this"
        //"...In Java, this refers to the current instance object on which the method is executed...."
        if (ClientUtilsManager.isNetConnectionAvailable(MainActivity.this)) {
            new GetAsyncJsonArray(MainActivity.this, new GetAsyncJsonArray.GetJsonArrayDelegate() {
                public void GetJsonArray(JSONArray jsonArray) {

                    //FIXME : No !!!! You should never change a state of an object , especially Singleton !
                    //Every time you call set on a Singleton , you creating a potential bug !
                    //What if other thread now calling set "null" ? Happy debugging...
                    DataManager.getInstance().setJsonArray(jsonArray.toString());

                    //Fixme : Method "getAsyncJsonArray" suppose to return a ...Hmm "JsonArray" ? Not to start an Activity ! -5 !!!
                    startActivity(new Intent(MainActivity.this, RatePicturesActivity.class));
                }
            }).execute(ClientUtilsManager.HTTPS_URL);

        } else {
            ClientUtilsManager.PopUpDialog(MainActivity.this, ClientUtilsManager.MESSAGE_NO_INTERNET_WARNING);
        }
    }

}



