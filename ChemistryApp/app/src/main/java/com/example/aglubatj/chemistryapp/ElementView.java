package com.example.aglubatj.chemistryapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Class meant to display elements in the periodic table activity
 */
public class ElementView extends View {
    private String elementSymbol;
    private int elementNumber;
    private float elementWeight;
    private int elementColor; // color of the symbol text, depends on state at room temperature
    private float symbolSize;
    private Drawable backgroundColor; // color the background, depends on whether element is a metal, metalloid, or nonmetal

    private TextPaint symbolTextPaint;
    private float symbolTextPaintWidth;
    private float symbolTextPaintHeight;

    private TextPaint numberTextPaint;
    private float numberTextPaintWidth;
    private float numberTextPaintHeight;

    private TextPaint weightTextPaint;
    private float weightTextPaintWidth;
    private float weightTextPaintHeight;

    public ElementView(Context context) {
        super(context);
        init(null, 0);
    }

    public ElementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ElementView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ElementView, defStyle, 0);

        elementSymbol = a.getString(
                R.styleable.ElementView_elementSymbol);
        elementNumber = a.getInt(
                R.styleable.ElementView_elementNumber,
                elementNumber);
        elementWeight = a.getFloat(
                R.styleable.ElementView_elementWeight,
                elementWeight);
        elementColor = a.getColor(
                R.styleable.ElementView_elementColor,
                elementColor);
        symbolSize = a.getDimension(
                R.styleable.ElementView_symbolSize,
                symbolSize);

        if (a.hasValue(R.styleable.ElementView_backgroundColor)) {
             backgroundColor = a.getDrawable(
                     R.styleable.ElementView_backgroundColor);
             backgroundColor.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        symbolTextPaint = new TextPaint();
        symbolTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        symbolTextPaint.setTextAlign(Paint.Align.LEFT);

        numberTextPaint = new TextPaint();
        numberTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        numberTextPaint.setTextAlign(Paint.Align.LEFT);

        weightTextPaint = new TextPaint();
        weightTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        weightTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        symbolTextPaint.setTextSize(symbolSize);
        symbolTextPaint.setColor(elementColor);
        symbolTextPaintWidth = symbolTextPaint.measureText(elementSymbol);

        Paint.FontMetrics fontMetrics = symbolTextPaint.getFontMetrics();
        symbolTextPaintHeight = fontMetrics.top + fontMetrics.bottom;

        numberTextPaint.setTextSize(symbolSize / 6);
        numberTextPaint.setColor(elementColor);
        numberTextPaintWidth = numberTextPaint.measureText(String.valueOf(elementNumber));

        fontMetrics = numberTextPaint.getFontMetrics();
        numberTextPaintHeight = fontMetrics.top + fontMetrics.bottom;

        weightTextPaint.setTextSize(symbolSize / 6);
        weightTextPaint.setColor(elementColor);
        weightTextPaintWidth = weightTextPaint.measureText(String.valueOf(elementWeight));

        fontMetrics = weightTextPaint.getFontMetrics();
        weightTextPaintHeight = fontMetrics.top + fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the example drawable on top of the text.
        if (backgroundColor != null) {
            backgroundColor.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            backgroundColor.draw(canvas);
        }
        // Draw the text.
        canvas.drawText(elementSymbol,
                paddingLeft + (contentWidth - symbolTextPaintWidth) / 2,
                paddingTop + (contentHeight - symbolTextPaintHeight) / 2,
                symbolTextPaint);

        canvas.drawText(String.valueOf(elementNumber),
                paddingLeft + (contentWidth - numberTextPaintWidth) / 2,
                paddingTop + (contentHeight - symbolTextPaintHeight) / 2 + symbolTextPaintHeight + numberTextPaintHeight/2,
                numberTextPaint);

        canvas.drawText(String.valueOf(elementWeight),
                paddingLeft + (contentWidth - weightTextPaintWidth) / 2,
                paddingTop + contentHeight + weightTextPaintHeight / 2,
                weightTextPaint);
    }

    public int getElementNumber(){
        return elementNumber;
    }
}
