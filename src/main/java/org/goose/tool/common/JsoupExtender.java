package org.goose.tool.common;

import org.apache.commons.configuration.ConfigurationUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/4/11 19:50
 */
public class JsoupExtender {
    /***
     * 通过HTML属性来获取元素组。
     * @param original
     * @param css
     * @return
     */
       public Elements getElementsByCss(Document original, String css) {
                Elements resultElements = original.select("[id~=" + css + "]");
                Elements select1 = original.select("[class~=" + css + "]");
                Elements select2 = original.select("[name~=" + css + "]");
                resultElements.addAll(select1);
                resultElements.addAll(select2);
                return resultElements;
        }

    /***
     * 删除原始文档中给定的TAG
     * @param original
     * @param tagNames
     */
        public void removeAllTag(Document original, String... tagNames) {
                for (String tagName : tagNames) {
                        Elements script = original.getElementsByTag(tagName);
                        for (Element element : script) {
                                element.remove();
                        }
                }
        }

    /***
     * 删除当前元素的所有属性
     * @param body
     */
        public void removeAllAttributes(Element body) {
                Attributes attributes = body.attributes();
                for (Attribute attribute : attributes) {
                        body.removeAttr(attribute.getKey());
                }
        }

    /****
     * 指定文件路径地址，得到该HTML文件的DOCUMENT。
     * @param str
     * @return
     * @throws java.io.IOException
     */
         public static Document getDocumentFromFileStr(String str) throws IOException {
                URL locate = ConfigurationUtils.locate(str);
                File file = ConfigurationUtils.fileFromURL(locate);
                return Jsoup.parse(file, "UTF-8");

        }

    /***
     * 指定URL地址，得到该URL地址对应的HTML DOCUMENT
     * @param urlLocation
     * @return
     * @throws java.io.IOException
     */
        public static Document getDocumentFromUrl(String urlLocation) throws IOException {

                return Jsoup.connect(urlLocation).get();
        }

    /***
     * 取得当前元素所有的前置兄弟节点。
     * @param textNode
     * @return
     */
        public Elements getPreviousSiblings(Element textNode) {
                Elements previousSiblings = new Elements();
                Element element = textNode.previousElementSibling();
                while (element!=null) {
                        previousSiblings.add(element);
                        element= element.previousElementSibling();
                }

                return previousSiblings;
        }

}
