package com.juying.txtreaderlib.interfaces;
/*在当前页面发生变化时通知监听器*/


public interface IPageChangeListener {
    void onCurrentPage(float progress);
}
