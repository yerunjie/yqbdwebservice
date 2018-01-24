<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no"/>
    <title>我的任务</title>
    <link rel="stylesheet" href="/wechat/weui/lib/weui.min.css">
    <link rel="stylesheet" href="/wechat/weui/css/jquery-weui.min.css">
    <link rel="stylesheet" href="/wechat/css/module.css">
    <script src="/wechat/weui/lib/jquery-2.1.4.js"></script>
    <script src="/wechat/weui/js/jquery-weui.min.js"></script>
    <script type='text/javascript' src='/wechat/js/swiper.js' charset='utf-8'></script>
</head>
<body>
<div class="user">
    <div class="userImg"><img src="update/user.jpg"/></div>
    <div class="ueseText">
        <h3>${userInfo.nickName}</h3>
        <p>${userInfo.school}</p>
    </div>
</div>
<div class="weui-panel__bd ">
<#list taskList as task>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__hd">
            <img class="weui-media-box__thumb"  src="http://goout-1252946747.cossh.myqcloud.com/${task.simpleDrawingAddress}">
        </div>
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">${task.taskTitle}</h4>
            <p class="weui-media-box__desc">${task.taskDescription}</p>
        </div>
    </a>
</#list>
</div>
<#include "wechat_footer.ftl"/>
</body>
</html>
