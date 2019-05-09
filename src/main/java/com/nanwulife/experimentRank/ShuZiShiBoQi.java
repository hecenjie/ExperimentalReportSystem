package com.nanwulife.experimentRank;

/**
 * @author 张文军
 * @Description:数字示波器实验判分类
 * @Company:南京农业大学工学院
 * @version:1.0
 * @date 2019/5/620:35
 */
public class ShuZiShiBoQi {
	/**
	 * 总分数
	 */
	private int score;
	/**
	 * 正确答案
	 */
	private String[] choices = {"D", "C", "E", "D", "B", "B", "B", "B", "A"};
	/**
	 * 学生所选的选项答案  new double[50]
	 */
	private String[] choice = new String[9];
	/**
	 * 每个选项的得分
	 */
	private int[] choiceScore = {3, 3, 3, 3, 3, 3, 2, 5, 8};

	private double[] blanks = {1, 3, 15, 20, 180, 180, 1000};
	private double[] blank = new double[7];
	private int[] blankScore = {2, 2, 3, 3, 3, 3, 4};

	private double[] tables = {0, 0, 14.5, 14.5, 0, 0, 2000, 2000, 500, 500, 0, 0, 9.5, 9.5, 0, 0, 1000, 1000, 1000, 1000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 12, 23, 21, 0, 1000, 14.7, 0, 1000, 4.8};
	private double[] table = new double[46];
	private int[] tableScore = {0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 5, 5, 5, 5, 5, 5};

	public ShuZiShiBoQi(String[] choice, String[] blank, String[] table) {
		for (int i = 0; i < choice.length; i++) {
			this.choice[i] = choice[i];
		}

		for (int i = 0; i < blank.length; i++) {
			if (blank[i].isEmpty()) {
				/** -0.0 表示考生没填*/
				blank[i] = "-0.0";
			}
			this.blank[i] = Double.parseDouble(blank[i]);
		}

		for (int i = 0; i < table.length; i++) {
			/**除去字符串中的所有空格*/
			table[i] = table[i].replace(" ", "");
			/**
			 * 去掉比例中的比号" : ";
			 */
			table[i] = table[i].replace(":", "");
			table[i] = table[i].replace("；", "");
			if (table[i].isEmpty()) {
				/** -0.0 表示考生没填*/
				table[i] = "-0.0";
			}
			this.table[i] = Double.parseDouble(table[i]);
		}
	}

	/**
	 * 统计得分
	 *
	 * @return
	 */
	public int getScore() {
		/**选择题*/
		for (int i = 0; i < choice.length; i++) {
			if (choice[i].equals(choices[i])) {
				score += choiceScore[i];
			}
		}
		/**填空题*/
		for (int i = 0; i < blank.length; i++) {
			if (blanks[i] == blank[i]) {
				score += blankScore[i];
			}
		}
		/**数据表单*/
		for (int i = 0; i < table.length; i++) {
			/**判断相应表项数据范围*/
			if ((i == 2 || i == 3) && (table[i] >= 14.5 || table[i] <= 15.5)) {
				score += tableScore[i];
			} else if ((i == 12 || i == 13) && (table[i] >= 9.5 || table[i] <= 10.5)) {
				score += tableScore[i];
			} else if ((i == 42) && (table[i] >= 14.7 || table[i] <= 15.3)) {
				score += tableScore[i];
			} else if ((i == 45) && (table[i] >= 4.8 || table[i] <= 5.3)) {
				score += tableScore[i];
			} else if (tables[i] == table[i]) {
				score += tableScore[i];
			}
		}
		return score;
	}
}
