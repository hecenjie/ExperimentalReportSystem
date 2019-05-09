package com.nanwulife.util;

import com.deepoove.poi.XWPFTemplate;
import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project ExperimentalReportSystem
 * @Description 报告word生成模块
 * @author Creams
 * createtime 2018年09月17日
 *
 */
public class WordToNewWordUtil {

    public void writeDocx(String path, Map<String, String> map) throws Exception {
        InputStream is = new FileInputStream(path);
        XWPFDocument doc = new XWPFDocument(is);
        // XWPFWordExtractor extractor = new XWPFWordExtractor(doc) ;
        // String text = extractor.getText();
        // System.out.println(text);
        // CoreProperties coreProps = extractor.getCoreProperties();
        // this.printCoreProperties(coreProps);
        // this.close(is);
    }


    /**
     * 替换段落里面的变量
     *
     * @param doc
     *            要替换的文档
     * @param params
     *            参数
     */
    private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para
     *            要替换的段落
     * @param params
     *            参数
     */
    private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                matcher = this.matcher(runText);
                if (matcher.find()) {
                    while ((matcher = this.matcher(runText)).find()) {
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                    }
                    // 直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                    // 所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                    para.removeRun(i);
                    if(runText.equals("null")){
                        runText="";
                    }
                    para.insertNewRun(i).setText(runText);
                }
            }
        }
    }

    /**
     * 替换表格里面的变量
     *
     * @param doc
     *            要替换的文档
     * @param params
     *            参数
     */
    private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {

                    String cellTextString = cell.getText();
                    for (Entry<String, Object> e : params.entrySet()) {
                        if (cellTextString.contains("${"+e.getKey()+"}"))
                            cellTextString = cellTextString.replace("${"+e.getKey()+"}", e.getValue().toString());
                    }
                    cell.removeParagraph(0);
                    if(cellTextString.contains("${") && cellTextString.contains("}")){
                        cellTextString = "";
                    }
                    cell.setText(cellTextString);
//                    paras = cell.getParagraphs();
//					for (XWPFParagraph para : paras) {
//						this.replaceInPara(para, params);
//					}

                }
            }
        }
    }

    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }

    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输出CoreProperties信息
     *
     * @param coreProps
     */
    private void printCoreProperties(CoreProperties coreProps) {
        System.out.println(coreProps.getCategory()); // 分类
        System.out.println(coreProps.getCreator()); // 创建者
        System.out.println(coreProps.getCreated()); // 创建时间
        System.out.println(coreProps.getTitle()); // 标题
    }

    /**
     * word占位用${object}有缺陷不能填充图片
     * @param filePath
     * @param params
     * @throws Exception
     */
    public static String templateWrite(String filePath, Map<String, Object> params,String outFilePath)throws Exception{
        InputStream is = new FileInputStream(filePath);
        WordToNewWordUtil writeWordUtil = new WordToNewWordUtil();
        XWPFDocument doc = new XWPFDocument(is);
        // 替换段落里面的变量
        writeWordUtil.replaceInPara(doc, params);
        // 替换表格里面的变量
        writeWordUtil.replaceInTable(doc, params);
        OutputStream os = new FileOutputStream(outFilePath);
        doc.write(os);
        writeWordUtil.close(os);
        writeWordUtil.close(is);
        os.flush();
        os.close();
        return "";
    }
    /**
     * word占位用{{object}}比较完美可以填充图片
     * @param filePath
     * @param params
     * @param outFilePath
     * @return
     * @throws Exception
     */
    public static String templateWrite2(String filePath, Map<String, Object> params,String outFilePath)throws Exception{
        XWPFTemplate template = XWPFTemplate.compile(filePath).render(params);
        FileOutputStream out = new FileOutputStream(outFilePath);
        template.write(out);
        out.flush();
        out.close();
        return "";
    }


    public static void main(String[] args) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("choice_1", "A");
        params.put("choice_2", "B");//
        params.put("choice_3", "C");//
        params.put("choice_4", "A");//
        params.put("choice_5", "B");//
        params.put("choice_6", "C");//
        params.put("choice_7", "C");//
        params.put("choice_8", "A");//
        params.put("choice_9", "A");//
        params.put("choice_10", "B");//
        params.put("table1_1", "B");//
        params.put("table1_3", "B");//
        params.put("table1_5", "B");//
        params.put("table1_7", "B");//
        params.put("table1_9", "B");//
        params.put("table1_11", "B");//
        params.put("table1_12", "B");//
        params.put("table1_14", "B");//
        params.put("table1_16", "B");//
        params.put("table1_18", "B");//
