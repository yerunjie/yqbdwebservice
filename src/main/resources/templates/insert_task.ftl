<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Go Out</title>
    <link rel="stylesheet" href="../assets/css/amazeui.css"/>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../assets/css/core.css"/>
    <link rel="stylesheet" href="../assets/css/menu.css"/>
    <link rel="stylesheet" href="../assets/css/index.css"/>
    <link rel="stylesheet" href="../assets/css/admin.css"/>
    <link rel="stylesheet" href="../assets/css/page/typography.css"/>
    <link rel="stylesheet" href="../assets/css/page/form.css"/>
</head>
<body>
<!-- Begin page -->
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-topbar-left am-hide-sm-only">
        <a href="/index" class="logo"><span>Go<span>to</span></span><i class="zmdi zmdi-layers"></i></a>
    </div>

    <div class="contain">
        <ul class="am-nav am-navbar-nav am-navbar-left">

            <li><h4 class="page-title">基本表格</h4></li>
        </ul>

        <ul class="am-nav am-navbar-nav am-navbar-right">
            <li class="inform"><i class="am-icon-bell-o" aria-hidden="true"></i></li>
            <li class="hidden-xs am-hide-sm-only">
                <form role="search" class="app-search">
                    <input type="text" placeholder="Search..." class="form-control">
                    <a href=""><img src="../assets/img/search.png"></a>
                </form>
            </li>
        </ul>
    </div>
</header>
<!-- end page -->


<div class="admin">
    <!--<div class="am-g">-->
    <!-- ========== Left Sidebar Start ========== -->
    <!--<div class="left side-menu am-hide-sm-only am-u-md-1 am-padding-0">
        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 548px;">
            <div class="sidebar-inner slimscrollleft" style="overflow: hidden; width: auto; height: 548px;">-->
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <!-- User -->
            <div class="user-box am-hide-sm-only">
                <div class="user-img">
                    <img src="../assets/img/avatar-1.jpg" alt="user-img" title="Mat Helme"
                         class="img-circle img-thumbnail img-responsive">
                    <div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>
                </div>
                <h5><a href="#">${company_name}</a></h5>
                <ul class="list-inline">
                    <li>
                        <a href="#">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>

                    <li>
                        <a href="#" class="text-custom">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- End User -->

            <ul class="am-list admin-sidebar-list">
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-table"></span>
                        任务管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                        <li><a href="table_complete">我的任务</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><i class="am-icon-line-chart"
                                                                                      aria-hidden="true"></i> 日常图表 <span
                            class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav2">
                        <li><a href="chart_line" class="am-cf"> 访问状况图</span></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- sidebar end -->

    <!--</div>
</div>
</div>-->
    <!-- ========== Left Sidebar end ========== -->


    <!--	<div class="am-g">-->
    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="card-box">
                <!-- Row start -->
                <div class="am-g">
                    <div class="am-g">
                        <div class="am-u-sm-12">
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">ID</label>
                                ${task.taskId}
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">标题</label>
                                    <input class="am-u-md-10 form-control" id="doc-ipt-text-1" name ="doc-ipt-text-1" placeholder="输入标题信息" value="${task.taskTitle}">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">介绍</label>
                                    <input class="am-u-md-10 form-control" id="doc-ipt-text-2" name ="doc-ipt-text-2" placeholder="输入介绍信息" value="${task.taskDescription}">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">报酬</label>
                                    <input class="am-u-md-10 form-control" id="doc-ipt-text-3" name ="doc-ipt-text-3" placeholder="输入价格信息" value="${task.pay}">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">发布时间</label>
                                    <input class="am-u-md-10 form-control" type="datetime-local" id="doc-ipt-text-4" name ="doc-ipt-text-4" value="${task.publishTime?string("yyyy-MM-dd'T'HH:mm:ss")}">

                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">截止时间</label>
                                    <input class="am-u-md-10 form-control" type="datetime-local" id="doc-ipt-text-5" name ="doc-ipt-text-5" value="${task.deadline?string("yyyy-MM-dd'T'HH:mm:ss")}">
                                </div>
                            </div>
                            <div class="am-form-group">
                                <div class="am-g">
                                    <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">开始时间</label>
                                    <input class="am-u-md-10 form-control" type="datetime-local" id="doc-ipt-text-6" name ="doc-ipt-text-6" value="${task.startTime?string("yyyy-MM-dd'T'HH:mm:ss")}">
                                </div>
                            </div>
                            <div>
                                <div class="am-form-group">
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">结束时间</label>
                                            <input class="am-u-md-10 form-control" type="datetime-local" id="doc-ipt-text-7" name ="doc-ipt-text-7" value="${task.completeTime?string("yyyy-MM-dd'T'HH:mm:ss")}">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">最大人数</label>
                                            <input class="am-u-md-10 form-control" id="doc-ipt-text-8" name ="doc-ipt-text-8" placeholder="输入最大人数信息" value="${task.maxPeopleNumber}">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-pwd-1">地点</label>
                                            <input class="am-u-md-10 form-control" id="doc-ipt-text-9" name ="doc-ipt-text-9" placeholder="输入地址" value="${task.taskAddress}">
                                        </div>
                                    </div>
                                </div>
                                <button type="button" onclick="editTask(${task.taskId})" class="am-btn am-btn-primary">Submit</button>
                            </div>
                            <!-- Row end -->

                        </div>
                    </div>
                </div>
                <!-- end right Content here -->
                <!--</div>-->
            </div>
        </div>

        <!-- navbar -->
        <a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
           data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

        <script type="text/javascript" src="../assets/js/jquery-2.1.0.js"></script>
        <script type="text/javascript" src="../assets/js/amazeui.min.js"></script>
        <script type="text/javascript" src="../assets/js/app.js"></script>
        <script type="text/javascript" src="../assets/js/blockUI.js"></script>


</body>

</html>
<script>
    function editTask(x) {
        var id = x;
        var taskTitle = $("#doc-ipt-text-1").val();
        var taskDescription= $("#doc-ipt-text-2").val();
        var pay = $("#doc-ipt-text-3").val();
        var publishTime = $("#doc-ipt-text-4").val();
        var deadline = $("#doc-ipt-text-5").val();
        var startTime = $("#doc-ipt-text-6").val();
        var completeTime = $("#doc-ipt-text-7").val();
        var maxPeopleNumber = $("#doc-ipt-text-8").val();
        var taskAddress = $("#doc-ipt-text-9").val();
        $.ajax({
            type: "post",
            url: "/task/editTask",
            timeout: 80000,
            dataType: "json",
            data: {
                "taskId" : id,
                "taskTitle": taskTitle,
                "taskDescription": taskDescription,
                "pay":pay,
                "publishTime":publishTime,
                "deadline":deadline,
                "startTime":startTime,
                "completeTime":completeTime,
                "maxPeopleNumber":maxPeopleNumber,
                "taskAddress":taskAddress
            },

            success: function (data) {
                alert("修改完成");
                window.location.href = "/webCompany/singleTask";
            },

            error: function () {
                alert("请求出错");
            }
        })

    }
</script>