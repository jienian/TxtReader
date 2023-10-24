package com.juying.txtreaderlib.tasks;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.juying.txtreaderlib.interfaces.ILoadListener;
import com.juying.txtreaderlib.interfaces.ITxtTask;
import com.juying.txtreaderlib.main.PageParam;
import com.juying.txtreaderlib.main.PaintContext;
import com.juying.txtreaderlib.main.TxtConfig;
import com.juying.txtreaderlib.main.TxtReaderContext;
import com.juying.txtreaderlib.utils.ELogger;
import com.juying.txtreaderlib.utils.TxtBitmapUtil;

/*
*主要功能：初始化文本阅读器的一些配置参数，包括文字颜色、文字大小、背景颜色。
* */

public class TxtConfigInitTask implements ITxtTask {
    private String tag = "TxtConfigInitTask";

    @Override
    public void Run(ILoadListener callBack, TxtReaderContext readerContext) {
        ELogger.log(tag, "do TxtConfigInit");
        callBack.onMessage("start init settings in TxtConfigInitTask");
        //文本配置信息，并将其传递给initTxtConfig()方法进行初始化
        TxtConfig config = readerContext.getTxtConfig();
        initTxtConfig(readerContext, config);

        //作用是检查当前页面是否有背景位图资源，如果有，则将其回收以释放内存空间，优化措施，避免内存泄漏和资源浪费
        //if not null ,do recycle
        if (readerContext.getBitmapData().getBgBitmap() != null) {
            readerContext.getBitmapData().getBgBitmap().recycle();
        }
        //获取当前页面的宽度和高度
        int width = readerContext.getPageParam().PageWidth;
        int height = readerContext.getPageParam().PageHeight;

        //init the bg bitmap
        /*调用TxtBitmapUtil.createBitmap()方法根据配置文件中的背景颜色、页面宽度和高度创建一个位图对象，
        并将其设置为readerContext对象的getBitmapData()方法返回的位图数据对象的背景位图*/
        readerContext.getBitmapData().setBgBitmap(TxtBitmapUtil.createBitmap(config.backgroundColor, width, height));
        //initPageParam
        //初始化页面参数
        initPageParam(readerContext);
        //start draw prepare

        //get preRead Progress
        /*startParagraphIndex表示段落的起始索引，通常用于文本处理中确定一个段落的开始位置。
          startCharIndex表示字符的起始索引，通常用于文本处理中确定一个字符在文本中的起始位置。*/
        int startParagraphIndex = 0;
        int startCharIndex = 0;
       /* 作用是获取文件消息中的段落和字符起始索引，并将其分别赋值给startParagraphIndex和startCharIndex变量
       * 首先判断readerContext对象的getFileMsg()方法返回的文件消息是否为null。
       * 如果不为null,则从文件消息中获取PreParagraphIndex和PreCharIndex属性的值，
       * 并将它们分别赋值给startParagraphIndex和startCharIndex变量*/
        if (readerContext.getFileMsg() != null) {
            startParagraphIndex = readerContext.getFileMsg().PreParagraphIndex;
            startCharIndex = readerContext.getFileMsg().PreCharIndex;
        }
       /* init  Context
       初始化页面参数，并创建一个TxtPageLoadTask对象来加载指定段落和字符的文本内容。*/
        initPainContext(readerContext.context, readerContext.getPaintContext(), readerContext.getTxtConfig());

        ITxtTask txtTask = new TxtPageLoadTask(startParagraphIndex, startCharIndex);
        txtTask.Run(callBack, readerContext);
    }

