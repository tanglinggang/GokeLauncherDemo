package com.example.wyman.gokelauncherdemo.AdapterDelegates;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wyman.gokelauncherdemo.Custom.AppBean;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;
import com.example.wyman.gokelauncherdemo.R;
import com.example.wyman.gokelauncherdemo.Adapterdelegates3.AdapterDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by wyman on 2017/5/11.
 */

public class AppBeanDelegates extends AdapterDelegate<List<DisplayableItem>> {
    private LayoutInflater layoutInflater;
    private static List<DisplayableItem> mLists = new ArrayList<>();
    private Activity activity;

    public AppBeanDelegates(Activity activity) {
        this.activity = activity;
        layoutInflater = activity.getLayoutInflater();
    }

    @Override public boolean isForViewType(@NonNull List<DisplayableItem> items, int position) {
        mLists = items;
        return items.get(position) instanceof AppBean;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {

        Log.d("Scroll", "DogAdapterDelegate create");
        final AppBeanViewHolder viewHolder = new AppBeanViewHolder(layoutInflater.inflate(R.layout.app_bean_recyclerview, parent, false));
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                AppBean appBean = (AppBean)mLists.get(position);
                Uri packageURI = Uri.parse("package:" + appBean.getPackageName());
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
                        packageURI);
                activity.startActivity(uninstallIntent);
            }
        });


        return viewHolder;
    }

    @Override public void onBindViewHolder(@NonNull List<DisplayableItem> items, int position,
                                           @NonNull RecyclerView.ViewHolder holder, @Nullable List<Object> payloads) {
        AppBeanViewHolder vh = (AppBeanViewHolder) holder;
        AppBean appBean = (AppBean) items.get(position);

        vh.imageViewIcon.setBackground(appBean.getIcon());
        vh.textView.setText(appBean.getName());

        Log.d("Scroll", "DogAdapterDelegate bind  " + position);
    }

    private static class AppBeanViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageViewIcon;

        private AppBeanViewHolder(View itemView) {
            super(itemView);
            imageViewIcon = (ImageView)itemView.findViewById(R.id.appBeanIcon);
            textView = (TextView)itemView.findViewById(R.id.appBeanName);
        }
    }
}
