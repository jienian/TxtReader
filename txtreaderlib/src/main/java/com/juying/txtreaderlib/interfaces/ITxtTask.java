package com.juying.txtreaderlib.interfaces;

import com.juying.txtreaderlib.main.TxtReaderContext;


public interface ITxtTask {
    void Run(ILoadListener callBack, TxtReaderContext readerContext);
}
