package org.goose.tool.common;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;
import org.apache.commons.configuration.ConfigurationUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/6/28 20:00
 */
public class StopWord {

    private static List<String> stopwords;


    public static int caculateStopWords(String text) throws IOException {
        int count = 0;
        if (stopwords == null) {
            stopwords= getStopWords();
        }
        //采用ansg来分词
        MyStaticValue.isRealName = true;
        List<Term> parse = ToAnalysis.parse(text);
        for (Term term : parse) {
            String realName = term.getRealName();
            if (stopwords.contains(realName)) {
                count++;
            }
        }

        return count;
    }

    public static List<String> getStopWords() throws IOException {
        URL locate = ConfigurationUtils.locate("stopwords/stopwords-zhnew.txt");
        File file = ConfigurationUtils.fileFromURL(locate);
        //将file中的字转为list
        return Files.readLines(file, Charsets.UTF_8);
    }


    /**
     * 设当前节点中链接数为a 文本段数为b 若a/b>=1 则表示链接数过多。
     * 针对英文文本段落，我们取链接中的单词数为m, 总的单词数为n 链接稠密度的算法为 m/n*a。
     * 中文并没有单词的概念，就是说中文的词汇之间并非像英文一样以空格隔开。故在链接中的单词数其实就是指的中文的段落数。
     * 当计算总的单词数的时候，会将所有单词拼接起来，所有的汉字就拼接成了一个段落。
     *
     * @param element
     * @return
     */
    public static boolean is_highlink_density(Element element) {
        Elements links = element.getElementsByTag("a");
        if (links.size() == 0) {
            return false;
        }
        String text = element.text();
        String[] words = text.split(" ");
        int wordsNumber = words.length;

        String textInLinks = getTextInLinks(links);
        String[] linkWords = textInLinks.split(" ");
        int linkWordsNumber = linkWords.length;
        int linksNumber = links.size();
        float i = ((float) linkWordsNumber / (float) wordsNumber) * (float) linksNumber;
        return i >= 1.0;

    }

    private static String getTextInLinks(Elements links) {
        StringBuilder sb = new StringBuilder();
        for (Element link : links) {
            String text = link.text();
            sb.append(text);
        }

        return sb.toString();
    }
}
