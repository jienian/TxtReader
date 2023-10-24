package com.juying.txtreaderlib.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.juying.txtreaderlib.R;
import com.juying.txtreaderlib.interfaces.IChapter;

import java.util.List;

public class ChapterList extends PopupWindow {
    /*上下文对象，用于获取系统服务和资源*/
    private Context mContext;
    /*：列表视图对象，用于显示章节列表*/
    private ListView mRootView;
    /*适配器对象，用于将数据绑定到列表视图上*/
    private MyAdapter mAdapter;
    /*章节列表，存储了所有要显示的章节信息*/
    private List<IChapter> mChapters;
    /*当前选中章节的索引，初始值为-1*/
    private int CurrentIndex = -1;
    /*所有字符的总数，表示章节的总字符数*/
    private int AllCharNum;
    /*列表视图的高度*/
    private int ViewHeight;


    /*构造函数接受四个参数*/
    public ChapterList(Context mContext, int ViewHeight, List<IChapter> mChapters, int allCharNum) {
        super(mContext);
        this.mContext = mContext;
        this.ViewHeight = ViewHeight;
        this.mChapters = mChapters;
        this.AllCharNum = allCharNum;
        initRootView();
    }

    public int getAllCharNum() {
        return AllCharNum;
    }

    public void setCurrentIndex(int currentIndex) {
        CurrentIndex = currentIndex;
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public ListView getListView() {
        return mRootView;
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    /*顶部章节列表*/
    protected void initRootView() {
        WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        m.getDefaultDisplay().getMetrics(metrics);

        int ViewHeight = this.ViewHeight;
        int ViewWidth = metrics.widthPixels;
        mRootView = new ListView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRootView.setLayoutParams(params);
        this.setContentView(mRootView);
        this.setWidth(ViewWidth);
        this.setHeight(ViewHeight);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.HwTxtChapterMenuAnimation);
        /*紫色*/
        this.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E2212122")));

        /*   this.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1c1c1a")));*/
        mAdapter = new MyAdapter();
        mRootView.setAdapter(mAdapter);
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mChapters == null ? 0 : mChapters.size();
        }

        @Override
        public Object getItem(int i) {
            return mChapters.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private final int ColorNegative = Color.parseColor("#aeaca2");
        private final int ColorSelected = Color.parseColor("#fa4613");

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder;
            if (view == null) {
                holder = new Holder();
                view = LayoutInflater.from(mContext).inflate(R.layout.adapter_chapterlist, null);
                holder.index = view.findViewById(R.id.adapter_chapterList_index);
                holder.title = view.findViewById(R.id.adapter_chapterList_title);
                holder.progress = view.findViewById(R.id.adapter_chapterList_progress);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            IChapter chapter = mChapters.get(i);
            if (CurrentIndex == i) {
                holder.title.setTextColor(ColorSelected);
                holder.progress.setTextColor(Color.WHITE);
                holder.progress.setText("当前");
            } else {
                holder.title.setTextColor(Color.WHITE);
                holder.progress.setTextColor(ColorNegative);
                float p = 0;
                if (AllCharNum > 0) {
                    p = (float) chapter.getStartIndex() / (float) AllCharNum;
                    if (p > 1) {
                        p = 1;
                    }
                }
                holder.progress.setText((int) (p * 100) + "%");
            }

            holder.index.setText(i + 1 + "");
            holder.title.setText((chapter.getTitle() + "").trim());
            return view;
        }

        private class Holder {
            TextView index;
            TextView title;
            TextView progress;
        }
    }


    /*帮助垃圾收集器回收内存*/
    public void onDestroy() {
        mContext = null;
        mRootView = null;
        mAdapter = null;
        if (mChapters != null) {
            mChapters.clear();
            mChapters = null;
        }
    }

}
