package com.example.credittask2;

public class ScoreManager {
    private static int score = 0;

    public static void reset() {
        score = 0;
    }

    public static void increment() {
        score++;
    }

    public static int getScore() {
        return score;
    }
}
