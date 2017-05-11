package com.example.wyman.gokelauncherdemo.Custom;

/**
 * Launcher的每个选项的对象定义，配合MyRecyclerViewAdapter使用。
 * 对于其中的某个部件，出于对以后使用的必要，可能对应一个系统功能、自定义功能，也可能直接对应一个外部App
 * Created by wyman on 2017/5/9.
 */

public class LauncherFunction implements DisplayableItem{
    private String name;    //功能名
    private int iconId;  //对应的图标ID
    private int backgroundId; //对应的背景ID

    public LauncherFunction(){

    }

    public LauncherFunction(String name,int iconId,int backgroundId){
        this.name = name;
        this.iconId = iconId;
        this.backgroundId = backgroundId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIconId(int iconId){
        this.iconId = iconId;
    }

    public void setBackgroundId(int backgroundId){
        this.backgroundId = backgroundId;
    }

    public String getName(){
        return this.name;
    }

    public int getIconId(){
        return this.iconId;
    }

    public int getBackgroundId(){
        return this.backgroundId;
    }
    public String toString(){
        return "Now Function is " + name + " Icon Id " + iconId;
    }

}
