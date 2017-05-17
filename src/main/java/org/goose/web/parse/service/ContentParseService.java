package org.goose.web.parse.service;

import org.goose.tool.cleaner.Cleaner;
import org.goose.tool.common.JsoupExtender;
import org.goose.tool.formatter.Formatter;
import org.goose.tool.selector.Selector;
import org.goose.web.parse.dao.ConfigDao;
import org.goose.web.parse.dao.OperateLogDao;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/7/8 17:01
 */

@Controller
@RequestMapping("/parse")
public class ContentParseService {
    @Autowired
    private OperateLogDao operateLogDao;
    @Autowired
    private ConfigDao configDao;
    private int modeId =1;
    private Logger logger = LoggerFactory.getLogger(ContentParseService.class);
    private Map<String,String> cache = new HashMap<String,String> ();

    /**
     * 执行正文解析
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "doParse")
    @ResponseBody
    public void doParse(HttpServletResponse response, String url) throws IOException {


        String proxy = configDao.getProxy();

        String[] proxys = proxy.split("\\:");
        if(proxys.length>1) {
            //设置代理
            System.setProperty("http.proxyHost", proxys[0]);
            System.setProperty("http.proxyPort", proxys[1]);
        }

        //url路径
        logger.debug("开始解析--------");
        String formatted_text;
        if(cache.get(url)==null) {
            formatted_text= getText(url);
            cache.put(url, formatted_text);
        }else {
            formatted_text = cache.get(url);
        }
        formatted_text=formatted_text.replaceAll("\n", "<br/>");
        operateLogDao.doLog(modeId,url,formatted_text);

        try {
            response.setContentType("type=text/html;charset=UTF-8");
            response.getWriter().write(formatted_text);
        } catch (Exception e) {
            logger.error("repose出错",e);
        }
    }

    private String getText(String urlLocation) throws IOException {
        Document original = JsoupExtender.getDocumentFromUrl(urlLocation);
        Cleaner cleaner = new Cleaner();
        Document cleanedDocument = cleaner.clean(original);
        Selector selector = new Selector();
        Element bestNode = selector.calculate_best_node(cleanedDocument);
        if (bestNode == null) return "无法定位最优子树,网页源代码为：\n" + original.html();
        Formatter format = new Formatter();
        String stopWordThreshod = configDao.getStopWordThreshod();
        format.setFormatStopWordThreshod(Integer.parseInt(stopWordThreshod));
        return format.get_formatted_text(bestNode);
    }
}
