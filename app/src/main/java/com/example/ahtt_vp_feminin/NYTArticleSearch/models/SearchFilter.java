package com.example.ahtt_vp_feminin.NYTArticleSearch.models;

import java.util.Date;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public class SearchFilter {
    Date     benginDate;
    boolean  sortOldest;
    boolean  arts;
    boolean  fashionAndStyle;
    boolean  sports;
    public Date getBenginDate(){
        return benginDate;
    }

    public void setBenginDate(Date benginDate){
        this.benginDate = benginDate;
    }

    public boolean isSortOldest() {
        return sortOldest;
    }
    public void setSortOldest(boolean sortOldest){
        this.sortOldest = sortOldest;
    }

    public boolean isArts() {

        return arts;

    }
    public void setArts(boolean arts) {

        this.arts = arts;
    }
    public boolean isFashionAndStyle()
    {return fashionAndStyle;
    }
    public void setFashionAndStyle(boolean fashionAndStyle) {
        this.fashionAndStyle = fashionAndStyle;
    }
    public boolean isSports(){return sports;}

    public void setSports(boolean sports) {
        this.sports = sports;

    }

}
