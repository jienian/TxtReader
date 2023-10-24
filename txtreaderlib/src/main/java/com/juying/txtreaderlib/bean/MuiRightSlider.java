package com.juying.txtreaderlib.bean;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

/*是一个jQuery插件，用于创建一个右滑块。它可以用于创建各种类型的滑块，例如时间滑块、日期滑块、评分滑块等等。*/
public class MuiRightSlider extends Slider {
    @Override
    public float getX(float dx) {
        return Left + dx + 5;
    }

    @Override
    public float getY(float dy) {
        return Bottom + dy + 5;
    }

    @Override
    public Path getPath(TxtChar txtChar, Path path) {
        if (txtChar != null) {
            int r = SliderWidth;
            int leftWidth = (int) (Math.cos(30) * r);
            int heightWidth = r * 3 / 2;
            Path p = path;
            p.reset();
            p.moveTo(txtChar.Right, txtChar.Bottom);
            p.lineTo(txtChar.Right + leftWidth, txtChar.Bottom + heightWidth);

            Rect rect = new Rect(
                    txtChar.Right - r,
                    txtChar.Bottom +  r,
                    txtChar.Right + r,
                    txtChar.Bottom +3*r);

            p.addArc(new RectF(rect), -30, 240);
            p.lineTo(txtChar.Right, txtChar.Bottom);
            return p;
        } else {
            return null;
        }
    }
}