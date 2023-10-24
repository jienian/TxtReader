package com.juying.txtreaderlib.main;

import com.juying.txtreaderlib.bean.TxtMsg;
import com.juying.txtreaderlib.interfaces.ILoadListener;


//一种适配器模式来处理加载事件
public class LoadListenerAdapter implements ILoadListener{
    @Override
    public void onSuccess() {
    }

    @Override
    public void onFail(TxtMsg txtMsg) {
    }

    @Override
    public void onMessage(String message) {
    }
}
