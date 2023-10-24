package com.juying.txtreaderlib.interfaces;



public interface IChapter {

    int getIndex();
    /*：返回章节开始的段落索引*/
    int getStartParagraphIndex();
    /*返回章节结束的段落索引*/
    int getEndParagraphIndex();

    int getStartCharIndex();//返回章节开始的字符索引

    int getEndCharIndex();//返回章节结束的字符索引

    int getStartIndex();//获取章节起始位置的方法

    String getTitle();// 获取章节标题

    void setStartParagraphIndex(int index);

    void setEndParagraphIndex(int index);


}
