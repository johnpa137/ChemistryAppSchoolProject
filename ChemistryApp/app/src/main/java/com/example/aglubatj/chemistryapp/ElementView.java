package com.example.aglubatj.chemistryapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
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

        elementNumber = a.getInt(
                R.styleable.ElementView_elementNumber,
                elementNumber);
        symbolSize = a.getDimension(
                R.styleable.ElementView_symbolSize,
                symbolSize);
        getPeriodicTableValues();

        a.recycle();
    }

    private void getPeriodicTableValues(){
        PeriodicTable pTable = PeriodicTable.getPeriodicTable();

        elementSymbol = pTable.getElement(elementNumber).getSymbol();
        elementWeight = pTable.getElement(elementNumber).getAtomicWeight();

        int solidElementColor = 0x000000;
        int liquidElementColor = 0x00117c;
        int gasElementColor = 0xd2d8ff;
        int transMetalElementColor = 0x3345ba;
        int halogenElementColor = 0x9fabfa;
        int nobleGasElementColor = 0x7620ff;
        int postTransMetalElementColor = 0x5967c4;
        int alkaliMetalElementColor = 0x59b9c4;
        int alkEarthMetalElementColor = 0x59a2c4;
        int lanthanoidElementColor = 0x8959c4;
        int actinoidElementColor = 0xc4599b;
        int metalloidElementColor = 0x0f9173;
        int nonmetalElementColor = 0x61c987;
        int unknownStateElementColor = 0x838383;

        int elementGroup = pTable.getElement(elementNumber).getGroup();
        int elementPeriod = pTable.getElement(elementNumber).getPeriod();

        if(elementPeriod == 1 || (elementGroup == 18 && elementPeriod != 7) || (elementPeriod == 2 && elementGroup > 14) || elementNumber == 17)
            elementColor = gasElementColor;
        else if (elementNumber == 35 || elementNumber == 80)
            elementColor = liquidElementColor;
        else if (elementPeriod == 7 && elementGroup > 3 && elementGroup < 19)
            elementColor = unknownStateElementColor;
        else
            elementColor = solidElementColor;

        switch(elementGroup){
            case 1:
                if(elementPeriod != 1){
                    backgroundColor = new ColorDrawable(alkaliMetalElementColor); break;
                }
                else{
                    backgroundColor = new ColorDrawable(nonmetalElementColor); break;
                }
            case 2: backgroundColor = new ColorDrawable(alkEarthMetalElementColor); break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: backgroundColor = new ColorDrawable(transMetalElementColor); break;
            case 13:
                if(elementPeriod != 2){
                    backgroundColor = new ColorDrawable(postTransMetalElementColor); break;
                }
                else{
                    backgroundColor = new ColorDrawable(metalloidElementColor); break;
                }
            case 14:
                if(elementPeriod == 2){
                    backgroundColor = new ColorDrawable(nonmetalElementColor); break;
                }
                else if(elementPeriod < 5){
                    backgroundColor = new ColorDrawable(metalloidElementColor); break;
                }
                else{
                    backgroundColor = new ColorDrawable(postTransMetalElementColor); break;
                }
            case 15:
                if(elementPeriod < 4){
                    backgroundColor = new ColorDrawable(nonmetalElementColor); break;
                }
                else if(elementPeriod < 6){
                    backgroundColor = new ColorDrawable(postTransMetalElementColor); break;
                }
                else{
                    backgroundColor = new ColorDrawable(metalloidElementColor); break;
                }
            case 16:
                if(elementPeriod < 5){
                    backgroundColor = new ColorDrawable(nonmetalElementColor); break;
                }
                else if(elementPeriod < 7){
                    backgroundColor = new ColorDrawable(postTransMetalElementColor); break;
                }
                else{
                    backgroundColor = new ColorDrawable(metalloidElementColor); break;
                }
            case 17: backgroundColor = new ColorDrawable(halogenElementColor); break;
            case 18: backgroundColor = new ColorDrawable(nobleGasElementColor); break;
            default:
                if(elementPeriod == 8){
                    backgroundColor = new ColorDrawable(lanthanoidElementColor); break;
                }
                else
                    backgroundColor = new ColorDrawable(actinoidElementColor);
        }

        backgroundColor.setCallback(this);

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

        // if (backgroundColor != null) {
        //     backgroundColor.setBounds(paddingLeft, paddingTop,
        //             paddingLeft + contentWidth, paddingTop + contentHeight);
        //     backgroundColor.draw(canvas);
        // }
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

    public void setElementNumber(int atomicNumber){
        elementNumber = atomicNumber;
        getPeriodicTableValues();
    }

    public float getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(float symbolSize) {
        this.symbolSize = symbolSize;
    }
}
