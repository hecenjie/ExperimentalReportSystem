package com.nanwulife.controller;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Major;
import com.nanwulife.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @Project: ExperimentalReportSystem
 * @Description:
 * @Author: Cenjie
 * @Date: Created in 2018/9/13
 */

@Controller
@RequestMapping("/major")
public class MajorController {

    private static final Logger logger = LoggerFactory.getLogger(MajorController.class);

    /**
     * 获取
     * @return
     */
    //TODO: 正在开发
//    public ServerResponse<ArrayList<Major>> getMajors(){
//        return
//    }

}
