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

<style>
    .stopWord {
        color: red;
        font-weight: bold;
    }

</style>


<script type="text/javascript">

    $(function () {

        $("#stopwordCommit").click(function () {

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
                        url: "<c:url value="/parse/parseStopWord"/>",
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
                <small>切词</small>
            </div>
        </div>

        <div class="am-cf am-padding" style="width: 80%;margin: 0 auto; border:1px solid #44b549;border-bottom:
        none; border-top: none">
            <p><strong style="color: #c10802">停止词</strong>
                是被搜索引擎认为是无意义的辅助词。 <br/>
                但是在文章采集和提取过程中，停止词确有相当大的作用。因为文章的构成必然是有停止词的。如果HTML中某些节点只有所谓的关键词，那么的可以认为这个节点可能是广告标签或者面包屑等。
            </p>
        </div>
        <div class="am-g" style="border-top:2px solid #0084c7;width: 80%;">

            <div style="margin-top: 10px">

                <form class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="url" class="am-u-sm-3 am-form-label">请输入待计算停止词的文本段落</label>

                        <div class="am-u-sm-9">
                            <textarea style="min-height:180px" id="url" placeholder="待计算停止词的文本段落"> </textarea>
                            <small>待计算停止词的文本段落</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" id="stopwordCommit" class="am-btn am-btn-primary">提交</button>

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
