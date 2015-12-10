package com.example.application.datamanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Generator on 11/29/2015.
 */
public  class ArticleWrapperHelper {
    private  int likeCounter;
    private  List<ArticleWrapper> articles;
    private  ListIterator<ArticleWrapper> articleWrapperListIterator;



    private  String jsonArray;

    public ArticleWrapperHelper(String jsonArray){

        setArticles(jsonArray);

    }


    private static ArticleWrapperHelper mInstance;

    public  void setJsonArray(String jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getJsonArray() {
        return this.jsonArray;
    }
    public ArticleWrapperHelper(){}

     public static ArticleWrapperHelper getInstance(){
        if(mInstance == null)
        {
            mInstance = new ArticleWrapperHelper();
        }
        return mInstance;
    }

    public  ListIterator<ArticleWrapper> getArticleWrapperListIterator() {
        return articleWrapperListIterator;
    }


    public  void setIt() {
        this.articleWrapperListIterator = articles.listIterator();
    }

    public String getArticleWrapperJsonString(){


        return articles.toString();
    }

    public  int getLikeCounter() {

        return likeCounter;
    }

    public  List<ArticleWrapper> getArticles() {
        return articles;
    }

    public  void setArticles(List<ArticleWrapper> articles) {
        this.articles = articles;
        articleWrapperListIterator = articles.listIterator();
    }

    public  void setArticles(String jsonArray) {
        this.articles = (List<ArticleWrapper>) new Gson().fromJson(jsonArray, new TypeToken<List<ArticleWrapper>>() {
        }.getType());
        articleWrapperListIterator = articles.listIterator();
    }

    public int getLikeCounter(boolean isLiked){
         if(isLiked)
         this.likeCounter+=1;
         return  this.likeCounter;
     }

    public String articlesToJson(){

        return (new Gson().toJson(this.articles));

    }



}
