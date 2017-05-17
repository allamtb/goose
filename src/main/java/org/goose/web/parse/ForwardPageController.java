package org.goose.web.parse;

import org.goose.web.parse.dao.OperateLogDao;
import org.goose.web.parse.vo.OptLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/7/8 16:06
 */

    @Controller
    @RequestMapping("/forward")
    public class ForwardPageController {

    @Autowired
    private OperateLogDao operateLogDao;

    /***
     *  跳转到正文解析
     * @return   ModelAndView
     */
    @RequestMapping(value = "parseMain")
    public ModelAndView forwardParseMain(){
        ModelAndView mav = new ModelAndView("parse/parseMain");

        return mav;
    }

    /***
     *  跳转到切词页面
     * @return   ModelAndView
     */
    @RequestMapping(value = "wordSplitMain")
    public ModelAndView forwardWordSplitMain(){
        ModelAndView mav = new ModelAndView("wordsplit/wordSplit");
        return mav;
    }


    /***
     *  跳转到停止词计算页面
     * @return   ModelAndView
     */
    @RequestMapping(value = "stopWordMain")
    public ModelAndView stopWordMain(){
        ModelAndView mav = new ModelAndView("stopword/parseStopWord");
        return mav;
    }


    /***
     *  跳转到停止词计算页面
     * @return   ModelAndView
     */
    @RequestMapping(value = "optLogList")
    public ModelAndView optLogList(){
        ModelAndView mav = new ModelAndView("optLog/optLogList");
        List<OptLog> optLogList = operateLogDao.LogList();
        mav.addObject("optLogList", optLogList);
        return mav;
    }
}
