/**
 * Created by 27654 on 2018/10/14.
 */
(function () {
    var items = {
        data: {
            table: document.getElementById('tb-list'),
        },
        editer: {
            init: function () {
                var by = document.getElementById('body');
                var d = new Dialog("信息完善", 'input_dg', by, "确认修改", function () {
                    var p = new prompt("数据加载中...");
                    setTimeout(function () {
                        p.flush("数据加载已完成！");
                    }, 1000);
                });
                var edts = items.data.table.getElementsByClassName('edt');
                for (var i = 0; i < edts.length; i++) {
                    edts[i].onclick = function () {
                        d.show();
                    }
                }
            },
        },
        delete: {
            init: function (id, context) {
                var row = null;
                var del = new Dialog("温馨提示", id, context, "确认删除", function () {
                    var p = row.parentNode.parentNode;
                    p.style.animation = "iui-fade-in .5s";
                    setTimeout(function () {
                        p.remove()
                    }, 500);
                });
                var dels = items.data.table.getElementsByClassName('del');
                for (var i = 0; i < dels.length; i++) {
                    dels[i].onclick = function () {
                        del.show();
                        row = this;
                    }
                }
            },
        },
        oNoFF: {
            init: function (id, context) {
                var id = null;
                var name;
                var dialog = new Dialog("温馨提示", id, context, name, function () {
                    //TODO

                });
                dialog.setting("width: 300px;");
                var onof = items.data.table.getElementsByClassName('on-off');
                for (var i = 0; i < onof.length; i++) {
                    onof[i].onclick = function () {
                        name = this.innerText;
                        if (name == "上架") {
                            dialog.setName("下架");
                        } else {
                            dialog.setName("上架");
                        }
                        dialog.show();
                    }
                }
            }
        },
        layDate: function () {
            lay('#version').html('-v' + laydate.v);
            //执行一个laydate实例
            laydate.render({
                elem: '#startDate' //指定元素
            });
            laydate.render({
                elem: '#endDate' //指定元素
            });
        },
        addwind: function () {
            //additem.html
            $("#add").click(function () {
                var iWidth = 900;                         //弹出窗口的宽度;
                var iHeight = 360;                        //弹出窗口的高度;
                //window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽
                var iTop = (window.screen.height - 30 - iHeight) / 2;       //获得窗口的垂直位置;
                var iLeft = (window.screen.width - 10 - iWidth) / 2;        //获得窗口的水平位置;
                window.open("additem.html", "发布宝贝", "_top", 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
            });
        }
    }
    items.layDate();
    //	items.addwind ();
    items.editer.init();
    items.delete.init('del_dg', '确认要删除该条记录吗？');
    items.oNoFF.init('on_off', '确认要更改吗');
})();
