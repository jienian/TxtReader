package com.juying.txtreaderlib.bean;

import android.graphics.Color;

/*这是一个名为NumChar的类，位于com.juying.txtreaderlib.bean包中。它继承自TxtChar类，并实现了以下功能：

1. 定义了一个静态常量DefaultTextColor,表示默认的文字颜色，值为十六进制颜色码"#45a1cf"。
2. 构造函数NumChar接受一个字符参数aChar,并调用父类TxtChar的构造函数进行初始化。
3. getTextColor()方法返回默认的文字颜色DefaultTextColor。
4. getCharType()方法返回字符类型Char_Num,表示该字符是一个数字字符。*/
public class NumChar extends TxtChar {
    public static  int DefaultTextColor = Color.parseColor("#45a1cf");
    public NumChar(char aChar) {
        super(aChar);
    }
    @Override
    public int getTextColor() {
        return DefaultTextColor;
    }

    @Override
    public int getCharType() {
        return Char_Num;
    }
}
