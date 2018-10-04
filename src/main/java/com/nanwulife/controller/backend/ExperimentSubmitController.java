package com.nanwulife.controller.backend;

import com.deepoove.poi.data.PictureRenderData;
import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.experimentRank.PhotoeletricExperiment;
import com.nanwulife.experimentRank.SolarEnergyExperiment;
import com.nanwulife.pojo.Score;
import com.nanwulife.pojo.User;
import com.nanwulife.service.IExperimentService;
import com.nanwulife.service.IScoreService;
import com.nanwulife.service.IUserService;
import com.nanwulife.util.PropertiesUtil;
import com.nanwulife.util.WordToNewWordUtil;
import net.sf.jsqlparser.schema.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 实验提交并导出word模块
 * @Author: Creams
 * @Date: Created in 2018/9/13
 */

@Controller
@RequestMapping("/sub")
public class ExperimentSubmitController {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentManageController.class);

    @Autowired
    IExperimentService iExperimentService;

    @Autowired
    IScoreService iScoreService;
    
    @Autowired
    IUserService iUserService;

    
    /**
     * 提交光电实验答案
     * @param selectval
     * @return
     */
    //TODO:完成
    @RequestMapping(value = "Exp_01.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse submitExp(@RequestParam(value = "selectval[]") String[] selectval, HttpSession session, @RequestParam(value = "result[]") String[] result,
                                    @RequestParam(value = "chart1[]") String[] chart1, @RequestParam(value = "table2[]") String[] table2,
                                    @RequestParam(value = "table3[]") String[] table3, @RequestParam(value = "table4[]") String[] table4){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        int rank = 0;
        int stu_class = iUserService.queryMajornameAndClassByNum(user.getStuNum()).getStuClass();
        String major_name = iUserService.queryMajornameAndClassByNum(user.getStuNum()).getMajorName();
        String chartPath = new PropertiesUtil("server.properties").readProperty("report.chart.server.path");
        String wordPath = new PropertiesUtil("server.properties").readProperty("report.word.server.path");
        String basePath;
        String path;
        Map<String, Object> params = new HashMap<String, Object>();
        ServerResponse serverResponse = iScoreService.isStuHaveScore(1, user.getId());
        if (serverResponse.getStatus() == 14){
            //报告重复提交
            return serverResponse;
        }
        else{
            serverResponse = null;
        }
        //=============================模板标记==============================
        for (int i = 0; i < 11; i++) {
            if (i + 1 <= 9)
                params.put("choice_" + "0" + (i+1) + "", selectval[i]);
            else
                params.put("choice_" + (i+1), selectval[i]);
        }

        for (int i = 0; i < 3; i++) {
            params.put("blank_02_0" + (i+1) + "", result[i]);
        }
        
        for (int i = 0; i < 5; i++) {
            params.put("blank_01_0" + (i+1) + "", chart1[i]);
        }

        for (int i = 0; i < 22; i++) {
            if (i + 1 <= 9)
                params.put("blank_03_" + "0" + (i+1) + "", table2[i]);
            else
                params.put("blank_03_" + (i+1), table2[i]);
        }

        for (int i = 0; i < 22; i++) {
            if (i + 1 <= 9)
                params.put("blank_04_" + "0" + (i+1) + "", table3[i]);
            else
                params.put("blank_04_" + (i+1), table3[i]);
        }

        for (int i = 0; i < 22; i++) {
            if (i + 1 <= 9)
                params.put("blank_05_" + "0" + (i+1) + "", table4[i]);
            else
                params.put("blank_05_" + (i+1), table4[i]);
        }
        
        if(System.getProperty("os.name").toLowerCase().contains("linux")){
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.linux.basePath");
        } else {
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.win.basePath");
        }
        
        params.put("localPicture1", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/1-1.png"));
        params.put("localPicture2", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/1-2.png"));


        rank = new PhotoeletricExperiment(selectval[0], selectval[1], selectval[2], selectval[3], selectval[4], selectval[5],
                selectval[6], selectval[7], selectval[8], selectval[9], selectval[10], Double.parseDouble(result[2]), Double.parseDouble(table2[21]), Double.parseDouble(table3[21]), Double.parseDouble(table4[21])).getRank();
        
        
        params.put("stunum", user.getStuNum());
        params.put("score", String.valueOf(rank));
        //=====================================================================
        
        path = basePath + wordPath + major_name + stu_class + "/" + user.getStuNum() + "/";
        File filedir = new File(path);
        if(!filedir.exists()){
            filedir.setWritable(true);
            filedir.mkdirs();
        }
        try {
            WordToNewWordUtil.templateWrite2(basePath + "光电效应实验模板.docx", params,  path + "1.docx");
        } catch (Exception e) {
            System.out.println("写入模板异常");
            e.printStackTrace();
        }

        Score score = new Score();
        score.setStuId(user.getId());
        score.setExpId(1);
        score.setScore(rank);
        user = null;
        return iScoreService.submit(score);
    }

    @RequestMapping(value = "Exp_02.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse submitExp(HttpSession session, @RequestParam(value = "selectval[]") String[] selectval, 
                                    @RequestParam(value = "table1[]") String[] table1, @RequestParam(value = "table2[]") String[] table2,
                                    @RequestParam(value = "table3[]") String[] table3, @RequestParam(value = "table4[]") String[] table4,
                                    @RequestParam(value = "result[]") String[] result){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.NEED_LOGIN.getCode(), Const.ResponseCode.NEED_LOGIN.getDesc());
        }
        int rank = 0;
        int stu_class = iUserService.queryMajornameAndClassByNum(user.getStuNum()).getStuClass();
        String major_name = iUserService.queryMajornameAndClassByNum(user.getStuNum()).getMajorName();
        String chartPath = new PropertiesUtil("server.properties").readProperty("report.chart.server.path");
        String wordPath = new PropertiesUtil("server.properties").readProperty("report.word.server.path");
        String basePath;
        String path;
        Map<String, Object> params = new HashMap<String, Object>();
        ServerResponse serverResponse = iScoreService.isStuHaveScore(2, user.getId());
        if (serverResponse.getStatus() == 14){
            //报告重复提交
            return serverResponse;
        }
        else{
            serverResponse = null;
        }

        if(System.getProperty("os.name").toLowerCase().contains("linux")){
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.linux.basePath");
        } else {
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.win.basePath");
        }
        
        //=============================模板标记==============================
        for (int i = 0; i < 10; i++) {
            params.put("choice_" + (i+1) + "", selectval[i]);
        }
        for (int i = 0; i < 42; i++) {
            params.put("table1_" + (i+1) + "", table1[i]);
        }

        for (int i = 0; i < 54; i++) {
            params.put("table2_" + (i+1) + "", table2[i]);
        }

        for (int i = 0; i < 42; i++) {
            params.put("table3_" + (i+1) + "", table3[i]);
        }
        
        for (int i = 0; i < 54; i++) {
            params.put("table4_" + (i+1) + "", table4[i]);
        }
        
        for (int i = 0; i < 8; i++) {
            params.put("result_" + (i+1) + "", result[i]);
        }

        params.put("pic1", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/2-1.png"));
        params.put("pic2", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/2-2.png"));
        params.put("pic3", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/2-3.png"));
        params.put("pic4", new PictureRenderData(625, 326, basePath + chartPath + user.getStuNum() + "/2-4.png"));
        //=============================模板标记==============================
        rank = new SolarEnergyExperiment(selectval[0], selectval[1], selectval[2], selectval[3], selectval[4], selectval[5], selectval[6], 
                selectval[7], selectval[8], selectval[9], Double.parseDouble(result[3]), Double.parseDouble(result[7])).getRank();

        params.put("name", user.getStuName());
        params.put("num", user.getStuNum());
        params.put("classno", major_name + user.getStuClass());
        params.put("score", rank);
        
        path = basePath + wordPath + major_name + stu_class + "/" + user.getStuNum() + "/";
        File filedir = new File(path);
        if(!filedir.exists()){
            filedir.setWritable(true);
            filedir.mkdirs();
        }
        try {
            WordToNewWordUtil.templateWrite2(basePath + "太阳能实验模板.docx", params,  path + "2.docx");
        } catch (Exception e) {
            System.out.println("写入模板异常");
            e.printStackTrace();
        }

        
        Score score = new Score();
        score.setStuId(user.getId());
        score.setExpId(2);
        score.setScore(rank);
        user = null;
        return iScoreService.submit(score);

    }
}
