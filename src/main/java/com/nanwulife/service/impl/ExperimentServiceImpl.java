package com.nanwulife.service.impl;

import com.nanwulife.common.Const;
import com.nanwulife.common.ServerResponse;
import com.nanwulife.dao.ExperimentMapper;
import com.nanwulife.pojo.Experiment;
import com.nanwulife.service.IExperimentService;
import com.nanwulife.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 实验Service层
 * @Author: Cenjie
 * @Date: Created in 2018/9/14
 */
@Service("iExperimentService")
public class ExperimentServiceImpl implements IExperimentService {

    private Logger logger = LoggerFactory.getLogger(ExperimentServiceImpl.class);

    @Autowired
    ExperimentMapper experimentMapper;

    /**
     * 开放实验
     * @param expId
     * @return
     */
    @Override
    public ServerResponse openExp(Integer expId){
        Experiment experiment = new Experiment();
        experiment.setId(expId);
        experiment.setOpen(Const.Exp.OPEN);
        int resultCount = experimentMapper.updateByPrimaryKeySelective(experiment);
        if(resultCount == 0)
        {return ServerResponse.createByError();}
        return ServerResponse.createBySuccess();
    }

    /**
     * 关闭实验
     * @param expId
     * @return
     */
    @Override
    public ServerResponse closeExp(Integer expId){
        Experiment experiment = new Experiment();
        experiment.setId(expId);
        experiment.setOpen(Const.Exp.CLOSE);
        int resultCount = experimentMapper.updateByPrimaryKeySelective(experiment);
        if(resultCount == 0)
        {return ServerResponse.createByError();}
        return ServerResponse.createBySuccess();
    }



		/**
     * 获取实验开放状态
     * @param expId
     * @return
     */
    @Override
    public ServerResponse getExpStatus(Integer expId){
        Experiment experiment = experimentMapper.selectByPrimaryKey(expId);
        if(experiment == null){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_NOT_EXITS.getCode(), Const.ResponseCode.EXP_NOT_EXITS.getDesc());
        }
        if(experiment.getOpen() == Const.Exp.OPEN){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_OPEN.getCode(), Const.ResponseCode.EXP_OPEN.getDesc());
        } else if(experiment.getOpen() == Const.Exp.CLOSE){
            return ServerResponse.createByErrorCodeMessage(Const.ResponseCode.EXP_CLOSE.getCode(), Const.ResponseCode.EXP_CLOSE.getDesc());
        } else{
            return ServerResponse.createByError();
        }
    }

    /**
     * 上传图表
     * @param expId
     * @param stuNum
     * @param image
     * @return
     */
    @Override
    public ServerResponse uploadChart(Integer expId, Long stuNum, String image, Integer index) {
        String basePath;
        String chartPath;
        String path;
        if(System.getProperty("os.name").toLowerCase().contains("linux")){
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.linux.basePath");
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")){
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.macos.basePath");
        } else{
            basePath = new PropertiesUtil("server.properties").readProperty("report.server.win.basePath");
        }
        chartPath = new PropertiesUtil("server.properties").readProperty("report.chart.server.path");
        path = basePath + chartPath + stuNum;
        logger.info(path);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        if(System.getProperty("os.name").toLowerCase().contains("linux") || System.getProperty("os.name").toLowerCase().contains("mac")){
            try {
                Runtime.getRuntime().exec("chmod 777 " + path);
            } catch(Exception ex){
                logger.info("文件权限变更出现异常!");
            }
        }

        try {
//            logger.info(image);
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] b = decoder.decodeBuffer(image);
//            ByteArrayInputStream bais = new ByteArrayInputStream(b);
//            BufferedImage bi = ImageIO.read(bais);
//            File w2 = new File(path, expId.toString()+".png");
//            logger.info(w2.getPath());
//            ImageIO.write(bi, "png", w2);
//            logger.info("上传文件成功");
//            bais.close();

            //Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(image);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = path + "/" + expId.toString() + "-" + index +  ".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return ServerResponse.createBySuccess();
        } catch (IOException ex){
            System.out.println(ex.toString());
            logger.info("上传文件异常: " + ex);
            return ServerResponse.createByErrorMessage(ex.toString());
        }
    }
    
    public ServerResponse pushModel(Map<String, Object> params, String a){
        return null;
    }

    /**
     * 获取所有实验列表
     * @return
     */
    @Override
    public List<Experiment> getAllExps(){
        List<Experiment> exps = experimentMapper.selectAllExps();
        return exps;
    }


}