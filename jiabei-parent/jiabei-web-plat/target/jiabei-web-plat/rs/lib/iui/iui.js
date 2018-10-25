/**弹出框**/
function prompt(text, time) {
    var node;
    this.text = text;
    this.flush = function (text) {
        node.innerHTML = text;
    }
    function init() {
        node = document.createElement('div');
        node.classList.add('iui-prompt');
        var h = window.innerHeight;
        node.style.top = (h - 110) / 2 + "px";
        node.innerHTML = text;
        document.body.appendChild(node);
    }

    var tm = 0;
    this.close = function (time) {
        tm = setTimeout(function () {
            document.body.removeChild(node);
        }, time);
    }
    if (tm == 0) {
        init();
        if (time != undefined) {
            this.close(time);
        }
    }

}

/**弹出框 开始**/
function popUp(title, text) {
    var html = "<div class='head'><div class='title-wrap'><span>" + title + "</span></div>" +
        "<div class='btn-wrap'><span class='close'>×</span></div></div><div class='body'>" +
        "<div class='content'>" + text + "</div></div>";
    var node = document.createElement('div');
    node.classList.add('iui-popup');
    node.style.left = "0px";
    node.style.top = "100px";
    node.style.zIndex = "1000";

    node.innerHTML = html;
    document.body.appendChild(node);
    var exit = node.getElementsByClassName('close')[0];
    exit.onclick = function () {
        if (node) {
            document.body.removeChild(node);
        }
    }

    function move() {
        var newx, newy, oldx, oldy;
        var w = node.clientWidth;
        var head = node.getElementsByClassName('head')[0];
        var h = head.clientHeight;
        head.onmousedown = function () {
            document.onmousemove = function (e) {
                var ev = e || window.event;
                oldx = ev.clientX;
                oldy = ev.clientY;

                newx = oldx - (2 * w);
                newy = oldy - (h / 2);
                console.log(newx, oldx);
                node.style.left = newx + "px";
                node.style.top = newy + "px";
            }
            document.onmouseup = function () {
                this.onmousemove = function () {
                }
            }
        }
    }

    move();
}
/**弹出框 结束**/

/**消息提示**/
function initTips() {
    var tips = document.getElementsByClassName('iui-tips');
    for (var i = 0; i < tips.length; i++) {
        tips[i].onmouseover = function () {
            this.classList.add('iui-tip');
        }
        tips[i].onmouseout = function () {
            this.classList.remove('iui-tip');
        }
    }
}

initTips();

//
//@version 2.0.0
//===========================================================================================
// 文本输入框：
// 结构：  头部（提示信息）， 身体（内容区），底部（按钮区）
// 	操作：  需要用户创建一段自己需要内容区的html片段  
// 	调用参数：  标题title，id名， 内容区代码块类名body， 提交按钮名字btnName，提交回调函数fun
//   格式： 
//   	var p = new Dialog(title, id, context, btnText, fun);
//		p.show(id)；  显示
//
function Dialog(title, id, context, btnName, fun) {
    this.id = id;
    this.method = fun;

    this.head = "<div class='ihead'><div class='left'><i class='fa fa-exclamation-circle'></i> " + title + "</div><div class='right'>" +
        "<a href='javascript:void(0)' class='close'> × </a></div></div>";
    var content;
    if (context == null) {
        return;
    }
    if (context instanceof Object) {
        this.body = "<div class='ibody scroll'>" + context.innerHTML + "</div>";
    } else {
        this.body = "<div class='ibody scroll'>" + context + "</div>";
    }
    this.footer = "<div class='ifooter'><span class='close iui-btn iui-btn-defalut'>" +
        " 取 消</span><span class='iui-btn iui-btn-blue' id='submit_" + id + "'>" + btnName + "</span></div>";

    this.build();
    this.actionLinster(this.id);
}

Dialog.prototype.build = function () {
    this._dialog = document.createElement("div");
    this._dialog.id = this.id;
    this._dialog.style.display = "none";
    this._dialog.classList.add('iui-dialog');
    this._dialog.innerHTML = this.head;
    this._dialog.innerHTML += this.body;
    this._dialog.innerHTML += this.footer;
    document.body.appendChild(this._dialog);

    this.dialogDom = document.getElementById(this.id);
}

Dialog.prototype.setName = function (newName) {
    var dom = document.getElementById("submit_" + this.id)
    dom.innerText = newName;
}
Dialog.prototype.setting = function (style) {
    this.dialogDom.style.cssText += style;
}

