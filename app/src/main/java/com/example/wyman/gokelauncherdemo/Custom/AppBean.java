package com.example.wyman.gokelauncherdemo.Custom;

import android.graphics.drawable.Drawable;

/**
 * 描述一个APP各种属性的对象
 * Created by wyman on 2017/5/10.
 */

public class AppBean implements DisplayableItem
{
    private String dataDir;     //数据路径
    private Drawable icon;      //图标
    private String id;
    private String name;
    private String launcherName;
    private String packageName;
    private int pageIndex;
    private int position;
    private boolean sysApp = false; //是否是系统自带APP

    public String getDataDir()
    {
        return this.dataDir;
    }

    public Drawable getIcon()
    {
        return this.icon;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPackageName()
    {
        return this.packageName;
    }

    public int getPageIndex()
    {
        return this.pageIndex;
    }

    public int getPosition()
    {
        return this.position;
    }

    public String getLauncherName() {
        return launcherName;
    }

    public boolean isSysApp() {
        return sysApp;
    }

    public void setDataDir(String paramString)
    {
        this.dataDir = paramString;
    }

    public void setIcon(Drawable paramDrawable)
    {
        this.icon = paramDrawable;
    }

    public void setId(String paramString)
    {
        this.id = paramString;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public void setPackageName(String paramString)
    {
        this.packageName = paramString;
    }

    public void setPageIndex(int paramInt)
    {
        this.pageIndex = paramInt;
    }

    public void setPosition(int paramInt)
    {
        this.position = paramInt;
    }

    public void setSysApp(boolean sysApp) {
        this.sysApp = sysApp;
    }

    public void setLauncherName(String launcherName) {
        this.launcherName = launcherName;
    }

    public String toString()
    {
        return "AppBean [packageName=" + this.packageName + ", name=" + this.name + ", dataDir=" + this.dataDir + "]";
    }
}