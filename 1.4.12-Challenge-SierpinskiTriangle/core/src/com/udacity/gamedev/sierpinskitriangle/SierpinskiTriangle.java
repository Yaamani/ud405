package com.udacity.gamedev.sierpinskitriangle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

/**
 * TODO: Start here
 *
 * Your challenge, should you choose to accept it, is to draw a Serpinski Triangle. I offer no hints
 * beyond the fact that ShapeRenderer has a very convenient triangle() function, and that using a
 * FitViewport can simplify matters considerably. Good luck!
 */


public class SierpinskiTriangle extends ApplicationAdapter {

    static final float SIZE = 10;
    private static final int RECURSIONS = 1;
    //private final float TRIANGLE_BOTTOM_LINE_LENGTH = (1 / (float) Math.pow(2, RECURSIONS)) * SIZE;

    private Array<Triangle> allowedToBeDrawn = new Array<Triangle>();

    ShapeRenderer renderer;
    FitViewport viewport;

    @Override
    public void create() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(SIZE, SIZE);

        drawSierpinskiTriangle(0, 0, renderer, RECURSIONS);

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply(); //Apply the viewport

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled); //start drawing (setting the batch)

        for (Triangle triangle : allowedToBeDrawn) {
            triangle.draw(renderer);
        }

        renderer.end();
    }

    private void drawSierpinskiTriangle(float bottomLeftX, float bottomLeftY, ShapeRenderer renderer, int recursion) {
        final float TRIANGLE_BOTTOM_LINE_LENGTH = (1 / (float) Math.pow(2, (RECURSIONS - recursion + 1))) * SIZE;

        /*Random random = new Random();

        Color customColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);*/

        Triangle leftTriangle = new Triangle(bottomLeftX, bottomLeftY,
                bottomLeftX + TRIANGLE_BOTTOM_LINE_LENGTH, bottomLeftY,
                bottomLeftX + .5f * TRIANGLE_BOTTOM_LINE_LENGTH, bottomLeftY + TRIANGLE_BOTTOM_LINE_LENGTH/*,
                customColor*/);

        Triangle topTriangle = new Triangle(leftTriangle.getX3(), leftTriangle.getY3(),
                leftTriangle.getX3() + TRIANGLE_BOTTOM_LINE_LENGTH, leftTriangle.getY3(),
                leftTriangle.getX3() + .5f * TRIANGLE_BOTTOM_LINE_LENGTH, leftTriangle.getY3() + TRIANGLE_BOTTOM_LINE_LENGTH/*,
                customColor*/);

        Triangle rightTriangle = new Triangle(topTriangle.getX2() - .5f * TRIANGLE_BOTTOM_LINE_LENGTH, topTriangle.getY2() - TRIANGLE_BOTTOM_LINE_LENGTH,
                topTriangle.getX2(), topTriangle.getY2(),
                topTriangle.getX2() + .5f * TRIANGLE_BOTTOM_LINE_LENGTH, topTriangle.getY2() - TRIANGLE_BOTTOM_LINE_LENGTH/*,
                customColor*/);

        if (recursion == 1) {
            allowedToBeDrawn.add(leftTriangle);
            allowedToBeDrawn.add(topTriangle);
            allowedToBeDrawn.add(rightTriangle);

            return;
        }

        drawSierpinskiTriangle(bottomLeftX, bottomLeftY, renderer, recursion - 1);
        drawSierpinskiTriangle(leftTriangle.getX3(), leftTriangle.getY3(), renderer, recursion - 1);
        drawSierpinskiTriangle(topTriangle.getX2() - .5f * TRIANGLE_BOTTOM_LINE_LENGTH, topTriangle.getY2() - TRIANGLE_BOTTOM_LINE_LENGTH, renderer, recursion - 1);
    }
}