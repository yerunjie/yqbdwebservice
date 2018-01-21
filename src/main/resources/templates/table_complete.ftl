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
                    <div class="am-u-sm-12 am-u-md-6">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-xs">
                                <button type="button" class="am-btn am-btn-default" onclick="postTask();return false;"><span class="am-icon-plus"></span>
                                    新增
                                </button>
                                <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span>
                                    保存
                                </button>
                                <button type="button" class="am-btn am-btn-default"><span
                                        class="am-icon-archive"></span> 审核
                                </button>
                                <button type="button" class="am-btn am-btn-default"><span
                                        class="am-icon-trash-o"></span> 删除
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="am-u-sm-12 am-u-md-3">
                        <div class="am-input-group am-input-group-sm">
                            <input type="text" class="am-form-field">
                            <span class="am-input-group-btn">
				            <button class="am-btn am-btn-default" type="button">搜索</button>
				          </span>
                        </div>
                    </div>
                </div>
                <!-- Row end -->

                <!-- Row start -->
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox"/></th>
                                    <th class="table-id">ID</th>
                                    <th class="table-title">标题</th>
                                    <th class="table-type">类别</th>
                                    <th class="table-author am-hide-sm-only">人数</th>
                                    <th class="table-date am-hide-sm-only">修改日期</th>
                                    <th class="table-set">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list taskList as task>
                                <tr>
                                    <td><input type="checkbox"/></td>
                                    <td>${task.taskId}</td>
                                    <td><a href="" onclick="singleTask(${task.taskId})">${task.taskTitle}</a></td>
                                    <td>default</td>
                                    <td class="am-hide-sm-only">${task.currentPeopleNumber}/${task.maxPeopleNumber}</td>
                                    <td class="am-hide-sm-only">${task.deadline?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <button class="am-btn am-btn-default am-btn-xs am-text-secondary"
                                                        onclick="editTask(${task.taskId});return false;"><span
                                                        class="am-icon-pencil-square-o"></span> 编辑
                                                </button>
                                                <button class="am-btn am-btn-default am-btn-xs am-hide-sm-only"
                                                        onclick="showParticipant(${task.taskId});return false;"><span
                                                        class="am-icon-copy"></span> 申请者
                                                </button>
                                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
                                                        onclick="deleteTask(${task.taskId})">
                                                    <span class="am-icon-trash-o"></span> 删除
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                            <div class="am-cf">
                                共 ${taskList?size} 条记录
                                <div class="am-fr">
                                    <ul class="am-pagination">
                                        <li class="am-disabled"><a href="#">«</a></li>
                                        <li class="am-active"><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">»</a></li>
                                    </ul>
                                </div>
                            </div>
                            <hr/>
                            <p>注：.....</p>
                        </form>
                    </div>

                </div>
                <!-- Row end -->

            </div>


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
<script type="text/javascript">
    function singleTask(x) {
        var account = x;
        $.ajax({
            type: "post",
            url: "/task/singleTask",
            timeout: 8000,
            dataType: "json",
            data: {
                "taskId": account
            },

            success: function (data) {
                window.location.href = "/webCompany/singleTask";
            },

            error: function () {
                alert("请求出错");
            }
        })

    }
    function editTask(x) {
        var account = x;
        $.ajax({
            type: "post",
            url: "/task/singleTask",
            timeout: 8000,
            dataType: "json",
            data: {
                "taskId": account
            },
            success: function (data) {
                window.location.href = "/webCompany/editTask";
            },
            error: function () {
                alert("请求出错");
            }
        })
    }
    function deleteTask(x) {
        var account = x;
        $.ajax({
            type: "post",
            url: "/task/deleteTask",
            timeout: 8000,
            dataType: "json",
            data: {
                "taskId": account
            },
            success: function (data) {
                window.location.href = "/webCompany/table_complete";
            },
            error: function () {
                alert("请求出错");
            }
        })
    }
    function showParticipant(x) {
        var account = x;
        $.ajax({
            type: "post",
            url: "/task/showParticipant",
            timeout: 8000,
            dataType: "json",
            data: {
                "taskId": account
            },
            success: function (data) {
                window.location.href = "/webCompany/showParticipant";
            },
            error: function () {
                alert("请求出错");
            }
        })
    }
    function postTask() {
        window.location.href = "/webCompany/post_task";

    }
</script>
