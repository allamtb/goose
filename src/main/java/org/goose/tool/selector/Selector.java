package org.goose.tool.selector;

import org.goose.tool.common.CommonConst;
import org.goose.tool.common.JsoupExtender;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.goose.tool.common.StopWord.caculateStopWords;
import static org.goose.tool.common.StopWord.is_highlink_density;

/**
 * 功能描述：最优子树选择器。
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/4/19 9:45
 */
public class Selector {


        JsoupExtender jsoupExtender = new JsoupExtender();

        /**
         * 通过停止词分析、分词技术、链接稠密度分析、加权求值等分析判断
         * 计算出最优父节点。
         *
         * @param document
         * @return
         */
        public Element calculate_best_node(Document document) throws IOException {
                /*所有需要检索的节点 p pre td*/
                Elements nodes_to_check = getAllNodesToCheck(document);
                Elements parentNodes = calculateScoreAndUpdateParent(nodes_to_check);
                //判断分值最高的父节点。
                return getBestScoreNode(parentNodes);
        }

        private Elements getAllNodesToCheck(Document document) {
                /*
                returns a list of nodes we want to search
                on like paragraphs and tables*/
                Elements nodes_to_check = new Elements();
                String[] tags = {"p", "pre", "td"};
                for (String tag : tags) {

                        Elements elementsByTag = document.getElementsByTag(tag);
                        nodes_to_check.addAll(elementsByTag);
                }

                return nodes_to_check;

        }

        private Element getBestScoreNode(Elements parentNodes) {

                Element top_node =null;
                for (Element parentNode : parentNodes) {

                        if (top_node == null) {
                                top_node = parentNode;
                                continue;
                        }

                        float score = getScore(parentNode);
                        float currentTopScore = getScore(top_node);
                        if (score > currentTopScore) {
                                top_node = parentNode;
                        }
                }
                return top_node;
        }


        private float getScore(Element parentNode) {

                String score = parentNode.attr(CommonConst.SCORE_ATTR);

                return Float.parseFloat(score);
        }

        private Elements calculateScoreAndUpdateParent(Elements nodes_to_check) throws
                IOException {
                Elements parentNodes = new Elements();
                /*偏移分数初始值*/
                float starting_boost = (float) 1.0;
                int cnt = 0;
                int i = 0;
                /*通过停止词 以及 链接稠密度 来判断节点是否是含有文本的节点。*/
                Elements nodes_with_text = new Elements();
                /*循环遍历需要检测的信息节点。并对信息节点进行评分。然后对该节点的父节点以及祖父节点加权。*/
                for (Element element : nodes_to_check) {

                        //获取元素其中的文本信息。
                        String innerText = element.ownText();
                        //获取文本中停止词的个数
                        int stopWordCount = caculateStopWords(innerText);
                        //判断元素是否连接稠密度过高
                        boolean high_link_density = is_highlink_density(element);
                        //如果停止词数量大于2，且不是高链接节点。
                        if (stopWordCount > 2 && !high_link_density) {

                                nodes_with_text.add(element);
                        }

                        for (Element nodeWithText : nodes_with_text) {

                                float boost_score = (float) 0.0;
                                //如果textNode节点还有前驱兄弟节点也是信息节点。
                                //那么该textNode是文章正文的可能性较高。应该加分。
                                if (is_boostable(nodeWithText)) {
                                        if (cnt >= 0) {
                                                boost_score = (float) ((1.0 / starting_boost) * 50);
                                                starting_boost += 1;
                                        }

                                }

                                int stopWords = caculateStopWords(nodeWithText.text());
                                float upscore = boost_score + stopWords;
                                //查找父节点
                                Element parent = nodeWithText.parent();
                                //父节点加权
                                updateScore(parent, upscore);

                                if (!parentNodes.contains(parent)) {
                                        parentNodes.add(parent);
                                }

                                //同时对其爷爷节点也更新同样的分值。
                                // 这样做的目的，是防止漏掉分布在某一个小子树中的文章。

                                Element grandFather = parent.parent();

                                if (grandFather != null) {
                                        updateScore(grandFather, upscore);
                                }

                                parentNodes.add(grandFather);

                                cnt += 1;
                                i += 1;
                        }

                }
                return parentNodes;
        }


        /**
         * *
         * <p/>
         * alot of times the first paragraph might be the caption under an image
         * so we'll want to make sure if we're going to boost a parent node that
         * it should be connected to other paragraphs,
         * at least for the first n paragraphs so we'll want to make sure that
         * the next sibling is a paragraph and has at
         * least some substatial weight to it
         * @return
         */
        public boolean is_boostable(Element textNode) throws IOException {

                String para = "p";
                int steps_away = 0;
                int minimum_stopword_count = 5;
                int max_stepsaway_from_node = 3;

                Elements previousSiblings = jsoupExtender.getPreviousSiblings(textNode);

                for (Element previousSibling : previousSiblings) {
                        if (!para.equals(previousSibling.tag().getName())) continue;
                        if (steps_away > max_stepsaway_from_node) return false;
                        String text = previousSibling.html();
                        int count = caculateStopWords(text);
                        if(count>minimum_stopword_count) return true;
                        steps_away++;
                }

                return false;

        }





        private void updateScore(Element parent, float upscore) {

                String score = parent.attr(CommonConst.SCORE_ATTR);

                if(score.isEmpty())
                {

                        parent.attr(CommonConst.SCORE_ATTR, String.valueOf(upscore));
                }

                else {
                        float addedScore = Float.parseFloat(score) + upscore;
                        parent.attr(CommonConst.SCORE_ATTR, String.valueOf(addedScore));
                }


        }


}
