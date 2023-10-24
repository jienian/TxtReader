package com.juying.txtreaderlib.utils;

import android.util.Log;

import com.juying.txtreaderlib.interfaces.ITxtReaderLoggerListener;
import com.juying.txtreaderlib.main.TxtConfig;


public class ELogger {
    private static ITxtReaderLoggerListener l;
    public static void setLoggerListener(ITxtReaderLoggerListener l) {
        ELogger.l = l;
    }
    public static void log(String tag, String msg) {
        if(TxtConfig.DebugMode) {
            Log.e(tag, msg + "");
            if (l != null) {
                l.onLog(tag, msg + "");
            }
        }
    }

}
