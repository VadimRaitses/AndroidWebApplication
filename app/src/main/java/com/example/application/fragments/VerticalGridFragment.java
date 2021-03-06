package com.example.application.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.application.adapters.SimpleAdapter;
import com.example.application.datamanager.ArticleWrapper;

import com.example.application.layout.InsetDecoration;
import com.google.gson.Gson;

import java.util.List;


public class VerticalGridFragment extends RecyclerFragment {

    public static VerticalGridFragment newInstance(List<ArticleWrapper> articles) {
        VerticalGridFragment fragment = new VerticalGridFragment();
        Bundle args = new Bundle();
        args.putString("jsonData",( new Gson().toJson(articles)));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new InsetDecoration(getActivity());
    }


    @Override
    protected SimpleAdapter getAdapter(List<ArticleWrapper> list) {
        return new SimpleAdapter(list);
    }
}
