package com.juying.txtreaderlib.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.widget.Scroller;

import com.juying.txtreaderlib.interfaces.ITextSelectDrawer;
/*
* 文本阅读器
* */

public abstract class PageDrawerBase {
    protected int PageSwitchTime = 400;
    protected TxtReaderView readerView;
    protected TxtReaderContext readerContext;
    protected Scroller scroller;
    protected Path mPath = new Path();
    protected ITextSelectDrawer textSelectDrawer;


    public PageDrawerBase(TxtReaderView readerView, TxtReaderContext readerContext, Scroller scroller) {
        this.readerView = readerView;
        this.readerContext = readerContext;
        this.scroller = scroller;
        PageSwitchTime = TxtConfig.getPageSwitchDuration(readerContext.context);
    }

    public PageDrawerBase(Context context) {
    }

    protected int getWidth() {
        return readerView.getWidth();
    }

    protected float getMoveDistance() {
        return readerView.getMoveDistance();
    }

    protected int getHeight() {
        return readerView.getHeight();
    }

    protected Bitmap getTopPage() {
        return readerView.getTopPage();
    }

    protected Bitmap getBottomPage() {
        return readerView.getBottomPage();
    }


    public ITextSelectDrawer getTextSelectDrawer() {
        if (textSelectDrawer == null) {
            textSelectDrawer = new NormalTextSelectDrawer();
        }
        return textSelectDrawer;
    }

    public void setTextSelectDrawer(ITextSelectDrawer textSelectDrawer) {
        this.textSelectDrawer = textSelectDrawer;
    }

    protected abstract void onDraw(Canvas canvas);
}
