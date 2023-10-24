package com.juying.txtreaderlib.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
/*定义属性名*/

public class TxtConfig {

    public static final String SAVE_NAME = "TxtConfig";
    /*字体大小*/
    public static final String C_TEXT_SIZE = "TEXT_SIZE ";
    /*字体颜色*/

    public static final String C_TEXT_COLOR = "TEXT_COLOR";
    /*注释文本的颜色*/
    public static final String C_NOTE_TEXT_COLOR = "TEXT_COLOR";
    /*通常用于在程序中引用滑块组件的颜色属性。*/
    public static final String C_SLIDER_COLOR = "SLIDER_COLOR";
    /*通常用于在程序中引用滑块组件的颜色属性。*/
    public static final String C_SELECT_TEXT_COLOR = "SELECTED_TEXT_COLOR";
    /*阅读文本颜色*/
    public static final String C_BACKGROUND_COLOR = "BACKGROUND_COLOR";
    /*用于在程序中引用是否显示注释的属性名。*/
    public static final String C_IS_SHOW_NOTE = "IS_SHOW_NOTE";
    /**/
    /*用于存储一个配置键*/
    public static final String C_CAN_PRESS_SELECT = "CAN_PRESS_SELECT";
    /*用于加粗文本*/
    public static final String C_BOLD = "BOLD ";
    /*是否显示特殊字符的属性名*/
    public static final String C_SHOW_SPECIAL_CHAR = "SHOW_SPECIAL_CHAR ";
    public static final String C_CENTER_CLICK_AREA = "CENTER_CLICK_AREA";
    public static final String C_PAGE_SWITCH_DURATION = "PAGE_SWITCH_DURATION";
    /*用于存储页面垂直模式*/
    public static final String C_PAGE_VERTICAL_MODE = "PAGE_VERTICAL_MODE ";

    /*设置文本颜色*/
    public static final String C_PAGE_SWITCH_TYPE_MODE = "PAGE_SWITCH_SYPE_MODE";
    /*仿真*/
    public static final int PAGE_SWITCH_MODE_SIMULATION = 1;//
    /*覆盖*/
    public static  final int PAGE_SWITCH_MODE_COVER = 2;//in px
    /*平移*/
    public static  final int PAGE_SWITCH_MODE_SERIAL = 3;//in px

    //public static  final int PAGE_SWITCH_MODE_SHEAR = 3;//in px

    public static  int Page_PaddingLeft = 20;//in px
    public static  int Page_PaddingBottom = 20;//in px
    public static  int Page_PaddingTop = 20;//in px
    public static  int Page_PaddingRight = 20;//in px
    public static  int Page_LinePadding = 30;//in px
    public static  int Page_Paragraph_margin = 20;//in px,为0，没有间距


    public   int Page_Switch_Mode = PAGE_SWITCH_MODE_COVER;
    public static  int MAX_TEXT_SIZE = 200;//字体最大不低于150
    public static  int MIN_TEXT_SIZE = 50;//字体最小不低于50
    public static  int DEFAULT_SELECT_TEXT_COLOR = Color.parseColor("#44f6950b");
    public static  int DEFAULT_SLIDER_COLOR = Color.parseColor("#1f4cf5");

    public int textSize = MIN_TEXT_SIZE;//字体大小
    public int textColor = Color.BLACK;//字体颜色
    public int backgroundColor = Color.WHITE;//背景颜色
    public int NoteColor = Color.RED;//笔记颜色
    public int SelectTextColor = DEFAULT_SELECT_TEXT_COLOR;//选中颜色
    public int SliderColor = DEFAULT_SLIDER_COLOR;//滑动条颜色

    public static  boolean DebugMode = true;//debug模式会进行打印日志，默认是开启

    public Boolean showNote = true;//是否显示笔记
    public Boolean canPressSelect = true;//是否能长按选中

    public Boolean VerticalPageMode = false;
    public Boolean Bold = false;//是否加粗
    public Boolean ShowSpecialChar = true;//是否显示特殊符号，对于数字、英文，可以显示特定颜色
    public float CenterClickArea = 0.35f;//0~1,中间点击区域占View宽度的百分比，区域为高为宽两倍的矩形，如果为1f，说明点击翻页将不起效果
    public int PageSwitchDuration = 400;//页面滑动时间间隔，毫秒，建议不要低于200

    /*于保存一些简单的配置信息或用户偏好设置等。
    这段代码中的方法可以实现以下功能：*/
    public static SharedPreferences getS(Context context) {
        SharedPreferences share = context.getSharedPreferences(SAVE_NAME, Context.MODE_PRIVATE);
        return share;
    }

    public static int getPageSwitchMode(Context context) {
        SharedPreferences share = getS(context);
        int PageSwitchMode =  share.getInt(C_PAGE_SWITCH_TYPE_MODE, PAGE_SWITCH_MODE_COVER);
        if(PageSwitchMode!=PAGE_SWITCH_MODE_COVER
                &&PageSwitchMode!= PAGE_SWITCH_MODE_SERIAL
                 && PageSwitchMode != PAGE_SWITCH_MODE_SIMULATION) {
               // &&PageSwitchMode!= PAGE_SWITCH_MODE_SHEAR){
            return PAGE_SWITCH_MODE_COVER;
        }
        return PageSwitchMode;
    }





