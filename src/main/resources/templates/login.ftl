<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="../assets/css/core.css" />
    <link rel="stylesheet" href="../assets/css/menu.css" />
    <link rel="stylesheet" href="../assets/css/amazeui.css" />
    <link rel="stylesheet" href="../assets/css/component.css" />
    <link rel="stylesheet" href="../assets/css/page/form.css" />
</head>
<body>
<div class="account-pages">
    <div class="wrapper-page">
        <div class="text-center">
            <a href="index.html" class="logo"><span>Admin<span>to</span></span></a>
        </div>

        <div class="m-t-40 card-box">
            <div class="text-center">
                <h4 class="text-uppercase font-bold m-b-0">Sign In</h4>
            </div>
            <div class="panel-body">
                <form class="am-form">
                    <div class="am-g">
                        <div class="am-form-group">
                            <input type="email" name="account" id="account"  class="am-radius"  placeholder="Username">
                        </div>

                        <div class="am-form-group form-horizontal m-t-20">
                            <input type="password" name="password" id="password" class="am-radius"  placeholder="Password">
                        </div>

                        <div class="am-form-group ">
                            <label style="font-weight: normal;color: #999;">
                                <input type="checkbox" class="remeber"> Remember me
                            </label>
                        </div>

                        <div class="am-form-group ">
                            <button type="button" class="am-btn am-btn-primary am-radius" style="width: 100%;height: 100%;" onclick="loginfuc();return false;">Log In</button>
                        </div>

                        <div class="am-form-group ">
                            <a href="page-recoverpw.html" class="text-muted"><i class="fa fa-lock m-r-5"></i> Forgot your password?</a>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../assets/js/jquery-2.1.0.js" ></script>
<script type="text/javascript" src="../assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="../assets/js/app.js" ></script>
<script type="text/javascript" src="../assets/js/blockUI.js" ></script>
<script type="text/javascript">
    function loginfuc() {
        var account = $("#account").val();
        var password = $("#password").val();

        $.ajax({
            type: "post",
            url:"/company/companyLogin",
            timeout:8000,
            dataType:"json",
            data:{
                "companyAccount":account,
                "companyPassword":password
            },

            success:function(data){
                if(data.returnCode==="0"){
                    alert("账号或密码错误！");
                }
                else if(data.returnCode==="-1"){
                    alert("没有该用户");
                }
                else{
                    alert("登录成功");
                    window.location.href="index";
                }
            },

            error:function(){
                alert("请求出错")
            }
        })

    }
</script>
