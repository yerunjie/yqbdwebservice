<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
    <link href="/wechat/index/css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <!-- jQuery (necessary JavaScript plugins) -->
    <script src="/wechat/index/js/bootstrap.js"></script>
    <!-- Custom Theme files -->
    <link href="/wechat/index/css/style.css" rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="/wechat/weui/lib/weui.min.css">
    <link rel="stylesheet" href="/wechat/weui/css/jquery-weui.min.css">
    <link rel="stylesheet" href="/wechat/css/module.css">
    <script src="/wechat/weui/lib/jquery-2.1.4.js"></script>
    <script src="/wechat/weui/js/jquery-weui.min.js"></script>
    <!-- Custom Theme files -->
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Game Box  Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <style type="text/css">
        .category-head {
            width: 100%;
            display: inline;
            white-space: nowrap;
            overflow-x: scroll;
            float: left;
            overflow-y: hidden
        }

        .category-head li {
            display: inline-block;
            padding-left: 10px;
        }
    </style>

    <script src="/wechat/index/js/jquery.min.js"></script>
</head>
<body>
<script src="/wechat/index/js/responsiveslides.min.js"></script>
<script>
    $(function () {
        $("#slider").responsiveSlides({
            auto: true,
            nav: false,
            speed: 500,
            namespace: "callbacks",
            pager: true,
        });
    });

</script>
<div class="weui-panel weui-panel_access">
    <!-- content -->

    <div class="slider">
        <div class="callbacks_container">
            <ul class="rslides" id="slider">
                <div class="slid banner1"
                     style="background: url('http://goout-1252946747.cossh.myqcloud.com/${task.simpleDrawingAddress}')">
                    <div class="caption">

                    </div>
                </div>
            </ul>
        </div>
    </div>
</div>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">标题</h4><p class="weui-media-box__desc">${task.taskTitle}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">描述</h4><p class="weui-media-box__desc">${task.taskDescription}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">主要工作</h4><p class="weui-media-box__desc">${task.primaryWork}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">地点</h4><p class="weui-media-box__desc">${task.taskAddress}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">开始时间</h4><p class="weui-media-box__desc">${task.startTime?string("yyyy-MM-dd HH:mm:ss")}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">结束时间</h4><p class="weui-media-box__desc">${task.completeTime?string("yyyy-MM-dd HH:mm:ss")}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">报酬</h4><p class="weui-media-box__desc">${task.pay}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">最大人数</h4><p class="weui-media-box__desc">${task.maxPeopleNumber}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">联系方式</h4><p class="weui-media-box__desc">${task.primaryContact}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">合作单位</h4><p class="weui-media-box__desc">${task.otherCompany}</p>
    </div>
</a>
<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
    <div class="weui-media-box__bd">
        <h4 class="weui-media-box__title">备注</h4><p class="weui-media-box__desc">${task.remark}</p>
    </div>

</a>
<#include "wechat_footer.ftl"/>
<!---->
</body>
</html>