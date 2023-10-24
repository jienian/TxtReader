package com.juying.txtreaderlib.interfaces;



public interface IChapterMatcher {
    /**
     * @param paragraphData 段落数据
     * @param ParagraphIndex 当前段落位置
     * @return
     */
    IChapter match(String paragraphData, int ParagraphIndex);
}
