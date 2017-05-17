package org.goose.tool;

import org.goose.tool.cleaner.Cleaner;
import org.goose.tool.common.JsoupExtender;
import org.goose.tool.formatter.Formatter;
import org.goose.tool.selector.Selector;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/6/28 20:40
 */
public class App {


    public static void main(String[] args) throws IOException {
        //设置代理
        System.setProperty("http.proxyHost", "172.17.18.80");
        System.setProperty("http.proxyPort", "8080");
        //url路径
        String urlLocation = "http://gb.cri.cn/42071/2015/07/07/5311s5022627.htm";
        String formatted_text = getText(urlLocation);
        System.out.println(formatted_text);
    }

    private static String getText(String urlLocation) throws IOException {
        Document original = JsoupExtender.getDocumentFromUrl(urlLocation);
        Cleaner cleaner = new Cleaner();
        Document cleanedDocument = cleaner.clean(original);
        Selector selector = new Selector();
        Element bestNode = selector.calculate_best_node(cleanedDocument);
        if(bestNode==null) return "无法定位最优子树,网页源代码为：\n" + original.html();
        Formatter format = new Formatter();
        return format.get_formatted_text(bestNode);
    }

}
