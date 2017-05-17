<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--* * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0 
 * Time:2015/7/6 16:57
 *
 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand">
        <strong>基于Jsoup的通用网页采集系统</strong> <small>后台管理</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                   <%-- <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>--%>
                    <li><a href="#" data-am-modal="{target: '#my-popup'}"><span class="am-icon-cog"  ></span> 设置</a></li>
                  <%--  <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>--%>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>


<div class="am-popup" id="my-popup">
    <div class="am-popup-inner">
        <div class="am-popup-hd">
            <h4 class="am-popup-title">设置属性</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
        </div>
        <div class="am-popup-bd">

            <form class="am-form am-form-horizontal">
                <div class="am-form-group">

                    <label for="proxyValue" class="am-u-sm-3 am-form-label">代理地址</label>
                    <div class="am-u-sm-9">
                        <input value="请输入文字" onfocus="this.value='172.17.18.80:8080'"
                               onblur="if(this.value==''){this.value='请输入文字'}"  type="url" id="proxyValue"
                               placeholder="代理地址">
                        <small>网页抓取使用的代理地址，如果为空，则不使用代理</small>
                    </div>

                    <label for="stopWordThreshold" class="am-u-sm-3 am-form-label">停止词阈值</label>
                    <div class="am-u-sm-9">
                        <input value="请输入文字" onfocus="this.value='3'"
                               onblur="if(this.value==''){this.value='请输入文字'}"  type="url" id="stopWordThreshold"
                               placeholder="停止词阈值">
                        <small>停止词阈值，阈值越小，得到的内容越多但得到噪音数据的几率更高</small>
                    </div>
                </div>

                <div class="am-form-group">
                    <div class="am-u-sm-9 am-u-sm-push-3">
                        <button type="button" id="configCommit" class="am-btn am-btn-primary">提交</button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(function () {
        $("#configCommit").click(function () {

            var $btn = $(this);
            var proxyValue = $("#proxyValue").val();
            var stopWordThresholdValue = $("#stopWordThreshold").val();


            $.ajax(
                    {
                        type: 'POST',
                        beforeSend: function () {
                            $("#result").html("");
                            $btn.button('loading');
                        },
                        url: "<c:url value="/config/updateConfig"/>",
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log(XMLHttpRequest.status);
                            console.log(XMLHttpRequest.readyState);
                            console.log(XMLHttpRequest.responseText);
                            console.log(textStatus);
                            alert("更新失敗");
                        },
                        success: function (html) {
                           alert("更新成功！");
                        },
                        complete: function () {
                            $btn.button('reset');
                        },
                        data: {
                            proxyValue: proxyValue,
                            stopWord: stopWordThresholdValue
                        },
                        dataType: "text",
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
                    }
            )
        });


        $('#my-popup').on('close:modal:amui', function(){
            alert("關閉了");
        });
    })



</script>