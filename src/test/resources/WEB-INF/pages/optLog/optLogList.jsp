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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">操作記錄</strong> /
                <small>記錄列表</small>
            </div>
        </div>



            <table class="am-table am-table-bordered am-table-radius am-table-striped">
                <thead>
                <tr>
                    <th style="width:20%">模塊名稱</th>
                    <th style="width:30%">輸入參數</th>
                    <th style="width:40%">返回結果</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="optLog" items="${optLogList}">
                <tr>
                    <td>${optLog.moudal}</td>
                    <td>${optLog.request}</td>
                    <td style=":20%">${optLog.reponse}</td>
                </tr>
                </c:forEach>

                </tbody>
            </table>

    <!-- content end -->

</div>


<jsp:include page="../common/foot.jsp"/>

</body>
</html>
