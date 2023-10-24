package com.txtreader;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.juying.txtreaderlib.main.LoadListenerAdapter;
import com.juying.txtreaderlib.main.TxtReaderView;


public class MoreDisplayActivity extends AppCompatActivity {
    private final int[] backgroundColors = new int[]{
            //四种背景颜色
            Color.parseColor("#ccebcc"),//浅绿色
            Color.parseColor("#d4c7a5"),
            Color.parseColor("#393330"),
            Color.parseColor("#00141f"),
    };
    //用于存储四种不同的文本颜色
    private final int[] textColors = new int[]{
            Color.parseColor("#505550"),//灰色
            Color.parseColor("#453e33"),
            Color.parseColor("#8f8e88"),
            Color.parseColor("#27576c")
    };
    //用于在不同的线程之间发送和处理消息
    private Handler handler;

    //TextView控件的背景颜色、文本颜色和延迟时间
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_display_more);
        setReaderView(findViewById(R.id.txtReaderView_g_1), backgroundColors[0], textColors[0], 20);
        setReaderView(findViewById(R.id.txtReaderView_g_2), backgroundColors[1], textColors[1], 200);
        setReaderView(findViewById(R.id.txtReaderView_g_3), backgroundColors[2], textColors[2], 400);
        setReaderView(findViewById(R.id.txtReaderView_g_4), backgroundColors[3], textColors[3], 600);
    }


    /*延迟任务来加载文本文件，并在文件加载成功后设置阅读器的样式*/
    private void setReaderView(final TxtReaderView readerView, final int background, final int textColor, long delatLoadTime) {
        handler.postDelayed(() -> {
            String path = getIntent().getStringExtra("filePath");
            readerView.loadTxtFile(path, new LoadListenerAdapter() {
                @Override
                public void onSuccess() {
                    readerView.setStyle(background, textColor);
                }
            });
        }, delatLoadTime);
    }
    /*目的是提升性能和稳定，
     * onPause()方法来执行一些清理操作*/

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
