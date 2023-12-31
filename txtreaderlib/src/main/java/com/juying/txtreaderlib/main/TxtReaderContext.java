package com.juying.txtreaderlib.main;

import android.content.Context;

import com.juying.txtreaderlib.bean.TxtFileMsg;
import com.juying.txtreaderlib.interfaces.IChapter;
import com.juying.txtreaderlib.interfaces.IChapterMatcher;
import com.juying.txtreaderlib.interfaces.IPageDataPipeline;
import com.juying.txtreaderlib.interfaces.IParagraphData;

import java.util.List;


public class TxtReaderContext {

    public Context context;
    private IParagraphData paragraphData;
    private PageParam pageParam;
    private TxtFileMsg fileMsg;
    private List<IChapter> chapters;

    private IPageDataPipeline pageDataPipeline;
    private PaintContext paintContext;
    private TxtConfig txtConfig;
    private Boolean InitDone = false;
    private IChapterMatcher chapterMatcher;

    private final BitmapData bitmapData = new BitmapData();
    private final PageData pageData = new PageData();

    public TxtReaderContext(Context context) {
        this.context = context;
    }

    public TxtFileMsg getFileMsg() {
        return fileMsg;
    }

    public void setFileMsg(TxtFileMsg fileMsg) {
        this.fileMsg = fileMsg;
    }

    public void setParagraphData(IParagraphData paragraphData) {
        this.paragraphData = paragraphData;
    }

    public IParagraphData getParagraphData() {
        return paragraphData;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public Context getContext() {
        return context;
    }

    public List<IChapter> getChapters() {
        return chapters;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    //设置章节时计算每个章节的起始和结束段落
    public void setChapters(List<IChapter> chapters) {
        int previousEndIndex = -1;
        for (int i = 0; i < chapters.size(); i++) {
            IChapter chapter = chapters.get(i);
            chapter.setStartParagraphIndex(previousEndIndex + 1);
            previousEndIndex = chapter.getEndParagraphIndex(); // 根据你的数据结构计算

        }

        this.chapters = chapters;
    }

    public IChapterMatcher getChapterMatcher() {
        return chapterMatcher;
    }

    public void setChapterMatcher(IChapterMatcher chapterMatcher) {
        this.chapterMatcher = chapterMatcher;
    }

    public PageData getPageData() {
        return pageData;
    }

    public IPageDataPipeline getPageDataPipeline() {
        if (pageDataPipeline == null) {
            if (getTxtConfig().VerticalPageMode) {
                pageDataPipeline = new VerticalPageDataPipeline(this);
            }else{
                pageDataPipeline = new PageDataPipeline(this);
            }
        }
        return pageDataPipeline;
    }

    public PaintContext getPaintContext() {
        if (paintContext == null) {
            paintContext = new PaintContext();
        }
        return paintContext;
    }

    public void setPaintContext(PaintContext paintContext) {
        this.paintContext = paintContext;
    }

    public void setPageDataPipeline(IPageDataPipeline pageDataPipeline) {
        this.pageDataPipeline = pageDataPipeline;
    }

    public TxtConfig getTxtConfig() {
        if (txtConfig == null) {
            txtConfig = new TxtConfig();
        }
        return txtConfig;
    }

    public void setTxtConfig(TxtConfig txtConfig) {
        this.txtConfig = txtConfig;
    }

    public BitmapData getBitmapData() {
        return bitmapData;
    }

    public Boolean InitDone() {
        return InitDone;
    }

    public void setInitDone(Boolean initDone) {
        InitDone = initDone;
    }

    public void Clear() {
        if (paragraphData != null) {
            paragraphData.Clear();
            paragraphData = null;
        }
        if (paintContext != null) {
            paintContext.onDestroy();
            paintContext = null;
        }
        if (chapters != null) {
            chapters.clear();
            chapters = null;
        }
        bitmapData.onDestroy();
        pageData.onDestroy();
        chapterMatcher = null;

    }

}
