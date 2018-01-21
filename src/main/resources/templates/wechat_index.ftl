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
    <div class="content">
        <div class="container">
            <div class="top-games">
                <h3>志愿推荐</h3>
                <span></span>
            </div>
            <ul class="category-head" id="category-head" style="">
                <#list taskList as task>
                    <li>
                        <div class="game-grid">
                            <h4>${task.taskTitle}</h4>
                            <#--<p>Nulla elementum nunc tempus.</p>-->
                            <img src="http://goout-1252946747.cossh.myqcloud.com/${task.simpleDrawingAddress}"
                                 style="height: 150px" alt=""/>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <div class="slider">
        <div class="callbacks_container">
            <ul class="rslides" id="slider">
                <div class="slid banner1">
                    <div class="caption">
                        <h3>海外游学 | 三十天，做一个真正的伦敦学子</h3>
                    </div>
                </div>


            <#--<div class="slid banner2">
                <div class="caption">
                    <h3>Pellentesque eu ante quis elit interdum cursus.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec pellentesque ex. Morbi
                        iaculis
                        mi in varius auctor. Nullam feugiat erat ex, eu vehicula velit efficitur non.</p>
                </div>
            </div>


            <div class="slid banner3">
                <div class="caption">
                    <h3>Fusce id urna ut felis feugiat fringilla sed quis nisl.</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec pellentesque ex. Morbi
                        iaculis
                        mi in varius auctor. Nullam feugiat erat ex, eu vehicula velit efficitur non.</p>
                </div>
            </div>-->

            </ul>
        </div>
    </div>
    <div class="content">
        <div class="container">
            <div class="top-games">
                <h3>合作商推荐</h3>
                <span></span>
            </div>
            <ul class="category-head" id="category-head" style="">
            <#list companyList as company>
                <li>
                    <div class="game-grid">
                        <h4>${company.companyName}</h4>
                    <#--<p>Nulla elementum nunc tempus.</p>-->
                        <img src="http://goout-1252946747.cossh.myqcloud.com/${company.headPortraitAddress}"
                             style="height: 150px" alt=""/>
                    </div>
                </li>
            </#list>
            </ul>
        </div>
    </div>
</div>

<#include "wechat_footer.ftl"/>
<!---->
</body>
</html>