Dialog.prototype.show = function () {
    var dom = document.getElementById(this.id);
    if (dom.style.display == "none") {
        dom.style.animation = "iui-fade-out .5s;";
        setTimeout(function () {
            dom.style.display = 'block';
        }, 500);
    } else if (dom.style.display == "block") {
        dom.style.animation = "iui-fade-out .5s";
        setTimeout(function () {
            dom.style.display = 'none';
        }, 500)
    }
}
//事件处理
Dialog.prototype.actionLinster = function (id) {

    //关闭操作
    function closeSelf(timer) {
        var dom = document.getElementById(id);
        dom.style.animation = "iui-fade-in .5s";
        setTimeout(function () {
            dom.style.display = "none";
            dom.style.animation = "none";
        }, 500);
    }

    //关闭事件绑定
    var cBtns = this._dialog.getElementsByClassName('close');
    for (var i = 0; i < cBtns.length; i++) {
        cBtns[i].onclick = function () {
            closeSelf();
        }
    }
    //绑定提交事件
    var meth = this.method;
    var submitBtn = document.getElementById("submit_" + this.id);
    submitBtn.addEventListener("click", function () {
        meth(closeSelf);
    });
}

/**
 * 插件名：  分页插件
 * 版本 v 1.0.1
 * 作者： 邓水洪
 * 时间： 2018-10-13
 *########################################分页   ##########################################
 * */

function iPage(id, row, size, method) {
    this.dom = document.getElementById(id);
    this.flips = this.dom.getElementsByClassName("flip");
    this.button = this.dom.getElementsByTagName("a");
    this.length = size < 6 ? size : 6;
    this.row = row > 1 ? row : 1;
    this.size = size > 0 ? size : 1;

    this.init(this.row, size);	//初始化控件
    this.addEvent();	//注册时间
    this.getData = method;  //翻页数据请求
}

/**
 * 初始化
 * @return {[type]} [description]
 */
iPage.prototype.init = function (row, size) {
    var html = "";
    for (var i = 1; i <= Math.min(size, this.length); i++) {
        if (i == row) {
            html += '<a href="javascript:void(0)" class="button active">' + i + '</a>';
        } else {
            html += '<a href="javascript:void(0)" class="button">' + i + '</a>';
        }
    }
    this.flips[0].insertAdjacentHTML('afterend', html);	//上一页的后面追加页码

    if (size > this.length) {  	//超过 length 页后 在下一页之前插入 省略页  默认只显示6页记录
        var omit = '<span class="omitted">...</span><a href="javascript:void(0)" class="button">' + size +
            '</a>';
        this.flips[1].insertAdjacentHTML('beforebegin', omit);	//下一页的前面追加总页数
    }
}

/**
 * 翻页事件
 * @return {[type]} [description]
 */
iPage.prototype.addEvent = function () {
    var dom = this;
    var index = 1;
    //上下页切换事件绑定
    for (var i = 0; i < this.flips.length; i++) {
        this.flips[i].onclick = function () {
            for (var j = 1; j < dom.button.length - 1; j++) {  // i=0 和 i= length-1 是切换按钮  没有这些事件
                if (dom.button[j].classList.toString().indexOf("active") > 0) {
                    var text = parseInt(dom.button[j].innerHTML);
                    if (this == dom.flips[0]) {     //上一页
                        dom.row = text > 1 ? text - 1 : 1;	//读取当前页码  不能小于0
                        index--;
                        if (index < 0) {
                            index = 0;
                        }
                        //刷新页码
                        if (dom.row >= 1 && index == 0) {
                            dom.flush(this);
                        }
                    } else {
                        //读取当前页码 不能超过最大显示页
                        dom.row = text < dom.size ? text + 1 : dom.size;
                        index++;
                        if (index > dom.length) {
                            index = dom.length;
                        }
                        if (dom.row > dom.length && dom.row <= dom.size && index == dom.length) {
                            dom.flush(this);
                        }
                    }
                    if (index > 0 && index <= dom.length) {
                        dom.button[j].classList.remove("active");
                        dom.button[index].classList.add("active");
                    }
                    dom.getData(dom.row);       //留给开发人员 外部调用数据
                    break;
                }
            }
        }
    }
    //点击页码事件
    for (var i = 1; i < dom.button.length - 1; i++) {
        dom.button[i].onclick = function () {
            if (this.classList.toString().indexOf("active") > 0) {
                return;
            }
            for (var j = 1; j < dom.button.length - 1; j++) {
                dom.button[j].classList.remove("active");
                if (this == dom.button[j]) {
                    index = j;
                }
            }
            this.classList.add("active");
            dom.row = parseInt(this.innerHTML);	//设置当前页号
            dom.getData(dom.row);       //留给开发人员 外部调用数据
        }
    }
}
/**
 * 刷新页码
 * @param  {[type]} e [description]
 * @return {[type]}   [description]
 */
iPage.prototype.flush = function (e) {
    for (var i = 1; i <= this.length; i++) {
        if (e == this.flips[1]) {
            if (parseInt(this.button[this.length].innerHTML) < this.size) {
                this.button[i].innerHTML = parseInt(this.button[i].innerHTML) + 1;
            } else {
                return;
            }
        } else {
            if (parseInt(this.button[this.length].innerHTML) != 6 &&  parseInt(this.button[i].innerHTML)>1) {
                this.button[i].innerHTML = parseInt(this.button[i].innerHTML) - 1;
            } else {
                return;
            }
        }
    }
}

