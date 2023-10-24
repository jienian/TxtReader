package com.juying.txtreaderlib.interfaces;

import com.juying.txtreaderlib.bean.TxtChar;


public interface ISliderListener {
    void onShowSlider(TxtChar txtChar);
    void onShowSlider(String CurrentSelectedText);
    void onReleaseSlider();
}
