package com.udacity.gamedev.sierpinskitriangle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Yamani on 10/7/2017.
 */

class Triangle {
    private final float x1; private final float y1;
    private final float x2; private final float y2;
    private final float x3; private final float y3;
    private final Color color;

    Triangle(float x1, float y1, float x2 ,float y2, float x3, float y3, Color color) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
        this.color = color;
    }

    Triangle(float x1, float y1, float x2 ,float y2, float x3, float y3) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.x3 = x3; this.y3 = y3;
        this.color = Color.WHITE;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.triangle(x1, y1, x2, y2, x3, y3, color, color, color);
    }
    float getX1() {
        return x1;
    }

    float getX2() {
        return x2;
    }

    float getX3() {
        return x3;
    }

    float getY1() {
        return y1;
    }

    float getY2() {
        return y2;
    }

    float getY3() {
        return y3;
    }

    Color getColor() {
        return color;
    }
}