/**
 * 获取当前页码
 * @return {[type]} [description]
 */
iPage.prototype.getRow = function () {
    return this.row;
}
//留给开发人员 外部调用数据
iPage.prototype.getData = function (row) {
}


/**
 *   ============================================ iTree =====================================================
 *
 * 插件名称： 树形结构
 * 作者： 邓水洪
 * 时间： 2018 - 10 - 05
 * @type {{data: {root: null, wrap: null}, init: iTree.init, create: iTree.create, insertNode: iTree.insertNode, action: iTree.action}
 */
function iTree() {
    this.root = null;
    this.wrap = null;
    this.path = {id: [], name: []};
    this.dataTree = [];         //索引数据  key 分类id  value  分类的信息
}
iTree.prototype.init = function () {
    var rootNode = document.getElementsByClassName("iui-tree");
    for (var i = 0; i < rootNode.length; i++) {
        this.action(rootNode[i]);	//注册事件
    }
}
/**
 * 支持自定义为节点绑点事件  可以为子节点和父节点分别绑点事件响应，  当你只传递一个回调函数是 则所有节点共用一个事假处理方法
 * 当你不传递回调函数时  会处理默认德事件
 * @param id  插件的唯一标识， 插件容器的id 名  要求不允许 否则可能会出现插件不可用
 * @param json  树状插件的数据支撑，  仅支撑json 格式数据
 * @param chilDothing     叶子节点的处理事件绑定
 * @param parentNodeChilDothing 父节点的事件绑定 这里的父节点指凡是有子节点的的节点
 */
iTree.prototype.create = function (id, json, chilDothing, parentNodeChilDothing) {
    this.chilMethod = chilDothing;
    this.rootMethod = parentNodeChilDothing;
    this.root = document.createElement("ul");
    this.root.classList.add("iui-tree");
    var newTree = this.insertNode(this.root, json);	//构造树
    this.wrap = document.getElementById(id);
    this.wrap.style.cssText = "height:100%; overflow:overlay;";
    this.wrap.classList.add("scroll");
    this.wrap.appendChild(newTree);
    this.action(this.root, json);	//注册事件
}
iTree.prototype.insertNode = function (root, json) {
    if (json == undefined) {
        return;
    }
    for (var i = 0; i < json.length; i++) {
        this.dataTree[json[i].id] = json[i];    //添加数据
        if (json[i].children != undefined && json[i].children != '') {     //有子节点， 不是叶子节点
            var li = document.createElement("li");
            var aTag = '<a href="javascript:void(0)" data-index=' + json[i].id + '><i class="fa fa-folder-open"></i> ' + json[i].name +
                '</a>';
            li.insertAdjacentHTML("beforeend", aTag);
            var subNode = document.createElement("ul");
            subNode.classList.add("sub-node");
            li.appendChild(subNode);
            root.appendChild(li);
            this.insertNode(subNode, json[i].children);
        } else {
            var html = '<li><a href="javascript:void(0)" data-index=' + json[i].id + '><i class="fa fa-file"></i> ' + json[i].name + '</a></li>';
            root.insertAdjacentHTML("beforeend", html);
        }
    }
    return root;
}
iTree.prototype.refresh = function (data) {
    while (this.root.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        this.root.removeChild(this.root.firstChild);
    }
    this.insertNode(this.root, data);
    this.action(this.root, data);
}
iTree.prototype.find = function (e) {
    var id = e.getAttribute("data-index");
    return this.dataTree[id];
}
iTree.prototype.chilMethod = function (data, e) {
    //dothing
}
iTree.prototype.rootMethod = function (data, e) {
    //dothing
}
iTree.prototype.action = function (root) {
    var dom = this;
    var items = root.getElementsByTagName("a");
    for (var i = 0; i < items.length; i++) {
        items[i].addEventListener("click", function () {
            for (var i = 0; i < items.length; i++) {
                items[i].style.cssText = "color: #666;font-weight: 500;";
            }
            this.style.cssText = "color:red; font-weight: 700;";
            var subNode = this.nextElementSibling;      //获取下一个子节点
            if (subNode) {
                var data = dom.find(this);
                if (dom.rootMethod) {
                    dom.rootMethod(data, this);
                } else {
                    dom.chilMethod(data, this);
                }
                if (subNode.style.display == "block") {
                    subNode.style.display = "none";
                    dom.path.id.pop();
                    dom.path.name.pop();
                } else {    //打开子类目
                    subNode.style.display = "block";
                    dom.path.id.push(this.getAttribute("data-index"))
                    dom.path.name.push(this.innerText);  //记录路径
                }
            } else {
                var data = dom.find(this);
                dom.chilMethod(data, this);
            }
        }, false);
    }
}

