<div class="weui-tabbar">
    <a href="/wechat/index" class="weui-tabbar__item <#if module == "wechat_index">weui-bar__item--on</#if>">
       <#-- <span class="weui-badge" style="position: absolute;top: -.4em;right: 2em;">8</span>-->
        <div class="weui-tabbar__icon">
            <img src="/wechat/images/gongzuo.png" alt="">
        </div>
        <p class="weui-tabbar__label">首页</p>
    </a>
    <a href="/wechat/list" class="weui-tabbar__item <#if module == "wechat_list">weui-bar__item--on</#if>">
        <div class="weui-tabbar__icon">
            <img src="/wechat/images/tongxun.png" alt="">
        </div>
        <p class="weui-tabbar__label">志愿</p>
    </a>
    <a href="/wechat/personal" class="weui-tabbar__item <#if module == "wechat_index">weui-bar__item--on</#if>">
        <div class="weui-tabbar__icon">
            <img src="/wechat/images/mine.png" alt="">
        </div>
        <p class="weui-tabbar__label">我</p>
    </a>
</div>