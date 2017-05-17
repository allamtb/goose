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
<jsp:include page="../common/head.jsp"/>


<script type="text/javascript">

    $(function () {
        $("#wordsplitCommit").click(function () {
            var $btn = $(this);
            var $content = $("#url").val();


            if ($content.length == 0) {
                alert("请输入文本段落");
                return;
            }

            $.ajax(
                    {
                        type: 'POST',
                        beforeSend: function () {
                            $("#result").html("");
                            $btn.button('loading');
                        },
                        url: "<c:url value="/split/doParse"/>",
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            alert(XMLHttpRequest.status);
                            alert(XMLHttpRequest.readyState);
                            alert(XMLHttpRequest.responseText);
                            alert(textStatus);
                        },
                        success: function (html) {
                            $("#result").html(html);
                            console.log(html);
                        },
                        complete: function () {
                            $btn.button('reset');
                        },
                        data: {
                            content: $content
                        },
                        dataType: "text",
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
                    }
            )
        });


    });


</script>

<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/"
                                                                        target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->
<jsp:include page="../common/top.jsp"/>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    <jsp:include page="../common/leftNav.jsp"/>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">

        <div class="am-cf am-padding" style="border-bottom:2px solid #0084c7">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">功能</strong> /
                <small>停止词计算</small>
            </div>
        </div>

        <div class="am-cf am-padding" style="width: 80%;margin: 0 auto; border:1px solid #44b549;border-bottom:
        none; border-top: none">
            <p><strong style="color: coral">切词工具</strong> <br/>
                本系统采用<strong>中国自然语言开源组织</strong>提供的切词工具ANSJ进行正文内容的提取。 <br/>
                ANSJ一个基于google语义模型+条件随机场模型的中文分词的java实现。   <br/>
                分词速度达到每秒钟大约200万字左右（mac air下测试），准确率能达到96%以上。<br/>
            </p>
        </div>


        <div class="am-g" style="border-top:2px solid #0084c7;width: 80%;">

            <div style="margin-top: 10px">
                <form class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="url" class="am-u-sm-3 am-form-label">待切词的文本段落</label>

                        <div class="am-u-sm-9">
                            <textarea style="min-height:180px" id="url" placeholder="待切词的文本段落"> </textarea>
                            <small>输入待切词的文本段落</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" id="wordsplitCommit" class="am-btn am-btn-primary">提交</button>

                        </div>
                    </div>
                </form>
            </div>
        </div>

        <hr/>

        <p style="width:80%" class="am-center" id="result">


        </p>


    </div>
    <!-- content end -->

</div>


<jsp:include page="../common/foot.jsp"/>

</body>
</html>
