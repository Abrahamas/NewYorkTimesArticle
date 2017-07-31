package com.example.ahtt_vp_feminin.NYTArticleSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {
    public  ArticleArrayAdapter (Context context, List<Article> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);

    }
    private static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;

    }
    private static class TextOnlyViewHolder {
        TextView tvTitle;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article = getItem(position);
        ;

        if (article.getThumbNail().length() == 0) {
            TextOnlyViewHolder viewHolder;

            if (convertView == null || convertView.getTag(R.string.text_and_image_view_holder) == null) {

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                convertView = layoutInflater.inflate(R.layout.item_article_text_only, parent, false);
                viewHolder = new TextOnlyViewHolder();
                convertView.setTag(R.string.text_and_image_view_holder, viewHolder);

                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);


            } else {
                viewHolder = (TextOnlyViewHolder) convertView.getTag(R.string.text_and_image_view_holder);
            }
            viewHolder.tvTitle.setText(article.getHeadline());

        } else {
            ViewHolder viewHolder;
            if (convertView == null || convertView.getTag(R.string.text_and_image_view_holder) == null) {

                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                convertView = layoutInflater.inflate(R.layout.item_article_result, parent, false);
                viewHolder = new ViewHolder();
                convertView.setTag(R.string.text_and_image_view_holder, viewHolder);
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            } else {
                viewHolder = (ViewHolder) convertView.getTag(R.string.text_and_image_view_holder);
            }
            viewHolder.ivImage.setImageResource(0);

            viewHolder.tvTitle.setText(article.getHeadline());

            String thumbNail = article.getThumbNail();
            if (!TextUtils.isEmpty(thumbNail)) {
                Picasso.with(getContext()).load(thumbNail).into(viewHolder.ivImage);
            }
        }
        return convertView;
    }

}
