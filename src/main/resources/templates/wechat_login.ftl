<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Login/Logout animation concept</title>
    <meta name="viewport" content="width=device-width"/>
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="/wechat/css/style_login.css" type="text/css" />

</head>

<body>

<div class="cont">
    <div class="demo">
        <div class="login">
            <div class="login__check"></div>
            <div class="login__form">
                <div class="login__row">
                    <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
                        <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
                    </svg>
                    <input type="text" class="login__input name" name="account" id="account"  placeholder="Username"/>
                </div>
                <div class="login__row">
                    <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
                        <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
                    </svg>
                    <input type="password"name="password" id="password" class="login__input pass" placeholder="Password"/>
                </div>
                <button type="button" class="login__submit" onclick="loginfuc();return false;">Sign in</button>
                <p class="login__signup">Don't have an account? &nbsp;<a>Sign up</a></p>
            </div>
        </div>
        <div class="app">
            <div class="app__top">
                <div class="app__menu-btn">
                    <span></span>
                </div>
                <svg class="app__icon search svg-icon" viewBox="0 0 20 20">
                    <!-- yeap, its purely hardcoded numbers straight from the head :D (same for svg above) -->
                    <path d="M20,20 15.36,15.36 a9,9 0 0,1 -12.72,-12.72 a 9,9 0 0,1 12.72,12.72" />
                </svg>
                <p class="app__hello">Good Morning!</p>
                <div class="app__user">
                    <img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/142996/profile/profile-512_5.jpg" alt="" class="app__user-photo" />
                    <span class="app__user-notif">3</span>
                </div>
                <div class="app__month">
                    <span class="app__month-btn left"></span>
                    <p class="app__month-name">March</p>
                    <span class="app__month-btn right"></span>
                </div>
            </div>
            <div class="app__bot">
                <div class="app__days">
                    <div class="app__day weekday">Sun</div>
                    <div class="app__day weekday">Mon</div>
                    <div class="app__day weekday">Tue</div>
                    <div class="app__day weekday">Wed</div>
                    <div class="app__day weekday">Thu</div>
                    <div class="app__day weekday">Fri</div>
                    <div class="app__day weekday">Sad</div>
                    <div class="app__day date">8</div>
                    <div class="app__day date">9</div>
                    <div class="app__day date">10</div>
                    <div class="app__day date">11</div>
                    <div class="app__day date">12</div>
                    <div class="app__day date">13</div>
                    <div class="app__day date">14</div>
                </div>
                <div class="app__meetings">
                    <div class="app__meeting">
                        <img src="http://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/profile/profile-80_5.jpg" alt="" class="app__meeting-photo" />
                        <p class="app__meeting-name">Feed the cat</p>
                        <p class="app__meeting-info">
                            <span class="app__meeting-time">8 - 10am</span>
                            <span class="app__meeting-place">Real-life</span>
                        </p>
                    </div>
                    <div class="app__meeting">
                        <img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/142996/profile/profile-512_5.jpg" alt="" class="app__meeting-photo" />
                        <p class="app__meeting-name">Feed the cat!</p>
                        <p class="app__meeting-info">
                            <span class="app__meeting-time">1 - 3pm</span>
                            <span class="app__meeting-place">Real-life</span>
                        </p>
                    </div>
                    <div class="app__meeting">
                        <img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/142996/profile/profile-512_5.jpg" alt="" class="app__meeting-photo" />
                        <p class="app__meeting-name">FEED THIS CAT ALREADY!!!</p>
                        <p class="app__meeting-info">
                            <span class="app__meeting-time">This button is just for demo ></span>
                        </p>
                    </div>
                </div>
            </div>
            <div class="app__logout">
                <svg class="app__logout-icon svg-icon" viewBox="0 0 20 20">
                    <path d="M6,3 a8,8 0 1,0 8,0 M10,0 10,12"/>
                </svg>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src='/wechat/js/jquery.min.js'></script>
<script type="text/javascript" src='/wechat/js/index.js'></script>

</body>
</html>
<script type="text/javascript" src="../assets/js/jquery-2.1.0.js" ></script>
<script type="text/javascript" src="../assets/js/amazeui.min.js"></script>
<script type="text/javascript" src="../assets/js/app.js" ></script>
<script type="text/javascript" src="../assets/js/blockUI.js" ></script>
<script type="text/javascript">
    function loginfuc() {
        alert(1);
        var account = $("#account").val();
        var password = $("#password").val();
        $.ajax({
            type: "post",
            url:"/wechat/userLogin",
            timeout:80000,
            dataType:"json",
            data:{
                "userAccount":account,
                "userPassword":password
            },

            success:function(data){
                alert(2);
                if(data.returnCode==="3.0.E.2"){
                    alert("账号或密码错误！");
                    window.location.href="wechat_login";
                }
                else if(data.returnCode==="3.0.E.1"){
                    alert("没有该用户");
                    window.location.href="wechat_login";
                }
                else{
                    alert("登录成功");
                    window.location.href="wechat_list";
                }
            },

            error:function(){
                alert("请求出错");
            }
        })

    }
</script>
