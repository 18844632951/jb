/**
 * Created by 27654 on 2018/10/14.
 */
var home = {
    ////选择菜单项事件
    selectItem: function () {
        //获取所有a节点
        var as = document.getElementById('menus').getElementsByTagName('a');
        for (var i = 0; i < as.length; i++) {	//绑定单击事件
            as[i].onclick = function () {
                for (var j = 0; j < as.length; j++) {	//清除所有a节点的的选中效果
                    as[j].classList.remove('active');
                }
                this.classList.add('active');		//给自己添加选中效果
                if (this.parentNode.className == 'item') {	//点击的是一级菜单哦
                    var subItem = this.parentNode.getElementsByClassName('sub-item')[0];
                    //打开或关闭子菜单
                    if (subItem && subItem.style.display == "none" || subItem.style.display == '') {
                        subItem.style.display = 'block';
                    } else {
                        subItem.style.display = 'none';
                    }
                } else {		//二级菜单呢   搞点什么呢？  待定吧！！！！
                    //TODO
                }
            }
        }
    },////隐藏菜单事件
    switchItems: function () {
        var sw = document.getElementById('switch-items');
        var leftWrap = document.getElementById('left-wrap');
        var items = leftWrap.getElementsByClassName('item');
        //关闭侧栏菜单
        function closeM() {
            //关闭所有的子菜单
            var subitems = document.getElementById('menus').getElementsByClassName('sub-item');
            for (var j = 0; j < subitems.length; j++) {
                if (subitems[j].style.display == 'block') {
                    subitems[j].style.display = 'none';
                }
            }
            //操作类似显示侧栏 注释见openM
            leftWrap.style.cssText = "width:60px;animation: iui-slideLeft .5s;";
            for (var i = 0; i < items.length; i++) {
                var title = items[i].getElementsByClassName('title')[0];
                title.style.display = "none";
            }
            document.getElementsByClassName("photo")[0].style.cssText = "transform: scale(.5);";
            //切换logo
            var logo = document.getElementsByClassName("logo")[0];
            logo.innerHTML = "<span style='font-family: Arial;font-size: 20px; color:orangered;'> JB </span>";
        }

        //打开侧栏菜单
        function openM() {
            //设置打开动画
            leftWrap.style.cssText = 'width: 230px; animation: iui-slideRight .5s;';
            //TODO 待优化  显示侧栏菜单
            for (var i = 0; i < items.length; i++) {
                var title = items[i].getElementsByClassName('title')[0];
                title.style.display = "inline-block";
            }
            document.getElementsByClassName("photo")[0].style.cssText = "transform: scale(1);";
            //切换logo
            var logo = document.getElementsByClassName("logo")[0];
            logo.innerHTML = '<span style="font-family: 隶属; font-size:20px; ">JB平台管理中心</span>'; //logo的切换处理
        }

        //初始化事件
        sw.onclick = function () {
            if (leftWrap.clientWidth < 170) {
                openM();
            } else {
                closeM();
            }
        }
    },////全局对话框
    dialog: function (path, json) {
        if (path != "" || path != null) {
            document.getElementById("bgpanel").style.display = "block";
			$("#dialog").src=path;
            $("#dialog").show(500);
        }
        if (json.width != undefined) {
            $("#dialog").width = json.width;
        }
        if (json.height != undefined) {
            $("#dialog").height = json.height;
        }
    },
    initDialog: function () {		//关闭 弹框 iframe
        document.getElementById("exit").onclick = function () {
            document.getElementById("bgpanel").style.display = "none";
        }
    },
    init: function () {
        this.selectItem();
        this.switchItems();
        this.initDialog();
    }
};
