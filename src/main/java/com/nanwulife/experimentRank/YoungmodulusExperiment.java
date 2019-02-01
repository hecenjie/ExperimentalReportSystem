package com.nanwulife.experimentRank;

import com.sun.corba.se.pept.transport.ReaderThread;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 杨氏模量实验评分模块
 * @Author: Creams
 * @Date: Created in 2019/1/18
 */
public class YoungmodulusExperiment {
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
    private String choice_12;
    private String choice_13;
    private String choice_14;
    private String choice_15;
    private String choice_16;
    private String choice_17;

    private double result_d;
    private double s7_s7;
    private double s4_s3;
    private double F;
    private long E;
    
    private int score;

    public YoungmodulusExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9, String choice_10, String choice_11, String choice_12, String choice_13, String choice_14, String choice_15, String choice_16, String choice_17, double result_d, double s7_s7, double s4_s3, double f, long e) {
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
        this.choice_12 = choice_12;
        this.choice_13 = choice_13;
        this.choice_14 = choice_14;
        this.choice_15 = choice_15;
        this.choice_16 = choice_16;
        this.choice_17 = choice_17;
        this.result_d = result_d;
        this.s7_s7 = s7_s7;
        this.s4_s3 = s4_s3;
        F = f;
        E = e;

    }

    public int getScore()
    {
        if (choice_1.equals("C"))
            score += 3;
        if (choice_2.equals("B"))
            score += 3;
        if (choice_3.equals("C"))
            score += 2;
        if (choice_4.equals("A"))
            score += 2;
        if (choice_5.equals("B"))
            score += 2;
        if (choice_6.equals("D"))
            score += 2;
        if (choice_7.equals("A"))
            score += 2;
        if (choice_8.equals("C"))
            score += 2;
        if (choice_9.equals("C"))
            score += 2;
        if (choice_10.equals("A"))
            score += 2;
        if (choice_11.equals("B"))
            score += 2;
        if (choice_12.equals("E"))
            score += 2;
        if (choice_13.equals("D"))
            score += 2;
        if (choice_14.equals("C"))
            score += 3;
        if (choice_15.equals("A"))
            score += 3;
        if (choice_16.equals("C"))
            score += 2;
        if (choice_17.equals("D"))
            score += 6;

        if (result_d >= 0.0003 && result_d <= 0.0007)
            score += 15;
        else
            score += 8;
        
        if (s7_s7 >= 0 && s7_s7 <= 0.5)
            score += 20;
        else if (s7_s7 >= 0.51 && s7_s7 <= 2)
            score += 15;
        else if (s7_s7 > 2)
            score += 8;
        
        if (Math.abs(s4_s3) > 0.1)
            score += 8;
        
        if (F == 39.2)
            score += 9;
        
        if (E > 10 && E < 9 * Math.pow(10, 12))
            score += 6;
        return score;
    }
}
