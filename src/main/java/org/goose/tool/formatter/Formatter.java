package org.goose.tool.formatter;

import org.goose.tool.common.CommonConst;
import org.goose.tool.common.StopWord;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 功能描述： 对获取到最优子树进行格式化输出。
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/6/27 9:21
 */
public class Formatter {


    private  int formatStopWordThreshod = 3;

    /**
     * 文档正文格式化输出功能模块。步骤如下：
     * 1.得到文章的根节点
     * 2.删除根节点下面的负分节点
     * 3.去TAG留内容
     * 4.替换BR节点
     * 5.获取根节点中子节点文本内容的集合。
     * 6.返回文章内容。
     *
     * @param node 最优节点子树。
     * @return 文章内容
     */
    public String get_formatted_text(Element node) throws IOException {
        //删除根节点下面的负分节点
        node = remove_negativescores_nodes(node);
        // 删除stopWord词少的节点
        node = remove_fewwords_paragraphs(node);
        // 去TAG留内容
        node = links_to_text(node);
        // 替换BR节点
        node = add_newline_to_br(node);
        // 获取根节点中子节点文本内容的集合。
        String article = convert_to_text(node);
        //返回文章内容。
        return article;
    }

    public Element remove_fewwords_paragraphs(Element node) throws IOException {

        Elements allElements = node.getAllElements();

        for (Element element : allElements) {


            String text = element.text();
            int i = StopWord.caculateStopWords(text);

            if (i < formatStopWordThreshod) {
                element.remove();
            }


            boolean highlink_density = StopWord.is_highlink_density(element);
            if (highlink_density) {
                if (element.parentNode() != null)
                element.remove();
            }

        }
        return node;
    }

    /**
     * 采用最简单的方式输出文章内容。
     *
     * @param node 优化筛选后的最优节点子树。
     * @return 文章内容
     */
    private String convert_to_text(Element node) {

        Elements allElements = node.getAllElements();
        StringBuilder stringBuilder = new StringBuilder();
        for (Element element : allElements) {

            if (!element.equals(node)) {

                stringBuilder.append(element.text()).append("\n");
            }

        }

        return stringBuilder.toString();
    }

    /**
     * 删除负分节点。
     *
     * @param node 最优子树
     * @return 最优子树
     */
    public Element remove_negativescores_nodes(Element node) {
        /***
         if there are elements inside our top node
         that have a negative gravity score,
         let's give em the boot
         **/
        Elements elements = node.select("[" + CommonConst.SCORE_ATTR + "]");
        for (Element element : elements) {
            String gravityScore = element.attr(CommonConst.SCORE_ATTR);
            Float sorce = Float.valueOf(gravityScore);
            if (sorce < 1) {
                element.remove();
            }
        }
        return node;
    }

    /**
     * 将最优子树中的 <br>, <i>, <strong>之类的节点信息直接去掉TAG保留信息。
     *
     * @param node 最优子树
     * @return 最优子树
     */
    private Element links_to_text(Element node) {
        /***
         replace common tags with just
         text so we don't have any crazy formatting issues
         so replace <br>, <i>, <strong>, etc....
         with whatever text is inside Jsoup
         code
         **/
        String[] tags = {"a", "b", "strong", "i", "br", "sup"};
        stripTags(node, tags);
        return node;
    }


    public Element add_newline_to_br(Element node) {
        Elements select = node.getElementsByTag("br");
        for (Element element : select) {
            element.append("\n");
            element.remove();

        }
        return node;
    }

    public void stripTags(Element node, String[] tags) {
        for (String tag : tags) {
            Elements elementsByTag = node.getElementsByTag(tag);
            for (Element element : elementsByTag) {
                if (element.parent() == null) {
                    continue;
                }
                String s = element.ownText();
                if (!StringUtil.isBlank(s)) {
                    element.after(s);
                }
                element.remove();
            }
        }
    }

    public int getFormatStopWordThreshod() {
        return formatStopWordThreshod;
    }

    public void setFormatStopWordThreshod(int formatStopWordThreshod) {
        this.formatStopWordThreshod = formatStopWordThreshod;
    }
}