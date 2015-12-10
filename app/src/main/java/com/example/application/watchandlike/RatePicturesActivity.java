package com.example.application.watchandlike;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.application.datamanager.ArticleWrapperHelper;
import com.example.application.requestmanager.PicassoImageHelper;
import com.example.application.utilsmanager.ClientUtilsManager;

public class RatePicturesActivity extends Activity {

    private static final String MESSAGE_END_OF_ARTICLES = "Sorry, but you already seen  all products";
    private static final String MESSAGE_RATE_ARTICLES = "Please Rate all Articles";
    private static final String MESSAGE_NO_DATA = "Sorry,currently no data to download";
    private ImageView imgView;
    private ImageButton likeButton;
    private ImageButton dislikeButton;
    private Button reviewButton;
    private TextView likeCounter;
    private ArticleWrapperHelper articleHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_pictures);

         imgView = (ImageView)findViewById(R.id.imageView);
         likeButton = (ImageButton)findViewById(R.id.imageButton);
         dislikeButton = (ImageButton)findViewById(R.id.imageButton2);
         reviewButton = (Button)findViewById(R.id.button2);
         likeCounter= (TextView)findViewById(R.id.textView);

        String jsonData =  getIntent().getStringExtra("jsonData");
         if(jsonData!=null || jsonData.length()>0){
             articleHelper= new ArticleWrapperHelper(jsonData);
             RatePicturesActivityLogic();
         }
        else ClientUtilsManager.PopUpDialog(this,MESSAGE_NO_DATA);

    }

    private void RatePicturesActivityLogic() {

            if (articleHelper.getArticles() != null)
               if (articleHelper.getArticles().size() > 0) {
                   likeCounter.setText(String.format("% 2d  / %d"
                           ,articleHelper.getLikeCounter()
                           , articleHelper.getArticles().size()));

                   DownloadImage(this
                             , articleHelper.getArticleWrapperListIterator().next().getMedia().get(0).getUri()
                             , imgView);

            }
            likeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    showNextArticle(true);
                }
            });
            dislikeButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    showNextArticle(false);
                }
            });
            reviewButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (articleHelper.getArticleWrapperListIterator().hasNext()) {
                        ClientUtilsManager.PopUpDialog(RatePicturesActivity.this, MESSAGE_RATE_ARTICLES);
                        return;
                    }
                    //when all articles are seen we can start next  activity  after triggering review button.
                    startReviewActivity();
                }
            });


    }


    public void startReviewActivity(){

        startActivity(new Intent(getApplicationContext(),ReviewArticlesActivity.class).putExtra("jsonData",articleHelper.articlesToJson()));

    }


    public void showNextArticle(boolean isLiked) {

            if (articleHelper.getArticleWrapperListIterator().hasNext()) {

                articleHelper.getArticles().get(articleHelper.getArticleWrapperListIterator().nextIndex() - 1).setIsLiked(isLiked);
                                                                                                       likeCounter.setText(String.format("% 2d  / %d"
                                                                                                      ,articleHelper.getLikeCounter(isLiked)
                                                                                                      ,articleHelper.getArticles().size()));

              DownloadImage(this
                      ,articleHelper.getArticleWrapperListIterator().next().getMedia().get(0).getUri()
                      , imgView);


            } else {
                ClientUtilsManager.PopUpDialog(this,MESSAGE_END_OF_ARTICLES);
            }

        }





    public void DownloadImage(Context cont, String imageUrl, ImageView img){
        PicassoImageHelper.picassoLoadSingleImage(cont, imageUrl, img);
    }




}
