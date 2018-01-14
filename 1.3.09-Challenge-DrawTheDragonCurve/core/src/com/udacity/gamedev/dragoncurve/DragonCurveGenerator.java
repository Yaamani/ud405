package com.udacity.gamedev.dragoncurve;

import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class DragonCurveGenerator {

    enum Direction {
        LEFT,
        RIGHT;

        public static Vector2 turn(Vector2 heading, Direction turn){
            Vector2 newHeading = new Vector2();
            switch (turn){
                case LEFT:
                    newHeading.x = -heading.y;
                    newHeading.y = heading.x;
                    break;
                case RIGHT:
                    newHeading.x = heading.y;
                    newHeading.y = -heading.x;
                    break;
            }
            return newHeading;
        }
    }

    static LinkedList<Direction> dragonTurns(int recursions){
        LinkedList<Direction> turns = new LinkedList<Direction>();
        turns.add(Direction.LEFT);

        for (int i = 0; i < recursions; i++){
            // TODO: Create a reversed copy of turns
            LinkedList<Direction> reversed = new LinkedList<Direction>();
            for (int c = turns.size() - 1; c >= 0; c--) {
                reversed.add(turns.get(c));
            }
            System.out.println("------------- i = " + i + " -------------");
            System.out.println("turns: " + turns);
            System.out.println("reversed: " + reversed);
            // TODO: Add a left turn to turns
            turns.add(Direction.LEFT);

            // TODO: Add reflected version of reversed to turns
            for (int ii = 0; ii < reversed.size(); ii++) {
                if (reversed.get(ii).equals(Direction.LEFT)) turns.add(Direction.RIGHT);
                else turns.add(Direction.LEFT);
            }
        }
        System.out.println("--------------------------------------------------");
        System.out.println(turns);
        return turns;
    }

    static float[] generateDragonCurve(int width, int height, int recursions){

        LinkedList<Direction> turns = DragonCurveGenerator.dragonTurns(recursions);

        Vector2 head = new Vector2(width/2, height/2);
        Vector2 heading = new Vector2(5, 0);

        float[] curve = new float[(turns.size() + 1) * 2];

        int i = 0;
        curve[i++] = head.x;
        curve[i++] = head.y;

        //TODO: Convert the list of turns into the actual path
        for (int c = 0; c < turns.size(); c++) {
            heading = Direction.turn(heading, turns.get(c));

            head.x += heading.x;
            head.y += heading.y;

            curve[i++] = head.x;
            curve[i++] = head.y;
        }

        System.out.print("curve: ");
        for (int ii = 0; ii < curve.length; ii++) {
            System.out.print(curve[ii] + ", ");
        }
        return curve;

    }


}
