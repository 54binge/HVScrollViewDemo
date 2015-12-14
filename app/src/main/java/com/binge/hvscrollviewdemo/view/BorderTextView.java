package com.binge.hvscrollviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.TextView;

import com.binge.hvscrollviewdemo.R;

/**
 * Created by xzz64 on 2015/12/14.
 */
public class BorderTextView extends TextView {

    private int borderWith = 1;
    private int borderColor = Color.BLACK;
    private boolean drawRightBorder = false;
    private boolean drawLeftBorder = false;
    private boolean drawTopBorder = false;
    private boolean drawBottomBorder = false;

    public void setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderWith(int borderWith) {
        this.borderWith = borderWith;
    }

    public BorderTextView(Context context) {
        super(context);
    }

    public void drawBorder(boolean lBorder, boolean tBorder, boolean rBorder, boolean bBorder) {
        this.drawLeftBorder = lBorder;
        this.drawTopBorder = tBorder;
        this.drawRightBorder = rBorder;
        this.drawBottomBorder = bBorder;
    }


    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BorderTextView);
        borderWith = typedArray.getDimensionPixelSize(R.styleable.BorderTextView_borderWidth, 1);
        borderColor = typedArray.getColor(R.styleable.BorderTextView_borderColor, Color.BLACK);
        drawLeftBorder = typedArray.getBoolean(R.styleable.BorderTextView_drawLeftBorder, false);
        drawRightBorder = typedArray.getBoolean(R.styleable.BorderTextView_drawRightBorder, false);
        drawTopBorder = typedArray.getBoolean(R.styleable.BorderTextView_drawTopBorder, false);
        drawBottomBorder = typedArray.getBoolean(R.styleable.BorderTextView_drawBottomBorder, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(borderColor);

        //上
        if (drawTopBorder) {
            canvas.drawLine(0, 0, getWidth() - borderWith, 0, paint);
        }
        //左
        if (drawLeftBorder) {
            canvas.drawLine(0, 0, 0, getHeight() - borderWith, paint);
        }
        //右
        if (drawRightBorder) {
            canvas.drawLine(getWidth() - borderWith, 0, getWidth() - borderWith, getHeight() - borderWith, paint);
        }
        //下
        if (drawBottomBorder) {
            canvas.drawLine(0, getHeight() - borderWith, getWidth() - borderWith, getHeight() - borderWith, paint);
        }
        super.onDraw(canvas);
    }
}
