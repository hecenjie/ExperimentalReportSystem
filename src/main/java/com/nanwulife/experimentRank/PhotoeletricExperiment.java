package com.nanwulife.experimentRank;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 光电效应实验评分模块
 * @Author: Creams
 * @Date: Created in 2018/9/15
 */
public class PhotoeletricExperiment {
    //choice_x 选择题_序号  blank_x 填空题_序号
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
    private double blank_1;
    private double redI;
    private double blackI;
    private double blueI;
    
    private int rank = 0;

    public PhotoeletricExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9, String choice_10, String choice_11, double blank_1, double redI, double blackI, double blueI) {
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
        this.blank_1 = blank_1;
        this.redI = redI;
        this.blackI = blackI;
        this.blueI = blueI;
    }

    public int getRank(){
        if (choice_1.equals("A"))
            rank += 2;
        if (choice_2.equals("C"))
            rank += 2;
        if (choice_3.equals("A"))
            rank += 2;
        if (choice_4.equals("B"))
            rank += 3;
        if (choice_5.equals("A"))
            rank += 3;
        if (choice_6.equals("C"))
            rank += 3;
        if (choice_7.equals("B"))
            rank += 3;
        if (choice_8.equals("B"))
            rank += 3;
        if (choice_9.equals("C"))
            rank += 3;
        if (choice_10.equals("A"))
            rank += 3;
        if (choice_11.equals("C"))
            rank += 3;
        if (blank_1 >= 0 && blank_1 <= 3)
            rank += 45;
        else if (blank_1 > 3 && blank_1 <= 6)
            rank += 40;
        else if (blank_1 > 6 && blank_1 <= 10)
            rank += 35;
        else if (blank_1 > 10 && blank_1 <= 15)
            rank += 30;
        else if (blank_1 > 15 && blank_1 <= 20)
            rank += 25;
        else if (blank_1 > 20)
            rank += 20;
        if (redI > blueI && blueI > blackI)
            rank += 25;
        else 
            rank += 20;
        return rank;
    }
    
}
