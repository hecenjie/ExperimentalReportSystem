package com.nanwulife.service.impl;

import com.nanwulife.dao.MajorMapper;
import com.nanwulife.pojo.Major;
import com.nanwulife.service.IMajorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: ExperimentalReportSystem
 * @Description: 专业信息Service层实现
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */

@Service("iMajorService")
public class MajorServiceImpl implements IMajorService {

    private Logger logger = LoggerFactory.getLogger(MajorServiceImpl.class);

    @Autowired
    MajorMapper majorMapper;

    /**
     * 获取所有专业列表
     * @return
     */
    public List<Major> getAllMajors(){
        List<Major> majors = majorMapper.selectAllMajors();
        return majors;
    }
}
