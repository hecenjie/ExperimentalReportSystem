package com.nanwulife.experimentRank;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 光栅衍射及光波波长的测定实验评分模块
 * @Author: Creams
 * @Date: Created in 2018/11/24
 */
public class GratingdiffractionExperiment {
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
    
    
    private double blank_1;
    private double blank_2;
    private double blank_3;
    private double blank_4;

    private int rank = 0;

    public GratingdiffractionExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9, String choice_10, String choice_11, String choice_12, String choice_13, 
                                       double blank_1, double blank_2, double blank_3, double blank_4) {
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

        this.blank_1 = blank_1;
        this.blank_2 = blank_2;
        this.blank_3 = blank_3;
        this.blank_4 = blank_4;
    }
    
    public int getRank(){
        if (choice_1.equals("B"))
            rank += 3;
        if (choice_2.equals("A"))
            rank += 3;
        if (choice_3.equals("B"))
            rank += 3;
        if (choice_4.equals("G"))
            rank += 2;
        if (choice_5.equals("E"))
            rank += 2;
        if (choice_6.equals("B"))
            rank += 2;
        if (choice_7.equals("D"))
            rank += 2;
        if (choice_8.equals("D"))
            rank += 3;
        if (choice_9.equals("A"))
            rank += 3;
        if (choice_10.equals("B"))
            rank += 3;
        if (choice_11.equals("D"))
            rank += 3;
        if (choice_12.equals("C"))
            rank += 3;
        if (choice_13.equals("A"))
            rank += 3;

        if (blank_1 >= 9.00 && blank_1 <= 9.85)
            rank += 20;
        else
            rank += 10;

        if (blank_2 >= 19.00 && blank_2 <= 19.85)
            rank += 20;
        else 
            rank += 10;

        if (blank_3 >= 9.80 && blank_3 <= 10.2)
            rank += 13;
        else 
            rank += 6;

        if (blank_4 >= 9.80 && blank_4 <= 10.2)
            rank += 12;
        else 
            rank += 6;

        return rank;
    }
    
    
}
