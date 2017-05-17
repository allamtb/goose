package org.goose.web.parse.service;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;
import org.goose.tool.common.StopWord;
import org.goose.web.parse.dao.OperateLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/7/13 15:21
 */
@Controller
@RequestMapping("/parse")
public class StopWordParseService {


    @Autowired
    private OperateLogDao operateLogDao;
    private int modeId =2;

    private Logger logger = LoggerFactory.getLogger(StopWordParseService.class);

    /**
     * 执行停止词计算。
     */
    @RequestMapping(value = "parseStopWord")
    @ResponseBody
    public void doParse(HttpServletResponse response, String content) throws IOException {

        logger.debug("开始计算停止词");
        String request =content;
        String result = "";
        List<String> stopwords = StopWord.getStopWords();
        //采用ansg来分词
        MyStaticValue.isRealName = true;
        List<Term> parse = ToAnalysis.parse(content);

        Set<String> stringSet = new HashSet<String>();

        for (Term term : parse) {
            String realName = term.getRealName();
            if (stopwords.contains(realName)) {

                if(!stringSet.contains(realName)) {
                    stringSet.add(realName);
                    result= content.replaceAll(realName, "<span class='stopWord'>" + realName + "</span>");
                }

            }
        }
        logger.debug("计算停止词结束");

        //计入操作日志
        operateLogDao.doLog(modeId, request, result);


        try {
            response.setContentType("type=text/html;charset=UTF-8");
            response.getWriter().write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
