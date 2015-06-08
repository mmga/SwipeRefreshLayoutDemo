package com.mmga.swiperefreshlayoutdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private ArrayList<String> mDatas;
    private SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeLayout.setOnRefreshListener(MainActivity.this);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.addItemAtIndex(0,"refresh");
                swipeLayout.setRefreshing(false);
            }
        }, 2000);

    }


    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i <= 20; i++) {
            mDatas.add("item" + i);
        }

    }
}
