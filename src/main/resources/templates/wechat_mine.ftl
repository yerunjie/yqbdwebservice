<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no" />
<title>我</title>
<link rel="stylesheet" href="/wechat/weui/lib/weui.min.css">
<link rel="stylesheet" href="/wechat/weui/css/jquery-weui.min.css">
<link rel="stylesheet" href="/wechat/css/module.css">
<script src="/wechat/weui/lib/jquery-2.1.4.js"></script>
<script src="/wechat/weui/js/jquery-weui.min.js"></script>
<script type='text/javascript' src='/wechat/js/swiper.js' charset='utf-8'></script>
</head>
<body>
	<div class="user">
		<div class="userImg"><img src="http://goout-1252946747.cossh.myqcloud.com/${userInfo.headPortrait}" /></div>
		<div class="ueseText">
			<h3>${userInfo.nickName} </h3>
			<p>${userInfo.school}</p>
		</div>
	</div>

    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">姓名<span>${userInfo.realName}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">性别<span>${userInfo.sex}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">服务专业性等级<span>${userInfo.professionalLevel}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">信用与服务等级<span>${userInfo.creditLevel}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">手机号<span>${userInfo.telephone}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">学校<span>${userInfo.school}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">职业<span>${userInfo.occupation}</span></h4>
        </div>
    </a>
    <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
        <div class="weui-media-box__bd">
            <h4 class="weui-media-box__title">公司<span>${userInfo.companyName}</span></h4>
        </div>
    </a>
    <button type="button"  onclick="location.href='my_task'" >我的任务</button>
	<#include "wechat_footer.ftl"/>
</body>
</html>
