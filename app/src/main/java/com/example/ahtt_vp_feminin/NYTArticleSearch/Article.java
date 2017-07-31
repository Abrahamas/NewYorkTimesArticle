package com.example.ahtt_vp_feminin.NYTArticleSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import java.util.ArrayList;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

@Parcel(analyze = {Article.class})
public class Article {
    String webURL;
    String headline;

    public String getThumbNail() {return thumbNail;}

    public String getHeadline () {return headline;}

    public String getWebURL () {return webURL;}

    String thumbNail;

    public Article () {

    }

    public Article(JSONObject jsonObject) throws JSONException {
        this.webURL = jsonObject.getString("web_url");
        this.headline = jsonObject.getJSONObject("headline").getString("main");
        JSONArray multimedia = jsonObject.getJSONArray("multimedia");
        if (multimedia.length() > 0) {
            JSONObject multimediaObject = multimedia.getJSONObject(0);
            this.thumbNail = "http://www.nytimes.com/" + multimediaObject.get("url");
        }else{
                this.thumbNail = "";
            }
        }
        public static ArrayList<Article> fromJSONArray(JSONArray array){
            ArrayList<Article> result = new ArrayList<>();
            for (int ix = 0; ix < array.length(); ix++) {
            try {
                result.add(new Article(array.getJSONObject(ix)));
            }catch(JSONException e) {
                e.printStackTrace();
            }

            }
            return result;
    }


}

