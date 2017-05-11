package com.example.wyman.gokelauncherdemo.AdapterDelegates;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wyman.gokelauncherdemo.AppManagerActivity;
import com.example.wyman.gokelauncherdemo.AppUninstallActivity;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;
import com.example.wyman.gokelauncherdemo.Custom.LauncherFunction;
import com.example.wyman.gokelauncherdemo.R;
import com.example.wyman.gokelauncherdemo.Adapterdelegates3.AdapterDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by wyman on 2017/5/11.
 */

public class LauncherFunctionDelegates extends AdapterDelegate<List<DisplayableItem>> {
    private LayoutInflater layoutInflater;
    private static List<DisplayableItem> mLists = new ArrayList<>();
    private Activity activity;

    public LauncherFunctionDelegates(Activity activity) {
        layoutInflater = activity.getLayoutInflater();
        this.activity = activity;
    }

    @Override public boolean isForViewType(@NonNull List<DisplayableItem> items, int position) {
        mLists = items;
        return items.get(position) instanceof LauncherFunction;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {

        Log.d("Scroll", "DogAdapterDelegate create");
        final LauncherFunctionViewHolder viewHolder = new LauncherFunctionViewHolder(layoutInflater.inflate(R.layout.view_myrecycler, parent, false));
        viewHolder.imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                LauncherFunction launcherFunction = (LauncherFunction) mLists.get(position);
                if(launcherFunction.getName().equalsIgnoreCase("应用卸载")){
                    Intent intent = new Intent(activity, AppUninstallActivity.class);
                    activity.startActivity(intent);
                }

                if(launcherFunction.getName().equalsIgnoreCase("系统设置")){
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    activity.startActivity(intent);
                }

                if(launcherFunction.getName().equalsIgnoreCase("我的应用")){
                    Intent intent = new Intent(activity, AppManagerActivity.class);
                    activity.startActivity(intent);
                }
            }
        });
        return viewHolder;
    }

    @Override public void onBindViewHolder(@NonNull List<DisplayableItem> items, int position,
                                           @NonNull RecyclerView.ViewHolder holder, @Nullable List<Object> payloads) {
        LauncherFunctionViewHolder vh = (LauncherFunctionViewHolder) holder;
        LauncherFunction launcherFunction = (LauncherFunction) items.get(position);

        vh.imageViewBack.setBackgroundResource(launcherFunction.getBackgroundId());
        vh.imageViewIcon.setBackgroundResource(launcherFunction.getIconId());
        vh.textView.setText(launcherFunction.getName());

        Log.d("Scroll", "DogAdapterDelegate bind  " + position);
    }

    private static class LauncherFunctionViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageViewIcon;
        private ImageView imageViewBack;

        private LauncherFunctionViewHolder(View itemView) {
            super(itemView);
            imageViewIcon = (ImageView)itemView.findViewById(R.id.MyRecyclerViewImageIcon);
            imageViewBack = (ImageView)itemView.findViewById(R.id.MyRecyclerViewImageBack);
            textView = (TextView)itemView.findViewById(R.id.MyRecyclerViewName);
        }
    }
}
