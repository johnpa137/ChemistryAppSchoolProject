package com.example.aglubatj.chemistryapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ElementView extends View {
    private String elementSymbol = "H";
    private int elementNumber = 1;
    private float elementWeight = 1.008f;
    private int elementColor = Color.RED;
    private float symbolSize = 200.0f;
    private float numberSize = 50.0f;
    private float weightSize = 50.0f;
    private Drawable mExampleDrawable;

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
        numberSize = a.getDimension(
                R.styleable.ElementView_numberSize,
                numberSize);
        weightSize = a.getDimension(
                R.styleable.ElementView_weightSize,
                weightSize);

         if (a.hasValue(R.styleable.ElementView_exampleDrawable)) {
             mExampleDrawable = a.getDrawable(
                     R.styleable.ElementView_exampleDrawable);
             mExampleDrawable.setCallback(this);
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

        numberTextPaint.setTextSize(numberSize);
        numberTextPaint.setColor(elementColor);
        numberTextPaintWidth = numberTextPaint.measureText(String.valueOf(elementNumber));

        fontMetrics = numberTextPaint.getFontMetrics();
        numberTextPaintHeight = fontMetrics.top + fontMetrics.bottom;

        weightTextPaint.setTextSize(weightSize);
        weightTextPaint.setColor(elementColor);
        weightTextPaintWidth = weightTextPaint.measureText(String.valueOf(elementWeight));

        fontMetrics = weightTextPaint.getFontMetrics();
        weightTextPaintHeight = fontMetrics.top + fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
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

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
    public String getElementSymbol() {
        return elementSymbol;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     *
     * @param exampleString The example string attribute value to use.
     */
    public void setElementSymbol(String exampleString) {
        elementSymbol = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getElementColor() {
        return elementColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param color The example color attribute value to use.
     */
    public void setElementColor(int color) {
        elementColor = color;
        invalidateTextPaintAndMeasurements();
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    public float getElementWeight() {
        return elementWeight;
    }

    public void setElementWeight(float elementWeight) {
        this.elementWeight = elementWeight;
    }

    public float getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(float symbolSize) {
        this.symbolSize = symbolSize;
    }

    public float getNumberSize() {
        return numberSize;
    }

    public void setNumberSize(float numberSize) {
        this.numberSize = numberSize;
    }

    public float getWeightSize() {
        return weightSize;
    }

    public void setWeightSize(float weightSize) {
        this.weightSize = weightSize;
    }

    public Drawable getmExampleDrawable() {
        return mExampleDrawable;
    }

    public void setmExampleDrawable(Drawable mExampleDrawable) {
        this.mExampleDrawable = mExampleDrawable;
    }
}
