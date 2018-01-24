<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,minimum-scale=1,maximum-scale=1,initial-scale=1,user-scalable=no"/>
    <title>任务列表</title>
    <link rel="stylesheet" href="/wechat/weui/lib/weui.min.css">
    <link rel="stylesheet" href="/wechat/weui/css/jquery-weui.min.css">
    <link rel="stylesheet" href="/wechat/css/module.css">
    <script src="/wechat/weui/lib/jquery-2.1.4.js"></script>
    <script src="/wechat/weui/js/jquery-weui.min.js"></script>
    <script type='text/javascript' src='/wechat/js/swiper.js' charset='utf-8'></script>
</head>
<body class="weui-pull-to-refresh">
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>
<#--<div class="weui-search-bar" id="searchBar">
    <form class="weui-search-bar__form">
        <div class="weui-search-bar__box">
            <i class="weui-icon-search"></i>
            <input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
            <a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
        </div>
        <label class="weui-search-bar__label" id="searchText">
            <i class="weui-icon-search"></i>
            <span>搜索</span>
        </label>
    </form>
    <a href="javascript:" class="weui-search-bar__cancel-btn" id="searchCancel">取消</a>
</div>-->

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd">工作列表</div>
    <div class="weui-panel__bd ">
    <#list taskList as task>
        <a onclick="singleTask(${task.taskId})" href="" class="weui-media-box weui-media-box_appmsg">
            <div class="weui-media-box__hd">
                <img class="weui-media-box__thumb" src="http://goout-1252946747.cossh.myqcloud.com/${task.simpleDrawingAddress}">
            </div>
            <div class="weui-media-box__bd">
                <h4 class="weui-media-box__title">${task.taskTitle}</h4>
                <p class="weui-media-box__desc">${task.taskDescription}</p>
            </div>
        </a>
    </#list>
    </div>
    <#--<div class="weui-panel__ft">
        <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
            <div class="weui-cell__bd">查看更多</div>
            <span class="weui-cell__ft"></span>
        </a>
    </div>-->
</div>


<#include "wechat_footer.ftl"/>
<script>
    $(document.body).pullToRefresh().on("pull-to-refresh", function () {
        setTimeout(function () {
            $(document.body).pullToRefreshDone();
        }, 2000);
    });
</script>
</body>
</html>

<script>
   function singleTask(taskId) {
       var account = taskId;
       $.ajax({
           type: "post",
           url: "/wechat/single_task_search",
           timeout: 80000,
           dataType: "json",
           data: {
               "taskId": account
           },
           success: function (data) {
               window.location.href = "/wechat/single_task";
           },
           error: function () {
               alert("请求出错");
           }
       })

   }
</script>