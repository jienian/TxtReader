package com.juying.txtreaderlib.tasks;

import android.content.Context;
import android.graphics.Color;

import com.juying.txtreaderlib.interfaces.ILoadListener;
import com.juying.txtreaderlib.interfaces.ITxtTask;
import com.juying.txtreaderlib.main.PaintContext;
import com.juying.txtreaderlib.main.TxtConfig;
import com.juying.txtreaderlib.main.TxtReaderContext;
import com.juying.txtreaderlib.utils.ELogger;

/*用于初始化绘制上下文
接受三个参数：context表示上下文对象，paintContext表示绘制上下文对象，txtConfig表示文本配置对象*/
public class DrawPrepareTask implements ITxtTask {
    private String tag = "DrawPrepareTask";

    @Override
    public void Run(ILoadListener callBack, TxtReaderContext readerContext) {
        callBack.onMessage("start do DrawPrepare");
        ELogger.log(tag, "do DrawPrepare");
        initPainContext(readerContext.context,readerContext.getPaintContext(), readerContext.getTxtConfig());
        readerContext.getPaintContext().textPaint.setColor(Color.WHITE);
        ITxtTask txtTask = new BitmapProduceTask();
        txtTask.Run(callBack, readerContext);
    }

    private void initPainContext(Context context,PaintContext paintContext, TxtConfig txtConfig) {
        TxtConfigInitTask.initPainContext(context,paintContext, txtConfig);
    }
}
