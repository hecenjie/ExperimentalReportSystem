package com.nanwulife.experimentRank;

/**
 * @author 张文军
 * @Description:光片正判分类 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/7/2515:39
 */
public class GuangPianZhengPanPanfen {
    /**
     * 总分数
     */
    private int score = 0;
    /**
     * 正确答案
     */
    private String[] choices = {"C", "A", "A", "B", "C", "D", "A", "A", "D", "D", "A", "E", "F", "B", "F", "F"};
    /**
     * 学生所选的选项答案  new double[50]
     */
    private String[] choice = new String[16];
    /**
     * 每个选项的得分
     */
    private int[] choiceScore = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4};


    /**
     * 接收第三个表格判分数据
     */
    private Float table3_13;


    /**
     * 接收参数
     *
     * @param choice 选择题
     * @param blank  填空题
     * @param table1 表格1数据
     * @param table2 表格数据
     * @param table3 表格1数据
     */
    public GuangPianZhengPanPanfen(String[] choice, String[] blank, String[] table1, String[] table2, String[] table3) {

        /**
         * 接受选择题学生所选答案
         */
        for (int i = 0; i < choice.length; i++) {
            this.choice[i] = choice[i];
        }

        this.table3_13 = Float.parseFloat(table3[table3.length - 1]);

    }

    /**
     * 判分
     *
     * @return
     */

    public int getScore() {

        /**
         * 计算选择题
         */
        for (int i = 0; i < choices.length; i++) {
            if (choice[i].equals(choices[i])) {
                score = score + choiceScore[i];
            }
        }

        /**
         * 计算第三个表格数据
         */
        if (table3_13 >= 0.92 * 1.55 && table3_13 <= 1.55) {
            score = score + 46;
        } else if (table3_13 > 0.8 * 1.55 && table3_13 < 0.92 * 1.55) {
            score = score + 36;
        } else {
            score = score + 26;
        }


        return score;
    }
}
