package com.example.wyman.gokelauncherdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.wyman.gokelauncherdemo.Adapter.AppUninstallAdapter;
import com.example.wyman.gokelauncherdemo.App.GetAppList;
import com.example.wyman.gokelauncherdemo.Custom.AppBean;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;
import com.example.wyman.gokelauncherdemo.Utils.Tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppUninstallActivity extends AppCompatActivity {
    private List<AppBean> mAppList = new ArrayList<>();
    private List<DisplayableItem> mAdapterList = new ArrayList<>();
    private AppUninstallAdapter appUninstallAdapterLocal;
    private Context context;
    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_uninstall);
        context = this;
        init();
    }

    private void init(){
        GetAppList getAppInstance = new GetAppList(context);
        mAppList = getAppInstance.getUninstallAppList();
        Iterator<AppBean> iterator = mAppList.iterator();
        while (true){
            if(!iterator.hasNext()){
                break;
            }
            AppBean appBean = iterator.next();
            mAdapterList.add(appBean);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activityAppUninstallRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AppUninstallAdapter appUninstallAdapter = new AppUninstallAdapter(this,mAdapterList);
        appUninstallAdapterLocal = appUninstallAdapter;
        recyclerView.setAdapter(appUninstallAdapter);
    }

    @Override
    protected void onStart() {
        Log.i("AppUninstallActivity","=====Start=====");
        super.onStart();
        receiver = new Receiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        this.registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        if(receiver != null) {
            this.unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent){
            //接收安装广播
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {

                String packageName = intent.getDataString();
                List<ResolveInfo> list = Tools.findActivitiesForPackage(context, packageName);
                ResolveInfo info = list.get(0);
                PackageManager localPackageManager = context.getPackageManager();
                AppBean localAppBean = new AppBean();
                localAppBean.setIcon(info.activityInfo.loadIcon(localPackageManager));
                localAppBean.setName(info.activityInfo.loadLabel(localPackageManager).toString());
                localAppBean.setPackageName(info.activityInfo.packageName);
                localAppBean.setDataDir(info.activityInfo.applicationInfo.publicSourceDir);

                mAppList.add(localAppBean);
            }
            //接收卸载广播
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                String receiverName = intent.getDataString();
                receiverName = receiverName.substring(8);
                AppBean appBean;
                for(int i=0;i<mAdapterList.size();i++){
                    appBean = (AppBean) mAdapterList.get(i);
                    String packageName = appBean.getPackageName();
                    if(packageName.equals(receiverName)){
                        mAdapterList.remove(i);
                        if(appUninstallAdapterLocal != null) {
                            appUninstallAdapterLocal.notifyItemRangeRemoved(i,1);
                        }
                    }
                }
            }
        }
    }
}
