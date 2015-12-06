package com.example.application.watchandlike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application.datamanager.ArticleWrapper;
import com.example.application.datamanager.DataManager;
import com.example.application.requestmanager.PicassoImageManager;
import com.example.application.utilsmanager.ClientUtilsManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class RatePicturesActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        //FIXME : Synchronized !??? WTF ?!?! Synchronized on an instance of a "class" ?!?!?!?! Just Die !!! -10 !
        //Go and learn some multithreading :https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html
        synchronized (DataManager.class) {

            //FIXME : You need to to pass relevant data to the activity via "intent" , and not to try and save it somewhere and then read it...
            //Try to parse your objects and pass them in intent : http://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-on-android
            allocateDataManagerWithJsonData(DataManager.getInstance().getJsonArray());
            allocateRatePicturesActivityControllers();
        }
    }

    //FIXME : I've asked you to give meaningful names 2 times... -10 .
    private void allocateRatePicturesActivityControllers() {
        //FIXME : No , you really need to explain yourself here with this synchronization...
        synchronized (DataManager.class) {

            //FIXME : And one of the ...getInstance().getArticles().size() will be null someday...Trust me..it will...
            if (DataManager.getInstance().getArticles() != null && DataManager.getInstance().getArticles().size() > 0) {
                ((TextView) this.findViewById(R.id.textView)).setText(String.format("% 2d  / %d"
                        , DataManager.getInstance().getLikeCounter()
                        , DataManager.getInstance().getArticles().size()));

                //And why don't you use "this.getImage" ? you are not consistent....Even when you are wrong , at least be consistent...
                //Otherwise there is a feeling that there are several different people writing this test...
                getImage(RatePicturesActivity.this
                        , DataManager.getInstance().getArticleWrapperListIterator().next().getMedia().get(0).getUri()
                        , (ImageView) this.findViewById(R.id.imageView));

            }
            (this.findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    showNextArticle(true);
                }
            });
            (this.findViewById(R.id.imageButton2)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    showNextArticle(false);
                }
            });
            (this.findViewById(R.id.imageButton3)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (DataManager.getInstance().getArticleWrapperListIterator().hasNext()) {
                        ClientUtilsManager.PopUpDialog(RatePicturesActivity.this, ClientUtilsManager.MESSAGE_RATE_ARTICLES);
                        return;
                    }

                    //FIXME: "allocateRatePicturesActivityControllers" starts an Activity... You know it is wrong right ? -5 .
                    startReviewActivity();
                }
            });
        }

    }


    public void startReviewActivity() {

        startActivity(new Intent(RatePicturesActivity.this, ReviewArticlesActivity.class));

    }

    public DataManager allocateDataManagerWithJsonData(String articleJSONArray) {

        synchronized (DataManager.class) {
            if (articleJSONArray != null) {
                DataManager.getInstance().setArticles((List<ArticleWrapper>) new Gson().fromJson(articleJSONArray, new TypeToken<List<ArticleWrapper>>() {
                }.getType()));
                return DataManager.getInstance();
            }
            return null;
        }
    }


    public void showNextArticle(boolean isLiked) {
        synchronized (DataManager.class) {
            if (DataManager.getInstance().getArticleWrapperListIterator().hasNext()) {

                DataManager.getInstance().getArticles().get(DataManager.getInstance().getArticleWrapperListIterator().nextIndex() - 1).setIsLiked(isLiked);

                //FIXME : findViewById is an expensive operation , try to cache your view and reuse it later...
                ((TextView) this.findViewById(R.id.textView)).setText(String.format("% 2d  / %d"
                        , DataManager.getInstance().getLikeCounter(isLiked)
                        , DataManager.getInstance().getArticles().size()));

                getImage(RatePicturesActivity.this
                        , DataManager.getInstance().getArticleWrapperListIterator().next().getMedia().get(0).getUri()
                        , (ImageView) this.findViewById(R.id.imageView));


            } else {
                ClientUtilsManager.PopUpDialog(RatePicturesActivity.this, ClientUtilsManager.MESSAGE_END_OF_ARTICLES);
            }

        }


    }


    public void getImage(Context cont, String imageUrl, ImageView img) {
        PicassoImageManager.picassoLoadSingleImage(cont, imageUrl, img);
    }


}
