package com.nanwulife.experimentRank;

public class InertiaExperiment {
    private String choice_1;
    private String choice_2;
    private String choice_3;
    private String choice_4;
    private String choice_5;
    private String choice_6;
    private String choice_7;
    private String choice_8;
    private String choice_9;
    private String choice_10;
    private String choice_11;


    private double error;
    private double j4;
    private double j3;
    private double j2;
    private double j1;

    private int score = 0;

    public InertiaExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9, String choice_10, String choice_11, double error, double j4, double j3, double j2, double j1) {
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_3 = choice_3;
        this.choice_4 = choice_4;
        this.choice_5 = choice_5;
        this.choice_6 = choice_6;
        this.choice_7 = choice_7;
        this.choice_8 = choice_8;
        this.choice_9 = choice_9;
        this.choice_10 = choice_10;
        this.choice_11 = choice_11;
        this.error = error;
        this.j4 = j4;
        this.j3 = j3;
        this.j2 = j2;
        this.j1 = j1;
    }

    public int getScore() {
        if (choice_1.equals("B"))
            score += 2;
        if (choice_2.equals("C"))
            score += 2;
        if (choice_3.equals("A"))
            score += 2;
        if (choice_4.equals("B"))
            score += 3;
        if (choice_5.equals("B"))
            score += 3;
        if (choice_6.equals("A"))
            score += 3;
        if (choice_7.equals("C"))
            score += 3;
        if (choice_8.equals("B"))
            score += 3;
        if (choice_9.equals("C"))
            score += 3;
        if (choice_10.equals("B"))
            score += 3;
        if (choice_11.equals("D"))
            score += 3;

        if (error >= 0 && error <= 3)
            score += 35;
        else if (error > 3 && error <= 6)
            score += 30;
        else if (error > 6 && error <= 10)
            score += 25;
        else if (error > 10 && error <= 15)
            score += 20;
        else if (error > 15 && error <= 20)
            score += 15;
        else if (error > 20)
            score += 10;

        if (j4 > j3 && j3 > j2 && j2 > j1)
            score += 35;
        else
            score += 25;

        return score;
    }
}
