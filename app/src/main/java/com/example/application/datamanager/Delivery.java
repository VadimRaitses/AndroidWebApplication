package com.example.application.datamanager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Generator on 11/29/2015.
 */
public class Delivery  implements Serializable {
    private Time  time;
    private String type;
    public String terms;
    private String deliveredBy;
    private String text;
    private String typeLabelLink;
    private List<Details> details;
}