/*        params.put("stunum", "123456");//
        params.put("stuname", "A");//
        params.put("score", "A");//
        params.put("choice_01", "A");//
        params.put("choice_02", "B");//
        params.put("choice_03", "C");//
        params.put("choice_04", "A");//
        params.put("choice_05", "B");//
        params.put("choice_06", "C");//
        params.put("choice_07", "C");//
        params.put("choice_08", "A");//
        params.put("choice_09", "A");//
        params.put("choice_10", "B");//
        params.put("choice_11", "B");//


        params.put("blank_01_01", "5");//
        params.put("blank_01_02", "4");//
        params.put("blank_01_03", "3");//
        params.put("blank_01_04", "2");//
        params.put("blank_01_05", "1");//


        params.put("blank_02_01", "1.326");//
        params.put("blank_02_02", "2.534");//
        params.put("blank_02_03", "2524");//


        params.put("blank_03_01", "1.326");//
        params.put("blank_03_02", "2.534");//
        params.put("blank_03_03", "2524");//
        params.put("blank_03_04", "252");//
        params.put("blank_03_05", "2545");//
        params.put("blank_03_06", "1.326");//
        params.put("blank_03_07", "2.534");//
        params.put("blank_03_08", "2524");//
        params.put("blank_03_09", "252");//
        params.put("blank_03_10", "2545");//
        params.put("blank_03_11", "1.326");//
        params.put("blank_03_12", "2.534");//
        params.put("blank_03_13", "2524");//
        params.put("blank_03_14", "252");//
        params.put("blank_03_15", "2545");//
        params.put("blank_03_16", "1.326");//
        params.put("blank_03_17", "2.534");//
        params.put("blank_03_18", "2524");//
        params.put("blank_03_19", "252");//
        params.put("blank_03_20", "2545");//
        params.put("blank_03_21", "252");//
        params.put("blank_03_22", "2545");//

        params.put("blank_04_01", "1.326");//
        params.put("blank_04_02", "2.534");//
        params.put("blank_04_03", "2524");//
        params.put("blank_04_04", "252");//
        params.put("blank_04_05", "2545");//
        params.put("blank_04_06", "1.326");//
        params.put("blank_04_07", "2.534");//
        params.put("blank_04_08", "2524");//
        params.put("blank_04_09", "252");//
        params.put("blank_04_10", "2545");//
        params.put("blank_04_11", "1.326");//
        params.put("blank_04_12", "2.534");//
        params.put("blank_04_13", "2524");//
        params.put("blank_04_14", "252");//
        params.put("blank_04_15", "2545");//
        params.put("blank_04_16", "1.326");//
        params.put("blank_04_17", "2.534");//
        params.put("blank_04_18", "2524");//
        params.put("blank_04_19", "252");//
        params.put("blank_04_20", "2545");//
        params.put("blank_04_21", "252");//
        params.put("blank_04_22", "2545");//

        params.put("blank_05_01", "1.326");//
        params.put("blank_05_02", "2.534");//
        params.put("blank_05_03", "2524");//
        params.put("blank_05_04", "252");//
        params.put("blank_05_05", "2545");//
        params.put("blank_05_06", "1.326");//
        params.put("blank_05_07", "2.534");//
        params.put("blank_05_08", "2524");//
        params.put("blank_05_09", "252");//
        params.put("blank_05_10", "2545");//
        params.put("blank_05_11", "1.326");//
        params.put("blank_05_12", "2.534");//
        params.put("blank_05_13", "2524");//
        params.put("blank_05_14", "252");//
        params.put("blank_05_15", "2545");//
        params.put("blank_05_16", "1.326");//
        params.put("blank_05_17", "2.534");//
        params.put("blank_05_18", "2524");//
        params.put("blank_05_19", "252");//
        params.put("blank_05_20", "2545");//
        params.put("blank_05_21", "252");//
        params.put("blank_05_22", "2545");//


        params.put("localPicture1", new PictureRenderData(566, 299, "/home/creams/桌面/A.png"));
        params.put("localPicture2", new PictureRenderData(566, 299, "/home/creams/桌面/A.png"));
        params.put("localPicture3", new PictureRenderData(566, 299, "/home/creams/桌面/A.png"));
        params.put("localPicture4", new PictureRenderData(566, 299, "/home/creams/桌面/A.png"));*/


        templateWrite2("/home/creams/桌面/太阳能实验模板.docx", params, "/home/creams/桌面/test1.docx");
    }
}
