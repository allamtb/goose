package org.goose.tool.cleaner;

import org.goose.tool.common.JsoupExtender;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/4/11 17:12
 */
public class Cleaner {
        /*Jsoup辅助扩展*/
        private JsoupExtender jsoupExtender = new JsoupExtender();
        public Document clean(Document original) {
                Document clone = original.clone();
                cleanBodyClasses(clone);
                cleanArticleTags(clone);
                //删除SEO 隐藏的部分
                removeHides(clone);
                removeDropCaps(clone);
                removeScriptsAndStyles(clone);
                removeBadNodes(clone);
                singleTagToPara(clone, "div");
                singleTagToPara(clone, "span");
                cleanSpanInP(clone);
                return clone;
        }

        private void removeHides(Document clone) {
                clone.select("*[style*=display:none]").remove();
        }


        public Document cleanBodyClasses(Document original) {
                Element body = original.body();
                jsoupExtender.removeAllAttributes(body);
                return original;
        }

        public Document cleanArticleTags(Document original) {

                Elements articles = original.getElementsByTag("article");

                for (Element article : articles) {
                        jsoupExtender.removeAllAttributes(article);
                }
                return original;
        }

        public Document removeDropCaps(Document original) {

                Elements select1 = original.select("span[class~=dropcap]");
                Elements select2 = original.select("span[class~=drop_cap]");
                select1.addAll(select2);

                for (Element element : select1) {
                        element.remove();
                }
                return original;

        }


        public Document removeScriptsAndStyles(Document original) {
                jsoupExtender.removeAllTag(original, "script", "style");
                return original;
        }

        public Document removeBadNodes(Document original) {

                String css = "^side$|combx|retweet|mediaarticlerelated|menucontainer|" +
                        "navbar|storytopbar-bucket|utility-bar|inline-share-tools" +
                        "|comment|PopularQuestions|contact|foot|footer|Footer|footnote" +
                        "|cnn_strycaptiontxt|cnn_html_slideshow|cnn_strylftcntnt" +
                        "|^links$|meta$|shoutbox|sponsor" +
                        "|tags|socialnetworking|socialNetworking|cnnStryHghLght" +
                        "|cnn_stryspcvbx|^inset$|pagetools|post-attributes" +
                        "|welcome_form|contentTools2|the_answers" +
                        "|communitypromo|runaroundLeft|subscribe|vcard|articleheadings" +
                        "|date|^print$|popup|author-dropdown|tools|socialtools|byline" +
                        "|konafilter|KonaFilter|breadcrumbs|^fn$|wp-caption-text" +
                        "|legende|ajoutVideo|timestamp|js_replies";

                Elements select = jsoupExtender.getElementsByCss(original, css);

                for (Element element : select) {
                        if(element.parent()!=null)
                        element.remove();
                }
                return original;
        }


        public Document cleanSpanInP(Document original) {

                Elements select = original.select("p > span");
                for (Element element : select) {
                        element.remove();
                }
                return original;

        }


        public Document singleTagToPara(Document original, String tagStr) {

                Elements elements = original.getElementsByTag(tagStr);

                String tags[] = new String[]{"a", "blockquote", "dl", "div", "img", "ol", "p", "pre", "table", "ul"};
                for (Element element : elements) {
                        boolean isBadDiv = true;
                        for (String tag : tags) {
                                Elements elementsByTag = element.getElementsByTag(tag);
                                if (elementsByTag.size() > 0) {
                                        isBadDiv = false;
                                }
                        }

                        if (isBadDiv) {
                                String s = element.ownText();
                                //新建P节点。目的是让后续的文本方便处理。
                                Element p = new Element(Tag.valueOf("p"), s);
                                p.html(s);
                                element.replaceWith(p);
                        }

                }
                return original;
        }


}
