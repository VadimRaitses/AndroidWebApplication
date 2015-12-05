package com.example.application.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.application.adapters.SimpleAdapter;
import com.example.application.datamanager.ArticleWrapper;
import com.example.application.datamanager.DataManager;
import com.example.application.watchandlike.R;

import java.util.List;


public abstract class RecyclerFragment extends Fragment implements AdapterView.OnItemClickListener {

    private RecyclerView mList;
    private SimpleAdapter mAdapter;

    /** Required Overrides for Sample Fragments */

    protected abstract RecyclerView.LayoutManager getLayoutManager();
    protected abstract RecyclerView.ItemDecoration getItemDecoration();


    protected abstract SimpleAdapter getAdapter(List<ArticleWrapper> articles);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        synchronized(DataManager.class) {
            View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
            mList = (RecyclerView) rootView.findViewById(R.id.section_list);
            mList.setLayoutManager(getLayoutManager());
            mList.addItemDecoration(getItemDecoration());
            mList.getItemAnimator().setAddDuration(1000);
            mList.getItemAnimator().setChangeDuration(1000);
            mList.getItemAnimator().setMoveDuration(1000);
            mList.getItemAnimator().setRemoveDuration(1000);
            mAdapter = getAdapter(DataManager.getInstance().getArticles());
            mAdapter.setOnItemClickListener(this);
            mList.setAdapter(mAdapter);
            return rootView;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.grid_options, menu);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(),
                "Clicked: " + position + ", index " + mList.indexOfChild(view),
                Toast.LENGTH_SHORT).show();
    }
}
