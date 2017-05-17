<%--* * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/7/6 16:59
 *
 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <li class="am-active"><a href="<c:url value="../../../index.jsp"/>"> <span class="am-icon-home"></span>
                首页</a></li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>功能<span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                    <li><a href="<c:url value= "/forward/parseMain"/>" class="am-cf"><span
                            class="am-icon-check"></span> 正文提取<span
                            class=" am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                    <li><a href="<c:url value= "/forward/wordSplitMain"/>"> <span class="am-icon-puzzle-piece"></span> 分词</a></li>
                    <li><a href="<c:url value= "/forward/stopWordMain"/>"><span class="am-icon-th"></span> 停止词计算</a></li>
                </ul>
            </li>
            <li><a href="<c:url value= "/forward/optLogList"/>"><span class="am-icon-table"></span> 操作记录</a></li>

            <li><a href="#"><span class="am-icon-sign-out"></span> 注销</a></li>
        </ul>

        <div class="am-panel am-panel-default admin-sidebar-panel">
            <div class="am-panel-bd">
                <p><span class="am-icon-bookmark"></span>座右铭</p>
                <p>时光静好，与君语；细水流年，与君同。</p>
            </div>
        </div>

        <div class="am-panel am-panel-default admin-sidebar-panel">
            <div class="am-panel-bd">
                <p><span class="am-icon-tag"></span>Welcome</p>
                <p>Welcome to check  MaoKai's UESTC Master Paper</p>
            </div>
        </div>
    </div>
</div>