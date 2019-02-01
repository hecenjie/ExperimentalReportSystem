package com.nanwulife.experimentRank;

public class NewtonRingExperiment {
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
    private String choice_18;

    private double R;
    private double sigema;

    private int score;

    public NewtonRingExperiment(String choice_1, String choice_2, String choice_3, String choice_4, String choice_5, String choice_6, String choice_7, String choice_8, String choice_9,
                                String choice_10, String choice_11, String choice_12, String choice_13, String choice_14, String choice_15, String choice_16, String choice_17, String choice_18,
                                double r, double sigema) {
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
        this.choice_18 = choice_18;
        R = r;
        this.sigema = sigema;
    }


    public int getScore(){
        if (choice_1.equals("A"))
            score += 3;
        if (choice_2.equals("D"))
            score += 3;
        if (choice_3.equals("C"))
            score += 3;
        if (choice_4.equals("B"))
            score += 3;
        if (choice_5.equals("C"))
            score += 3;
        if (choice_6.equals("A"))
            score += 3;
        if (choice_7.equals("B"))
            score += 1;
        if (choice_8.equals("D"))
            score += 1;
        if (choice_9.equals("C"))
            score += 1;
        if (choice_10.equals("A"))
            score += 1;
        if (choice_11.equals("D"))
            score += 1;
        if (choice_12.equals("C"))
            score += 1;
        if (choice_13.equals("C"))
            score += 4;
        if (choice_14.equals("B"))
            score += 4;
        if (choice_15.equals("C"))
            score += 4;
        if (choice_16.equals("D"))
            score += 4;
        if (choice_17.equals("A"))
            score += 4;
        if (choice_18.equals("A"))
            score += 4;

        if (R >= 1.8 && R <= 2.1)
            score += 42;
        else if (R >= 1.6 && R < 1.8 || R > 2.1 && R <= 2.2 )
            score += 39;
        else if (R >= 1.4 && R < 1.6 || R > 2.2 && R <= 2.3)
            score += 36;
        else if (R < 1.4 || R > 2.3)
            score += 32;


        if (sigema >= 0 && sigema <= 0.5)
            score += 10;
        else if (sigema > 0.5 && sigema <= 1)
            score += 8;
        else if (sigema > 1 && sigema <= 1.5)
            score += 6;
        else if (sigema > 1.5)
            score += 4;
        return score;
    }
}
