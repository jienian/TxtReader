package com.juying.txtreaderlib.interfaces;


public interface IPageDataPipeline {
    IPage getPageStartFromProgress(int paragraphIndex, int charIndex);
    IPage getPageEndToProgress(int paragraphIndex, int charIndex);
}
