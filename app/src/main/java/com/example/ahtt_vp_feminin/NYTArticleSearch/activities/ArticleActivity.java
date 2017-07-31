package com.example.ahtt_vp_feminin.NYTArticleSearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import com.example.ahtt_vp_feminin.NYTArticleSearch.Article;
import com.example.ahtt_vp_feminin.NYTArticleSearch.R;
import org.parceler.Parcels;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public class ArticleActivity extends AppCompatActivity {
    private static final String ARTICLE_MODEL = "article";
    private ShareActionProvider miShareAction;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        article = Parcels.unwrap(getIntent().getParcelableExtra(ARTICLE_MODEL));

        WebView  wvArticle = (WebView) findViewById(R.id.wvArticle);
        wvArticle.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(article.getWebURL());
                return true;
            }


        });

        wvArticle.loadUrl(article.getWebURL());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);

        setupShareAction(menu);
        return true;
    }
    private void setupShareAction(Menu menu) {
        MenuItem item = menu.findItem(R.id.menu_item_share);
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //shareIntent.putExtra(Intent.EXTRA_STREAM, this.Url);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, this.article.getHeadline());
        shareIntent.putExtra(Intent.EXTRA_TEXT, this.article.getWebURL());
        shareIntent.setType("text/plain");
        miShareAction.setShareIntent(shareIntent);

    }
}
