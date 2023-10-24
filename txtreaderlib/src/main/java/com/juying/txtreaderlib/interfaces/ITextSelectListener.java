package com.juying.txtreaderlib.interfaces;

import com.juying.txtreaderlib.bean.TxtChar;



public interface ITextSelectListener {
    void onTextChanging(TxtChar firstSelectedChar,TxtChar lastSelectedChar);
    void onTextChanging(String selectText);
    void onTextSelected(String selectText);
}
