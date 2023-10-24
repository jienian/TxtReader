package com.juying.txtreaderlib.bean;

import android.graphics.Path;

/*ShowBellow(布尔类型):表示是否显示在下方，默认值为true。
Left(整型):表示左侧位置。
Right(整型):表示右侧位置。
Top(整型):表示顶部位置。
Bottom(整型):表示底部位置。
SliderWidth(整型):表示滑动条的宽度。
此外，该类还包含以下抽象方法：

getX(float dx):手势判断位置x坐标的抽象方法。
getY(float dy):手势判断位置y坐标的抽象方法。
getPath(TxtChar txtChar, Path path):获取当前滑动条Path的抽象方法。*/

public abstract class Slider {
    public Boolean ShowBellow= true;
    public int Left;
    public int Right;
    public int Top;
    public int Bottom;
    public int SliderWidth;

    public abstract float getX(float dx);//手势判断位置x坐标
    public abstract float getY(float dy);//手势判断位置y坐标
    public abstract Path getPath(TxtChar txtChar,Path path);//获取当前滑动条Path
    @Override
    public String toString() {
        return "Slider{" +
                "ShowBellow=" + ShowBellow +
                ", Left=" + Left +
                ", Right=" + Right +
                ", Top=" + Top +
                ", Bottom=" + Bottom +
                '}';
    }
}
