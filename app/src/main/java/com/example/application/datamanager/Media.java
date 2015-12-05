package com.example.application.datamanager;

import java.io.Serializable;

/**
 * Created by Generator on 11/29/2015.
 */
public class Media  implements Serializable {

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    private String uri;
    private String mimeType;
    private String type;
    private int priority;
    private Object size;

}
