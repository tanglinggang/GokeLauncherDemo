package com.example.wyman.gokelauncherdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.wyman.gokelauncherdemo.Adapter.MainAdapter;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;
import com.example.wyman.gokelauncherdemo.Custom.LauncherFunction;
import com.example.wyman.gokelauncherdemo.View.MyRecyclerView;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<DisplayableItem> MainLauncherFunctionList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainLauncherFunctionList = launcherFunctionInit();
        MyRecyclerView recyclerView = (MyRecyclerView) findViewById(R.id.MainActivityRecyclerView);
        MainAdapter myRecyclerViewAdapter = new MainAdapter(this,MainLauncherFunctionList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,6, LinearLayoutManager.HORIZONTAL,false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if((position >= 0)&&(position <= 3)){
                    return 3;
                }else if((position >= 4)&&(position <= 10)){
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    private ArrayList<DisplayableItem> launcherFunctionInit(){
        ArrayList<DisplayableItem> launcherFunctions = new ArrayList<>();

        launcherFunctions.add(new LauncherFunction("应用卸载",R.drawable.mic_icon,R.drawable.my_recyclerview_back));
        launcherFunctions.add(new LauncherFunction("系统设置",R.drawable.mic_icon,R.drawable.my_recyclerview_back));
        launcherFunctions.add(new LauncherFunction("我的应用",R.drawable.mic_icon,R.drawable.my_recyclerview_back));
        for(int i = 0;i < 14; i++){
            launcherFunctions.add(new LauncherFunction("应用示例",R.drawable.mic_icon,R.drawable.my_recyclerview_back));;
        }
        return launcherFunctions;
    }
}
