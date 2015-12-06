package com.example.application.utilsmanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Generator on 11/28/2015.
 */
//FIXME : I don't even going to try to understand the purpose of this Garbage class... -15.
public  final class ClientUtilsManager {

    public static final String MESSAGE_WARNING = "Warning";
    public static final String MESSAGE_EMPTY = "";
    public static final String MESSAGE_WAIT = "Please wait content is downloading";
    public static final String MESSAGE_NO_INTERNET_WARNING = "Sorry, but you have no connection to Intenret";
    public static final String MESSAGE_END_OF_ARTICLES = "Sorry, but you already seen  all products";
    public static final String MESSAGE_RATE_ARTICLES = "Please Rate all Articles";
    public static final String HTTPS_URL = "https://api-mobile.home24.com/api/v1/articles?";
    public static final String ARTICLES = "articles";
    public static final String EMBEDDED = "_embedded";
    private static final String HEADER_KEY = "Accept-Language";
    private static final String HEADER_VALUE = "de_DE";
    private static final int TIMEOUT_VALUE = 100000;
    private static final String PARAM_APP_DOMAIN_KEY = "appDomain";
    private static final String PARAM_APP_DOMAIN_VALUE = "1";
    private static final String PARAM_LIMIT_KEY = "limit";
    private static final String PARAM_LIMIT_KEY_VALUE = "20";
    private static final String ERROR_MESSAGE = "Error";
    private static final String CHARACTER_ENCODING = "utf-8";
    private static final String SWITCH = "switch";
    private static final int OK = 200;
    private static final String HTTP_METHOD_GET = "GET";
    private static ConnectivityManager cm;
    private static ProgressDialog progressDialog;

    public static  void PopUpDialog(Context v, String alertMessage ){

        new AlertDialog.Builder(v)
                .setTitle(MESSAGE_WARNING)
                .setMessage(alertMessage)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

    public static boolean isNetConnectionAvailable(Context ctx)
    {

        NetworkInfo info = null;
        try
        {
            cm = (ConnectivityManager)
                    ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = cm.getActiveNetworkInfo();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return info != null && info.isConnected();
    }

    public static String readInputStream(InputStream is) {
        if (is != null) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(is, ClientUtilsManager.CHARACTER_ENCODING),8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                is.close();
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ERROR_MESSAGE;
    }

    public static URL  setRequestUrl(String addressURL){

        try {
            return new URL(addressURL + PARAM_APP_DOMAIN_KEY
                    +"="
                    +PARAM_APP_DOMAIN_VALUE
                    +"&"
                    +PARAM_LIMIT_KEY
                    +"="
                    +PARAM_LIMIT_KEY_VALUE );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
            return null;
    }

    public static HttpsURLConnection makeHTTPSconnection(String addressURL){

        HttpsURLConnection conn = null;
        try {
             conn = (HttpsURLConnection) ClientUtilsManager.setRequestUrl(addressURL).openConnection();
             conn.setReadTimeout(TIMEOUT_VALUE /* milliseconds */);
             conn.setConnectTimeout(TIMEOUT_VALUE /* milliseconds */);
             conn.setRequestMethod(HTTP_METHOD_GET);
             conn.setRequestProperty(HEADER_KEY,HEADER_VALUE);
             conn.setDoInput(true);

             conn.connect();
            int response = conn.getResponseCode();
            if(response == OK) {
             return conn;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        }


    public static  String downloadUrlToJSONString(String addressURL) throws IOException {
        HttpsURLConnection conn = makeHTTPSconnection(addressURL);
        if(conn!=null)
            return readInputStream(conn.getInputStream());
        else
            return null;
    }

    public static void dialogShow(Context context, String message){
        progressDialog =   ProgressDialog.show(context, ClientUtilsManager.MESSAGE_EMPTY, message, true);

    }

    public static void dialogDismiss(){

        if(progressDialog != null &&   progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }

    }




}