    /**
     *
     *
     * @param readerContext
     * @param config
     * 名为initTxtConfig,它接受两个参数：readerContext(文本读取器上下文对象)和config(文本配置对象)。
     */
    private void initTxtConfig(TxtReaderContext readerContext, TxtConfig config) {
        //获取是否显示笔记的设置
        config.showNote = TxtConfig.getIsShowNote(readerContext.context);
        //获取是否允许按压选择的设置
        config.canPressSelect = TxtConfig.getCanPressSelect(readerContext.context);
       // 获取文本颜色的设置
        config.textColor = TxtConfig.getTextColor(readerContext.context);
        //大小设置
        config.textSize = TxtConfig.getTextSize(readerContext.context);
        //获取背景颜色的设置
        config.backgroundColor = TxtConfig.getBackgroundColor(readerContext.context);
        //获取笔记文本颜色的设置
        config.NoteColor = TxtConfig.getNoteTextColor(readerContext.context);
        //获取选定文本颜色的设置
        config.SelectTextColor = TxtConfig.getSelectTextColor(readerContext.context);
        // 获取滑块颜色的设置
        config.SliderColor = TxtConfig.getSliderColor(readerContext.context);
        //:获取是否使用粗体文本的设置
        config.Bold = TxtConfig.isBold(readerContext.context);
        // 获取页面切换模式的设置
        config.Page_Switch_Mode = TxtConfig.getPageSwitchMode(readerContext.context);
        //获取是否显示特殊字符的设置
       // config.ShowSpecialChar = TxtConfig.IsShowSpecialChar(readerContext.context);
         config.VerticalPageMode = TxtConfig.IsOnVerticalPageMode(readerContext.context);
        //获取页面切换持续时间的设置
        config.PageSwitchDuration = TxtConfig.getPageSwitchDuration(readerContext.context);
    }

    /**
     * @param paintContext
     * @param txtConfig
     * 初始化一个绘图上下文
     */
    public static void initPainContext(Context context, PaintContext paintContext, TxtConfig txtConfig) {
        paintContext.textPaint.setTextSize(txtConfig.textSize);
        paintContext.textPaint.setFakeBoldText(txtConfig.Bold);
        paintContext.textPaint.setTextAlign(Paint.Align.LEFT);
        paintContext.textPaint.setColor(txtConfig.textColor);
        paintContext.textPaint.setAntiAlias(true);
        paintContext.notePaint.setTextSize(txtConfig.textSize);
        paintContext.notePaint.setColor(txtConfig.NoteColor);
        paintContext.notePaint.setTextAlign(Paint.Align.LEFT);
        paintContext.notePaint.setAntiAlias(true);
        paintContext.selectTextPaint.setTextSize(txtConfig.textSize);
        paintContext.selectTextPaint.setColor(txtConfig.SelectTextColor);
        paintContext.selectTextPaint.setTextAlign(Paint.Align.LEFT);
        paintContext.selectTextPaint.setAntiAlias(true);
        paintContext.sliderPaint.setColor(txtConfig.SliderColor);
        paintContext.sliderPaint.setAntiAlias(true);
        paintContext.textPaint.setFakeBoldText(txtConfig.Bold);
        paintContext.textPaint.setTypeface(null);
        if (txtConfig.VerticalPageMode) {
            AssetManager mgr = context.getAssets();
            Typeface tf = Typeface.createFromAsset(mgr, "fonts/text_style.TTF");
            paintContext.textPaint.setTypeface(tf);
        }
    }

    private void initPageParam(TxtReaderContext readerContext) {
        PageParam param = readerContext.getPageParam();
        int lineHeight = readerContext.getTxtConfig().textSize + param.LinePadding;
        param.LineHeight = lineHeight;
        if (!readerContext.getTxtConfig().VerticalPageMode) {
            param.LineWidth = param.PageWidth - param.PaddingLeft - param.PaddingRight;
            param.LineHeight = lineHeight;
            param.PageLineNum = (param.PageHeight - param.PaddingTop - param.PaddingBottom - readerContext.getTxtConfig().textSize - 2) / lineHeight + 1;
        } else {
            param.LineWidth = lineHeight;
            param.LineHeight = param.PageHeight - param.PaddingTop - param.PaddingBottom;
            param.PageLineNum = (param.PageWidth - param.PaddingLeft - param.PaddingRight - readerContext.getTxtConfig().textSize - 2) / lineHeight + 1;
        }
        param.PaddingLeft = TxtConfig.Page_PaddingLeft;
        param.LinePadding = TxtConfig.Page_LinePadding;
        param.PaddingRight = TxtConfig.Page_PaddingRight;
        param.PaddingTop = TxtConfig.Page_PaddingTop;
        param.PaddingBottom = TxtConfig.Page_PaddingBottom;
        param.ParagraphMargin = TxtConfig.Page_Paragraph_margin;
    }


}