    public static void savePageSwitchMode(Context context,int PageSwitchMode) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_PAGE_SWITCH_TYPE_MODE, PageSwitchMode);
        editor.apply();
    }


    /**
     * @param context
     * @param duration 不能低于100，建议200以上
     */
    public static void savePageSwitchDuration(Context context, int duration) {
        duration = Math.max(duration, 100);
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_PAGE_SWITCH_DURATION, duration);
        editor.apply();
    }

    public static int getPageSwitchDuration(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_PAGE_SWITCH_DURATION, 400);
    }

    public static void saveTextSize(Context context, int textSize) {
        textSize = Math.max(textSize, MIN_TEXT_SIZE);
        textSize = Math.min(textSize, MAX_TEXT_SIZE);
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_TEXT_SIZE, textSize);
        editor.apply();
    }

    public static int getTextSize(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_TEXT_SIZE, MIN_TEXT_SIZE);
    }

    public static void saveTextColor(Context context, int textColor) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_TEXT_COLOR, textColor);
        editor.apply();
        editor.commit();
    }

    public static int getTextColor(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_TEXT_COLOR, Color.BLACK);
    }

    public static void saveNoteTextColor(Context context, int textColor) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_NOTE_TEXT_COLOR, textColor);
        editor.apply();
        editor.commit();
    }

    public static int getNoteTextColor(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_NOTE_TEXT_COLOR, Color.BLACK);
    }

    public static void saveSelectTextColor(Context context, int textColor) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_SELECT_TEXT_COLOR, textColor);
        editor.apply();
        editor.commit();
    }

    public static int getSelectTextColor(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_SELECT_TEXT_COLOR, DEFAULT_SELECT_TEXT_COLOR);
    }


    public static void saveBackgroundColor(Context context, int BackgroundColor) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putInt(C_BACKGROUND_COLOR, BackgroundColor);
        editor.apply();
        editor.commit();
    }

    /*设置阅读背景颜色*/
    public static int getBackgroundColor(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_BACKGROUND_COLOR, Color.WHITE);
    }
    /*选择阅读背景颜色*/
/*
    public static void saveCenterClickArea(Context context, float CenterClickArea) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putFloat(C_CENTER_CLICK_AREA, CenterClickArea);
        editor.apply();
        editor.commit();
    }
*/

    public static float getCenterClickArea(Context context) {
        SharedPreferences share = getS(context);
        return share.getFloat(C_CENTER_CLICK_AREA, 0.3f);
    }

    public static void saveSliderColor(Context context, float CenterClickArea) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putFloat(C_SLIDER_COLOR, CenterClickArea);
        editor.apply();
        editor.commit();
    }

    public static int getSliderColor(Context context) {
        SharedPreferences share = getS(context);
        return share.getInt(C_SLIDER_COLOR, DEFAULT_SLIDER_COLOR);
    }

    public static void saveIsShowNote(Context context, Boolean IsShowNote) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(C_IS_SHOW_NOTE, IsShowNote);
        editor.apply();
        editor.commit();
    }

    public static Boolean getIsShowNote(Context context) {
        SharedPreferences share = getS(context);
        return share.getBoolean(C_IS_SHOW_NOTE, true);
    }

    public static void saveCanPressSelect(Context context, Boolean CanPressSelect) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(C_CAN_PRESS_SELECT, CanPressSelect);
        editor.apply();
        editor.commit();
    }

    public static Boolean getCanPressSelect(Context context) {
        SharedPreferences share = getS(context);
        return share.getBoolean(C_CAN_PRESS_SELECT, true);
    }


    public static void saveIsBold(Context context, Boolean bold) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(C_BOLD, bold);
        editor.apply();
        editor.commit();
    }

    public static Boolean isBold(Context context) {
        SharedPreferences share = getS(context);
        return share.getBoolean(C_BOLD, false);
    }
    /*显示符号*/
    public static void saveIsShowSpecialChar(Context context, Boolean showSpecialChar) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(C_SHOW_SPECIAL_CHAR, showSpecialChar);
        editor.apply();
     //   editor.commit();
    }
    /*从共享偏好设置*/
    public static Boolean IsShowSpecialChar(Context context) {
        SharedPreferences share = getS(context);
        return share.getBoolean(C_SHOW_SPECIAL_CHAR, true);
    }



    public static void saveIsOnVerticalPageMode(Context context, Boolean IsOnVerticalPageMode) {
        SharedPreferences share = getS(context);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(C_PAGE_VERTICAL_MODE, IsOnVerticalPageMode);
        editor.apply();
        editor.commit();
    }

    public static Boolean IsOnVerticalPageMode(Context context) {
        SharedPreferences share = getS(context);
        return share.getBoolean(C_PAGE_VERTICAL_MODE, false);
    }
}
