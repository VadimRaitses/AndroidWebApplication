package com.example.application.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application.datamanager.ArticleWrapper;
import com.example.application.requestmanager.PicassoImageHelper;
import com.example.application.watchandlike.R;

import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VerticalItemHolder> {

    private List<ArticleWrapper> mItems;

    private AdapterView.OnItemClickListener mOnItemClickListener;

    public SimpleAdapter( List<ArticleWrapper> articles){
        mItems = articles;
    }



    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */


    /*
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemRemoved(), to trigger any enabled item
     * animations in addition to updating the view.
     */


    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_article_item, container, false);

        return new VerticalItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position) {
        ArticleWrapper item = mItems.get(position);
        itemHolder.setAwayScore(String.valueOf(item.getTitle()));
        PicassoImageHelper.picassoLoadSingleImageWithNoDialog(itemHolder.getContext(), item.getMedia().get(0).getUri(), itemHolder.getImageView());
        if(item.isLiked()){

            itemHolder.itemView.setBackgroundColor(itemHolder.getContext().getResources().getColor(R.color.Blue));
        }else
            itemHolder.itemView.setBackgroundColor(itemHolder.getContext().getResources().getColor(R.color.lighterBlue));


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(VerticalItemHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }



    public static class VerticalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mAwayScore;
        private ImageView mImageView;
        private SimpleAdapter mAdapter;

        public VerticalItemHolder(View itemView, SimpleAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            mAdapter = adapter;
            mAwayScore = (TextView) itemView.findViewById(R.id.text_team_away);
            mImageView = (ImageView)itemView.findViewById(R.id.logo_team_away);
        }

        @Override
        public void onClick(View v) {
            mAdapter.onItemHolderClick(this);
        }

        public void setPicture(Bitmap img) {
            mImageView.setImageBitmap(img);
        }

        public ImageView getImageView(){
            return mImageView;
        }
        public void setAwayScore(CharSequence awayScore) {

            mAwayScore.setText(awayScore);
        }

        public Context getContext(){
            return itemView.getContext();
        }


    }



}
