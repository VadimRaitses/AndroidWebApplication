package com.example.application.datamanager;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by Generator on 11/29/2015.
 */
//FIXME : Just call this class "Garbage" or a "Trash Can" , this is what you use it for...
//This class doesn't manage a thnig...It just used to store different data in a runtime and retaining it...(Oh my god...)
//It will keep those objects in memory forever....
public class DataManager {
    private int likeCounter;
    private List<ArticleWrapper> articeles;
    private ListIterator<ArticleWrapper> artickleWrapperListIterator;


    private String jsonArray;


    private static DataManager mInstance;

    public void setJsonArray(String jsonArray) {
        this.jsonArray = jsonArray;
    }

    public String getJsonArray() {
        return this.jsonArray;
    }

    public DataManager() {
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public ListIterator<ArticleWrapper> getArticleWrapperListIterator() {
        return artickleWrapperListIterator;
    }


    public void setIt() {
        this.artickleWrapperListIterator = articeles.listIterator();
    }


    public int getLikeCounter() {

        return likeCounter;
    }

    public List<ArticleWrapper> getArticles() {
        return articeles;
    }

    public void setArticles(List<ArticleWrapper> articles) {
        this.articeles = articles;
        artickleWrapperListIterator = articles.listIterator();
    }

    public int getLikeCounter(boolean isLiked) {
        if (isLiked)
            this.likeCounter += 1;
        return this.likeCounter;
    }


}
