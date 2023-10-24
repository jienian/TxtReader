package com.juying.txtreaderlib.bean;

import com.juying.txtreaderlib.interfaces.IChapter;

import java.util.List;


public class Chapter implements IChapter {

    public String Title;//章节标题
    public int Start;//章节的起始位置
    public int End;//章节的结束位置


    public int Index;//章节位置
    public int StartParagraphIndex;//起始段落索引
    public int EndParagraphIndex;//结束段落索引
    public int StartCharIndex;//字符开始位置
    public int EndCharIndex;//字符结束位置
    public int StartIndex;//第一个字符在全文字符的位置


    /*定义了获取章节标题、起始位置、结束位置*/
    public Chapter(String title, int startParagraphIndex, int endParagraphIndex, int Start, int End) {
        this.Title = title;
        StartParagraphIndex = startParagraphIndex;
        EndParagraphIndex = endParagraphIndex;

        this.Start  = Start;
        this.End = End;
    }

    public Chapter(int startIndex, int index, String title, int startParagraphIndex, int endParagraphIndex, int startCharIndex, int endCharIndex) {
        StartIndex = startIndex;
        Title = title;
        Index = index;
        StartParagraphIndex = startParagraphIndex;
        EndParagraphIndex = endParagraphIndex;
        StartCharIndex = startCharIndex;
        EndCharIndex = endCharIndex;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public int getStartIndex() {
        return StartIndex;
    }




    @Override
    public int getStartCharIndex() {
        return StartCharIndex;
    }

    @Override
    public int getEndCharIndex() {
        return EndCharIndex;
    }

    @Override
    public int getStartParagraphIndex() {
        return StartParagraphIndex;
    }

    @Override
    public int getEndParagraphIndex() {
        return EndParagraphIndex;
    }

    @Override
    public String getTitle() {
        return Title;
    }



    @Override
    public void setStartParagraphIndex(int index) {
        StartParagraphIndex = index;
    }

    @Override
    public void setEndParagraphIndex(int index) {
        EndParagraphIndex = index;
    }





    @Override
    public int getIndex() {
        return Index;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "Title='" + Title + '\'' +
                ", Index=" + Index +
                ", Start= " + Start +
                ", End = " + End +
                ", StartParagraphIndex=" + StartParagraphIndex +
                ", EndParagraphIndex=" + EndParagraphIndex +
                ", StartCharIndex=" + StartCharIndex +
                ", EndCharIndex=" + EndCharIndex +

                '}';
    }
}
