package com.example.application.requestmanager;


import android.content.Context;
import android.os.AsyncTask;

import com.example.application.utilsmanager.ClientUtilsManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Generator on 11/26/2015.
 */
public final  class AsyncDownloadJsonArrayTask extends AsyncTask<String,Context,JSONArray> {

    private static final String MESSAGE_WAIT = "Please wait content is downloading";
    private static final String EMBEDDED = "_embedded";
    private static final String ARTICLES = "articles";
    private Context context;
    private  GetJsonArrayDelegate getArr;

    public interface GetJsonArrayDelegate{
       void GetJsonArray(JSONArray jsonArray);
   }
    public AsyncDownloadJsonArrayTask(Context context, GetJsonArrayDelegate getAsync){
        this.context =context;
        this.getArr = getAsync;

    }

    @Override
    protected void onPostExecute(JSONArray json) {
        getArr.GetJsonArray(json);
        ClientUtilsManager.dialogDismiss();
    }


    @Override
    protected void onPreExecute() {
      ClientUtilsManager.dialogShow(this.context,MESSAGE_WAIT);

    }

    protected void onProgressUpdate(Context... context) {


    }
    @Override
    protected JSONArray doInBackground(String... strings) {

        try {
            try {
                JSONObject jsonObject = new JSONObject(ClientUtilsManager.downloadUrlToJSONString(strings[0]));
                ClientUtilsManager.dialogDismiss();
                return jsonObject.getJSONObject(EMBEDDED).getJSONArray(ARTICLES);
            }catch(org.json.JSONException ex){

            }

        } catch (IOException e) {
                return null;
        }

        return  null;
    }







}



