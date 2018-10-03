package com.nanwulife.experimentRank;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 太阳能实验评分模块
 * @Author: Creams
 * @Date: Created in 2018/10/03
 */
public class SolarEnergyExperiment {
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
    private double FF1;
    private double FF2;
    private int rank = 0;

    public SolarEnergyExperiment(String choice_1, String choice_2, 
                                 String choice_3, String choice_4, 
                                 String choice_5, String choice_6, 
                                 String choice_7, String choice_8, 
                                 String choice_9, String choice_10, 
                                 double FF1, double FF2) {
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
        this.FF1 = FF1;
        this.FF2 = FF2;
    }

    public int getRank(){
        if (choice_1.equals("C"))
            rank += 4;
        if (choice_2.equals("B"))
            rank += 4;
        if (choice_3.equals("C"))
            rank += 4;
        if (choice_4.equals("D"))
            rank += 4;
        if (choice_5.equals("B"))
            rank += 4;
        if (choice_6.equals("B"))
            rank += 4;
        if (choice_7.equals("C"))
            rank += 4;
        if (choice_8.equals("D"))
            rank += 4;
        if (choice_9.equals("D"))
            rank += 4;
        if (choice_10.equals("C"))
            rank += 4;
        if (FF1 > 0.8 || FF1 < 0.3)
            rank += 20;
        else
            rank += 30;
        if (FF2 > 0.8 || FF2 < 0.3)
            rank += 20;
        else
            rank += 30;
        return rank;
    }
    
    
}
