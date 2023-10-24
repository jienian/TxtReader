package com.juying.txtreaderlib.main;

/*这是一个名为PageParam的类，它用于设置文本阅读器(如电子书阅读器)中页面参数。这些参数包括页边距、行距、行宽、行高、文本内边距等，用于控制文本在页面上的显示效果。通过设置不同的参数值，可以实现不同的页面布局和排版效果。*/
public class PageParam {
    public int PaddingLeft = 20;
    public int PaddingBottom = 0;
    public int PaddingTop = 20;
    public int PaddingRight = 20;
    public int ParagraphMargin = 0;
    public int VerticalLinePadding = 30;//横行距
    public int HorizonalLinePadding = 30;//竖行距
    public int LinePadding = 30 ;
    public int PageLineNum = -1;
    public float LineWidth = 0;
    public float LineHeight = 0;
    public float TextPadding = 5;
    public int PageWidth = 0;
    public int PageHeight = 0;
}
