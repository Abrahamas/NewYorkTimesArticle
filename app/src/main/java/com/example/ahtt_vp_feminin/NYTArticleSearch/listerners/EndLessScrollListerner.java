package com.example.ahtt_vp_feminin.NYTArticleSearch.listerners;

import android.widget.AbsListView;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public abstract class EndLessScrollListerner implements AbsListView.OnScrollListener {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousToatlItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public EndLessScrollListerner(){

    }

    public EndLessScrollListerner(int visibleThreshold)
    {
        this.visibleThreshold = visibleThreshold;
    }
    public EndLessScrollListerner(int visibleThreshold, int startPage) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (totalItemCount < previousToatlItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousToatlItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
            }
            if (loading && (totalItemCount > previousToatlItemCount)){
                loading = false;
                previousToatlItemCount = totalItemCount;
                currentPage++;
            }
            if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold)  >=totalItemCount){
                loading = onLoadMore(currentPage + 1, totalItemCount);
            }
        }

        public abstract boolean onLoadMore (int page, int totalItemCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }
}
