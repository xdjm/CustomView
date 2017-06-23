package com.xd.commander.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private String mText;
    /*
     * 文本的颜色
     */
    private int mTextColor;
    /*
     * 文本的大小
     */
    private float mTextSize;
    /*
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;
    //在java中初始化
    public MyView(Context context) {
        this(context,null);
    }
    //在xml中初始化
    public MyView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }
    //带有属性初始化
    public MyView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //取出自定义属性
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(
                        attrs,R.styleable.MyView,defStyleAttr,0);
        mText=typedArray.getString(R.styleable.MyView_mText);
        mTextColor = typedArray.getColor(R.styleable.MyView_mTextColor,Color.BLACK);
        mTextSize = typedArray.getDimension(R.styleable.MyView_mTextSize,100);
        mPaint = new Paint();
        typedArray.recycle();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        //获得绘制文本的宽和高
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }
    @Override
    protected void onDraw(Canvas canvas) {
//        绘制文字
//        x和y坐标是基于屏幕上的
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2,
                getHeight() / 2 + mBound.height() / 2,
                mPaint);
    }
}
