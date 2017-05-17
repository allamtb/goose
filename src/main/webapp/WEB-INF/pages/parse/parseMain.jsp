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
        $("#parseCommit").click(function () {
            var $btn = $(this);
            var $url = $("#url").val();

            if ($url.length == 0) {
                alert("请输入URL地址");
                return;
            }

            $.ajax(
                    {
                        type: 'POST',
                        beforeSend: function () {
                            $("#result").html("");
                            $btn.button('loading');
                        },
                        url: "<c:url value="/parse/doParse"/>",
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
                            url: $url
                        },
                        dataType: "text",
                        contentType:'application/x-www-form-urlencoded; charset=UTF-8'

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
                <small>正文提取</small>
            </div>
        </div>


        <div class="am-cf am-padding" style="width: 80%;margin: 0 auto; border:1px solid #44b549;border-bottom:
        none; border-top: none">
            <p><strong style="color: coral">新闻类网页正文提取</strong> <br/>
                本系统采用<strong>父节点加权比值算法</strong>进行正文内容的提取。 <br/>
                该算法是在总结了前人论文的基础上以及参考了国外优秀的正文采集系统实现提取出来一个算法。<br/>
                其目的就是在对降噪后的DOM节点进行最优正文节点树的判断。通过测试发现，父节点加权比值算法具有对各种HTML布局的新闻正文提取任务有很高的精确度。

            </p>
        </div>

        <div class="am-g" style="border-top:2px solid #0084c7;width: 80%;">

            <div style="margin-top: 10px">
                <form class="am-form am-form-horizontal">
                    <div class="am-form-group">
                        <label for="url" class="am-u-sm-3 am-form-label">请输入任意网页正文的URL地址</label>

                        <div class="am-u-sm-9">
                            <input value="http://news.163.com/15/0818/00/B18TO5BJ00014JB6.html?bdsj" onfocus="this.value=''" onblur="if(this.value==''){this.value='请输入文字'}"  type="url" id="url" placeholder="网页正文UR">
                            <small>输入新闻网页正文URL</small>
                        </div>
                    </div>

                    <div class="am-form-group">
                        <div class="am-u-sm-9 am-u-sm-push-3">
                            <button type="button" id="parseCommit" class="am-btn am-btn-primary">提交</button>

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
