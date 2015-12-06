package com.example.application.adapters;

import android.view.View;

import com.example.application.datamanager.ArticleWrapper;
import com.example.application.watchandlike.R;

import java.util.List;

//FIXME : -10 for not used class...
public class SimpleStaggeredAdapter extends SimpleAdapter {

    SimpleStaggeredAdapter(List<ArticleWrapper> articles){
        super(articles);


    }

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        super.onBindViewHolder(itemHolder, position);

        final View itemView = itemHolder.itemView;
        if (position % 4 == 0) {
            int height = itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.card_staggered_height);
            itemView.setMinimumHeight(height);
        } else {
            itemView.setMinimumHeight(0);
        }
    }
}
