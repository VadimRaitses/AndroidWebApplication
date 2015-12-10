package com.example.application.datamanager;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Generator on 11/28/2015.
 */
public class ArticleWrapper implements Parcelable, Serializable {

    private String description;
    private Price prevPrice;
    private String manufacturePrice;
    private Delivery delivery;
    private Brand brand;
    private List<Media> media;
    private String assemblyService;
    private String availability;
    private String url;
    private String energyClass;
    private String sku;
    private String title;
    private Price price;
    private boolean isLiked;
    private Bitmap bit;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrevPrice() {
        return prevPrice;
    }

    public void setPrevPrice(Price prevPrice) {
        this.prevPrice = prevPrice;
    }

    public String getManufacturePrice() {
        return manufacturePrice;
    }

    public void setManufacturePrice(String manufacturePrice) {
        this.manufacturePrice = manufacturePrice;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public String getAssemblyService() {
        return assemblyService;
    }

    public void setAssemblyService(String assemblyService) {
        this.assemblyService = assemblyService;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Bitmap getBit() {
        return bit;
    }

    public void setBit(Bitmap bit) {
        this.bit = bit;
    }

    public int getmData() {
        return mData;
    }

    public void setmData(int mData) {
        this.mData = mData;
    }

    public static Creator<ArticleWrapper> getCREATOR() {
        return CREATOR;
    }


//    public Metadata _metadata = new Metadata();
//    public Links _links = new Links();
   private int mData;

    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }


    public static final Parcelable.Creator<ArticleWrapper> CREATOR = new Parcelable.Creator<ArticleWrapper>() {
        public ArticleWrapper createFromParcel(Parcel in) {
            return new ArticleWrapper(in);
        }

        public ArticleWrapper[] newArray(int size) {
            return new ArticleWrapper[size];
        }
    };


    private ArticleWrapper(Parcel in) {
        mData = in.readInt();
    }
}







