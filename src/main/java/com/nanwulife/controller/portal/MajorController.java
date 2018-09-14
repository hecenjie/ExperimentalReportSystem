package com.nanwulife.controller.portal;

import com.nanwulife.common.ServerResponse;
import com.nanwulife.pojo.Major;
import com.nanwulife.service.IMajorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    IMajorService iMajorService;

    /**
     * 获取所有专业信息列表
     * @return
     */
    @RequestMapping(value = "get_all_majors.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<List<Major>> getAllMajors(){
        List<Major> majors = iMajorService.getAllMajors();
        return ServerResponse.createBySuccess(majors);
    }

}
