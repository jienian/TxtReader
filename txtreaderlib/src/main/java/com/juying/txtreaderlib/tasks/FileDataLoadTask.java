package com.juying.txtreaderlib.tasks;

import com.juying.txtreaderlib.bean.Chapter;
import com.juying.txtreaderlib.bean.TxtMsg;
import com.juying.txtreaderlib.interfaces.IChapter;
import com.juying.txtreaderlib.interfaces.IChapterMatcher;
import com.juying.txtreaderlib.interfaces.ILoadListener;
import com.juying.txtreaderlib.interfaces.IParagraphData;
import com.juying.txtreaderlib.interfaces.ITxtTask;
import com.juying.txtreaderlib.main.ParagraphData;
import com.juying.txtreaderlib.main.TxtReaderContext;
import com.juying.txtreaderlib.utils.ELogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*从文件中读取文本数据，根据章节格式解析出章节信息。*/

public class FileDataLoadTask implements ITxtTask {
    private static final String ChapterPatternStr = "(^.{0,3}\\s*第)(.{1,9})[章节卷集部篇回](\\s*)";
    //用于标识该类的实例（日志记录、调试）
    private String tag = "FileDataLoadTask";
    private IChapterMatcher chapterMatcher;
    private boolean stop = false;

    public void onStop() {
        stop = true;
    }

    @Override
    public void Run(ILoadListener callBack, TxtReaderContext readerContext) {
        stop = false;
        IParagraphData paragraphData = new ParagraphData();
        chapterMatcher = readerContext.getChapterMatcher();
        List<IChapter> chapter = new ArrayList<>();
        callBack.onMessage("开始读取File数据");
        Boolean readSuccess = ReadData(readerContext.getFileMsg().FilePath, readerContext.getFileMsg().FileCode, paragraphData, chapter);
        if (readSuccess) {
            ELogger.log(tag, "读取数据成功");
            callBack.onMessage(" 读取File数据成功");
            readerContext.setParagraphData(paragraphData);
            readerContext.setChapters(chapter);
            ITxtTask txtTask = new TxtConfigInitTask();
            txtTask.Run(callBack, readerContext);

        } else {
            callBack.onFail(TxtMsg.InitError);
            callBack.onMessage("FileDataLoadTask上读取失败");
        }
        stop = true;
    }

    private Boolean ReadData(String filePath, String Charset, IParagraphData paragraphData, List<IChapter> chapters) {
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        ELogger.log(tag, "开始读取reader数据");
        ELogger.log(tag, "--file Charset:" + Charset);
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset));
            try {
                String data;
                int index = 0;
                int chapterIndex = 0;
                while (!stop && (data = bufferedReader.readLine()) != null) {
                    if (data.length() > 0) {
                        IChapter chapter = compileChapter(data, paragraphData.getCharNum(), index, chapterIndex);
                        paragraphData.addParagraph(data);
                        if (chapter != null) {
                            chapterIndex++;
                            chapters.add(chapter);
                        }
                        index++;
                    }
                }
                initChapterEndIndex(chapters, paragraphData.getParagraphNum());
                return !stop;
            } catch (IOException e) {
                ELogger.log(tag, "IOException:" + e.toString());
            }
        } catch (UnsupportedEncodingException e) {
            ELogger.log(tag, "UnsupportedEncodingException:" + e.toString());
        } catch (FileNotFoundException e) {
            ELogger.log(tag, "FileNotFoundException:" + e.toString());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    private void initChapterEndIndex(List<IChapter> chapters, int paragraphNum) {
        if (chapters != null && chapters.size() > 0) {
            for (int i = 0, sum = chapters.size(); i < sum; i++) {
                int nextIndex = i + 1;
                IChapter chapter = chapters.get(i);

                if (nextIndex < sum) {
                    int startIndex = chapter.getStartParagraphIndex();
                    int endIndex = chapters.get(nextIndex).getEndParagraphIndex() - 1;
                //    Log.d(tag, "initChapterEndIndex: 起始点");
                    if (endIndex < startIndex) {
                        endIndex = startIndex;
                        
                    }
                    chapter.setEndParagraphIndex(endIndex);
                } else {
                    int endIndex = paragraphNum - 1;
                 //   Log.d(tag, "initChapterEndIndex: 终点");
                    endIndex = Math.max(endIndex, 0);
                    chapter.setEndParagraphIndex(endIndex);
                }
            }
        }
    }



    /**
     * @param data              文本数据
     * @param chapterStartIndex 开始字符在全文中的位置
     * @param ParagraphIndex    段落位置
     * @param chapterIndex      章节位置
     * @return 没有识别到章节数据返回null
     */
    private IChapter compileChapter(String data, int chapterStartIndex, int ParagraphIndex, int chapterIndex) {

        if (chapterMatcher == null) {
            if (data.trim().startsWith("第") || data.contains("第")) {
                Pattern p = Pattern.compile(ChapterPatternStr);
                Matcher matcher = p.matcher(data);
                if (matcher.find()) {
                    int startIndex = 0;
                    int endIndex = data.length();
                    return new Chapter(chapterStartIndex, chapterIndex, data, ParagraphIndex, ParagraphIndex, startIndex, endIndex);
                }
            }
            return null;
        } else {
            return chapterMatcher.match(data, ParagraphIndex);
        }

    }
    private void analyzeChapters(List<IChapter> chapterIndex) {

        for (int i = 0; i < chapterIndex.size(); i++) {
            IChapter chapter = chapterIndex.get(i);

        }

    }
}
