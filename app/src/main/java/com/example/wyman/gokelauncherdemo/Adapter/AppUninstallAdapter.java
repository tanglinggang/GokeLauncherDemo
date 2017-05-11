/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.wyman.gokelauncherdemo.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.wyman.gokelauncherdemo.AdapterDelegates.AppBeanDelegates;
import com.example.wyman.gokelauncherdemo.Custom.DisplayableItem;
import com.example.wyman.gokelauncherdemo.Adapterdelegates3.AdapterDelegatesManager;

import java.util.List;

/**
 * @author Hannes Dorfmann
 */
public class AppUninstallAdapter extends RecyclerView.Adapter {

  private AdapterDelegatesManager<List<DisplayableItem>> delegatesManager;
  private List<DisplayableItem> items;

  public AppUninstallAdapter(Activity activity, List<DisplayableItem> items) {
    this.items = items;

    // Delegates
    delegatesManager = new AdapterDelegatesManager<>();
    delegatesManager.addDelegate(new AppBeanDelegates(activity));
  }

  @Override public int getItemViewType(int position) {
    return delegatesManager.getItemViewType(items, position);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return delegatesManager.onCreateViewHolder(parent, viewType);
  }

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		delegatesManager.onBindViewHolder(items, position, holder);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
	  delegatesManager.onBindViewHolder(items, position, holder, payloads);
  }

  @Override public int getItemCount() {
    return items.size();
  }
}
