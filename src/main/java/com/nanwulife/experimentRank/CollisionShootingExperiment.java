package com.nanwulife.experimentRank;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 碰撞打靶实验评分模块
 * @Author: Creams
 * @Date: Created in 2019/1/30
 */

public class CollisionShootingExperiment {
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

    private double lose;

    private double x1,x2,x3,x4,x5,x6;
    private double z1,z2,z3,z4,z5,z6;

    private double hTheory;
    private double hTruth;

    private double z;
    private double x;

    private int score = 0;

    public CollisionShootingExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9, String choice_10,
                                       double lose, double x1, double x2, double x3, double x4, double x5, double x6,
                                       double z1, double z2, double z3, double z4, double z5, double z6, double hTheory, double hTruth) {
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
        this.lose = lose;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.x6 = x6;
        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
        this.z4 = z4;
        this.z5 = z5;
        this.z6 = z6;
        this.hTheory = hTheory;
        this.hTruth = hTruth;
        this.z = Math.abs(z1)+Math.abs(z2)+Math.abs(z3)+Math.abs(z4)+Math.abs(z5)+Math.abs(z6);
        this.x = Math.abs(x4-20.4)+Math.abs(x5-20.4)+Math.abs(x6-20.4);
    }



    public int getScore(){
        if (choice_1.equals("C"))
            score += 3;
        if (choice_2.equals("A"))
            score += 3;
        if (choice_3.equals("B"))
            score += 3;
        if (choice_4.equals("A"))
            score += 3;
        if (choice_5.equals("B"))
            score += 3;
        if (choice_6.equals("D"))
            score += 3;
        if (choice_7.equals("A"))
            score += 3;
        if (choice_8.equals("A"))
            score += 3;
        if (choice_9.equals("C"))
            score += 3;
        if (choice_10.equals("A"))
            score += 3;

        if (hTruth - hTheory > 0.4 && hTruth - hTheory < 1.0)
            score += 30;
        else if (hTruth - hTheory < 0)
            score += 10;
        else
            score += 25;

        if (lose > 5 && lose < 15)
            score += 10;
        else if (lose > 1 && lose < 5 || lose > 15 && lose < 20)
            score += 7;
        else
            score += 4;

        if (z <= 0.6 && x <= 0.3)
            score += 30;
        else if (z < 1.2 && z > 0.6 && x < 0.6 && x > 0.3)
            score += 25;
        else
            score += 20;
        return score;
    }
}
