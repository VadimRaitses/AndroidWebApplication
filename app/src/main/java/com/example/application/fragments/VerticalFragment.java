package com.example.application.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.application.adapters.SimpleAdapter;
import com.example.application.datamanager.ArticleWrapper;
import com.example.application.layout.DividerDecoration;
import com.google.gson.Gson;

import java.util.List;


public class VerticalFragment extends RecyclerFragment {

    public static VerticalFragment newInstance(List<ArticleWrapper> articles  ) {
        VerticalFragment fragment = new VerticalFragment();
        Bundle args = new Bundle();
        args.putString("jsonData",( new Gson().toJson(articles)));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        //We must draw dividers ourselves if we want them in a list
        return new DividerDecoration(getActivity());
    }





    @Override
    protected SimpleAdapter getAdapter(List<ArticleWrapper> list  ) {
        return new SimpleAdapter(list);
    }
}
