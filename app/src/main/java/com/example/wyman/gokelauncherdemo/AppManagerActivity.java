package com.example.wyman.gokelauncherdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wyman.gokelauncherdemo.Adapter.AppManagerAdapter;
import com.example.wyman.gokelauncherdemo.Adapter.AppUninstallAdapter;
import com.example.wyman.gokelauncherdemo.App.GetAppList;
import com.example.wyman.gokelauncherdemo.Custom.AppBean;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppManagerActivity extends AppCompatActivity {
    private Context context;
    private List<AppBean> mAppList = new ArrayList<>();
    private List<DisplayableItem> mAdapterList = new ArrayList<>();
    private AppManagerAdapter appManagerAdapterLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        context = this;
        init();
    }

    private void init(){
        GetAppList getAppInstance = new GetAppList(context);
        mAppList = getAppInstance.getLaunchAppList();
        Iterator<AppBean> iterator = mAppList.iterator();
        while (true){
            if(!iterator.hasNext()){
                break;
            }
            AppBean appBean = iterator.next();
            mAdapterList.add(appBean);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.AppManagerActivityRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        AppManagerAdapter appManagerAdapter = new AppManagerAdapter(this,mAdapterList);
        appManagerAdapterLocal = appManagerAdapter;
        recyclerView.setAdapter(appManagerAdapter);
    }
}
