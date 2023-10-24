package com.juying.txtreaderlib.tasks;

import com.juying.txtreaderlib.interfaces.IChapter;
import com.juying.txtreaderlib.interfaces.ILoadListener;
import com.juying.txtreaderlib.interfaces.IParagraphData;
import com.juying.txtreaderlib.interfaces.ITxtTask;
import com.juying.txtreaderlib.main.ParagraphData;
import com.juying.txtreaderlib.main.TxtReaderContext;
import com.juying.txtreaderlib.utils.ELogger;

import java.util.ArrayList;
import java.util.List;

/*实现一个文本加载任务
* readerContext表示读取上下文
* callBack表示加载监听器*/

public class TextLoader {
    private final String tag = "FileDataLoadTask";

    public void load(String text, TxtReaderContext readerContext, ILoadListener callBack) {
        IParagraphData paragraphData = new ParagraphData();
        List<IChapter> chapter = new ArrayList<>();

        callBack.onMessage("start read text");
        ELogger.log(tag, "start read text");
        paragraphData.addParagraph(text + "");
        readerContext.setParagraphData(paragraphData);
        readerContext.setChapters(chapter);
        ITxtTask txtTask = new TxtConfigInitTask();
        txtTask.Run(callBack, readerContext);
    }
}
