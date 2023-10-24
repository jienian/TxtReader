package com.juying.txtreaderlib.main;

import android.graphics.Bitmap;


/**
 * 存储页面和背景的Bitmap类。
 */
public class BitmapData {

    /**
     * 页面Bitmap数组。
     */
    private final Bitmap[] pages = new Bitmap[3];

    /**
     * 背景Bitmap。
     */
    private Bitmap BgBitmap;

    /**
     * 设置第一页Bitmap。
     *
     * @param page 页面Bitmap。
     */
    public void setFirstPage(Bitmap page) {
        pages[0] = page;
    }

    /**
     * 设置中间页Bitmap。
     *
     * @param page 页面Bitmap。
     */
    public void setMidPage(Bitmap page) {
        pages[1] = page;
    }

    /**
     * 设置最后一页Bitmap。
     *
     * @param page 页面Bitmap。
     */
    public void setLastPage(Bitmap page) {
        pages[2] = page;
    }

    /**
     * 获取第一页Bitmap。
     *
     * @return 页面Bitmap。
     */
    public Bitmap FirstPage() {
        return pages[0];
    }

    /**
     * 获取中间页Bitmap。
     *
     * @return 页面Bitmap。
     */
    public Bitmap MidPage() {
        return pages[1];
    }

    /**
     * 获取最后一页Bitmap。
     *
     * @return 页面Bitmap。
     */
    public Bitmap LastPage() {
        return pages[2];
    }

    /**
     * 设置背景Bitmap。
     *
     * @param bgBitmap 背景Bitmap。
     */
    public void setBgBitmap(Bitmap bgBitmap) {
        BgBitmap = bgBitmap;
    }

    /**
     * 获取背景Bitmap。
     *
     * @return 背景Bitmap。
     */
    public Bitmap getBgBitmap() {
        return BgBitmap;
    }

    /**
     * 获取所有页面Bitmap。
     *
     * @return 所有页面Bitmap。
     */
    public Bitmap[] getPages() {
        return pages;
    }

    /**
     * 在销毁时回收所有Bitmap。
     */
    public void onDestroy() {
        recycle(getBgBitmap());
        recycle(FirstPage());
        recycle(MidPage());
        recycle(LastPage());
    }

    /**
     * 回收Bitmap。
     *
     * @param bitmap 要回收的Bitmap。
     */
    private void recycle(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
    }
}
