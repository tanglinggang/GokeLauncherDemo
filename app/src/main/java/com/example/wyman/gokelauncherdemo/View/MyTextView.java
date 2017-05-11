package com.example.wyman.gokelauncherdemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Px;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;


/**
 * 自定义的text view，根据测得的组件宽度，自动计算文字大小以达到左右填满效果
 * 当高度不足以支撑文字大小时，取最大值即高度为文字大小
 * Created by wyman on 2017/5/10.
 */

public class MyTextView extends AppCompatTextView {
    int width;
    int number;
    float maxHeight;
    float wantSize;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getWidth();
        maxHeight = this.getHeight();
        number = this.getText().length();
        wantSize = width / number;
        if(wantSize > maxHeight){
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX,maxHeight);
        }else {
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX,wantSize);
        }
    }
}
