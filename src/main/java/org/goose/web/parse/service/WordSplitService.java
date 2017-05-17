package org.goose.web.parse.service;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;
import org.goose.web.parse.dao.OperateLogDao;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/7/13 15:21
 */
@Controller
@RequestMapping("/split")
public class WordSplitService {


    private int modeId =3;

    @Autowired
    private OperateLogDao operateLogDao;

    /**
     * 执行切词。
     */
    @RequestMapping(value = "doParse")
    @ResponseBody
    public void doParse(HttpServletResponse response, String content) throws IOException {
        //采用ansg来分词
        MyStaticValue.isRealName = true;
        List<Term> parse = ToAnalysis.parse(content);
        StringBuilder stringBuilder = new StringBuilder();
        for (Term term : parse) {
            String realName = term.getRealName();
            if(!StringUtil.isBlank(realName)) {
                stringBuilder.append(realName).append("/");
            }
        }
         //计入操作日志
        operateLogDao.doLog(modeId, content, stringBuilder.toString());


        try {
            response.setContentType("type=text/html;charset=UTF-8");
            response.getWriter().write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
