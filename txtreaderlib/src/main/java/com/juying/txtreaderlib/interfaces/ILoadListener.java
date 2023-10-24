package com.juying.txtreaderlib.interfaces;


import com.juying.txtreaderlib.bean.TxtMsg;

/*接口通常用于Android中的图片加载、数据加载等场景*/
public interface ILoadListener {
    void onSuccess();
    void onFail(TxtMsg txtMsg);
    void onMessage(String message);
}
