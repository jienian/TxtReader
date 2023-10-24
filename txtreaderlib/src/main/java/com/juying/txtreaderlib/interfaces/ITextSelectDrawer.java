package com.juying.txtreaderlib.interfaces;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.juying.txtreaderlib.bean.TxtChar;

import java.util.List;



public interface ITextSelectDrawer {
    void drawSelectedChar(TxtChar selectedChar, Canvas canvas, Paint paint);

    void drawSelectedLines(List<ITxtLine> selectedLines, Canvas canvas, Paint paint);
}
