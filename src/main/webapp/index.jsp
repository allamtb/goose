<%--* * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0 
 * Time:2015/7/2 18:26
 *
 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<!doctype html>
<html class="no-js">
<jsp:include page="WEB-INF/pages/common/head.jsp"/>
<body>


<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/"
                                                                        target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<jsp:include page="WEB-INF/pages/common/top.jsp"/>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="WEB-INF/pages/common/leftNav.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content" style="height:960px">

        <div class="am-cf am-padding" style="border-bottom:2px solid #0084c7">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> /
                <small>介绍</small>
            </div>
        </div>

        <article data-am-widget="paragraph" class="am-paragraph am-paragraph-default"
                 data-am-paragraph="{ tableScrollable: true, pureview: true }">

            <p style="font-size:large;text-indent: 4em">
                随着计算机网络的迅速发展,Internet已经成为一个巨大的信息资源库。在WEB海量信息的处理背景下，WEB智能信息检索、文档自动摘要、舆情分析等等需求应运而生。这些需求都是对互联网中海量的WEB页面的采集及分析的过程。通常情况下,这类技术都是通过网络爬虫来从网络上抓取原始网页的信息,而原始信息中除了用户所感兴趣的正文信息之外,还通常会包含有各种网络噪音数据,比如广告链接、标签信息、导航链接、评论等等。这些噪音数据的存在,极大地影响了网络检索的效率,也降低了人们的阅读效率。如何正确高效的在灵活多半、半结构化、异构性的HTML源文件中提取到文章正文，在基于互联网的数据挖掘、信息检索等领域具有着重要的意义。
            </p>

            <p style="font-size:large;text-indent: 4em">
                &nbsp&nbsp本文提出了一种基于停止词以及链接稠密度的父节点加权比值算法。该算法基于<strong style="color: #c10802">JSOUP</strong>
                对HTML的解析，并进行必要的降噪工作。通过对停止词分析、链接稠密度分析获得对信息节点进行二次信息节点筛选。
                然后对各个信息节点的进行<strong style="color: #1b961b">父节点加权比值</strong>。父节点加权比值是本系统采用的一个核心算法，
                其核心目的是找到包含全部正文节点最小DOM树。
                这个最小DOM树的根节点必然是各字节点的父节点，故称为“父节点”加权比值。加权的意思是权值相加，
                这里的权值是指各个子节点的分值。比值是指，在进行了全部节点迭代的加权后，系统会对所有节点的权值进行比较，
                选择权值最大的节点作为文章根节点。然后将文章根节点涵盖的所有正文信息提取出来。
                通过测试发现，父节点加权比值算法具有对各种HTML布局的新闻正文提取任务有 <strong>很高的精确度</strong>。
            </p>


            <p style="font-size:large;text-indent: 4em">With the advanced development of Internet technology and
                information, the quantity of webpage increases rapidly.Under this background,WEB intelligent
                information retrieval, automatic document summary, public opinion analysis come out. These technology
                basicly require the acquisition and analysis of massive Internet WEB page. Normally these technologies
                use web crawler to get original pages from the network information. Be these original pages they get
                not only contain information which the user interested with, but also often contain a variety of
                network noise data, such as advertising link, tag information, navigation links, comments and so on.
                These noisy data greatly affect the network retrieval of efficiency and reduce people's reading
                efficiency. How to correctly and efficiently extract the main article from the most flexible,
                semi-structured, heterogeneous HTML source file become more and more imprtant in Internet-based data
                mining, information retrieval, and other fields .
            </p>

            <p style="font-size:large;text-indent: 4em">This paper presents an approach named updating and comparering
                parent node weight algorithms based on stop words technology and links dense degree technology .Our
                system is based on <strong style="color: #c10802">JSOUP</strong> which is a excellent HTML parsing
                tool and base on the noise reduction
                process. When gain information node from stop words analysis and links dense degree analysis ,our
                system using updating and comparering parent node weight algorithms to caculate the weights of each
                node. Updating and<strong style="color: #1b961b"> comparering parent node weight algorithms </strong>whitch
                aim to to find the smallest node
                contains all the text of the DOM tree is the core algorithm of our system. The smallest node contains
                all the text of the DOM tree is called "parent" . The term weight means the score obtain from each son
                node.And updating weight means update parent score and comparering weight means after iterating all
                nodes, the parent node with maximum score will be the article root node. And then our system can
                exract all the text information from the article root node conveniently. Updating and comparering
                parent node weight algorithms has a <strong>high accuracy</strong> in various HTML content extracting
                test
                especially
                in news site extracting test .
            </p>

        </article>


    </div>
    <!-- content end -->

</div>


<jsp:include page="WEB-INF/pages/common/foot.jsp"/>

</body>
</html>
