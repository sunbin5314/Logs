$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    // 2.初始化Button的点击事件
    // var oButtonInit = new ButtonInit();
    // oButtonInit.Init();

});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            columns: [{                                             //表头
                checkbox: true
            },
                {field: 'rid', title: '编号', sortable: true},
                {field: 'name', title: '名称', sortable: true},
                {field: 'position', title: '位置', sortable: true},
                {field: 'type', title: '类型', sortable: true},
                {field: 'description', title: '描述', sortable: true}
            ],
            // data: mydata, //通过ajax返回的数据
            url: '/robotInfo/robotList',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            departmentname: $("#robot_position").val(),
            status: $("#robot_num").val()
        };
        return temp;
    };
    return oTableInit;
};

// var ButtonInit = function () {
//     var oInit = new Object();
//     var postdata = {};
//     oInit.Init = function () {
//         //初始化页面上面的按钮事件
//         //新增按钮事件
//         // $("#btn_add").click(function () {
//         //     $("#myModalLabel").text("新增");
//         //     $("#myModal").find(".form-control").val("");
//         //     $('#myModal').modal()
//         //
//         //     postdata.DEPARTMENT_ID = "";
//         // });
//         return oInit;
//     };
// }

//删除功能
$("#btn_delete").click(
    function () {
    var arrselections = $("#tb_departments").bootstrapTable('getSelections');
    if (arrselections.length <= 0) {
        // toastr.warning('请选择有效数据');
        alert("请选择有效数据");
        return;
    } else {
        if (confirm("确认要删除选择的数据吗？") == false) {
            return false;
        } else {
            console.log("arrselections rid name  =" + arrselections[0].rid + arrselections[0].name);
            var list = [];
            for (var i = 0; i < arrselections.length; i++) {  //遍历删除或封装成对象删除
                var rid = arrselections[i].rid;
                var name = arrselections[i].name;
                var type = arrselections[i].type;
                var position = arrselections[i].position;
                var description = arrselections[i].description;
                var data = {
                    rid: rid,
                    name: name,
                    type: type,
                    position: position,
                    description: description
                }
                console.log("data = " + data.description);
                list[i] = data;
            }
            console.log("list  =" + list.length + list[0].description);
            $.ajax({
                type: "POST",
                url: "/robotInfo/robotDel",
                data: JSON.stringify(list),//JSON.stringify()  json对象转字符串
                dataType: "text",
                contentType: "application/json",
                success: function (status) {
                    alert("删除成功");
                    $("#tb_departments").bootstrapTable('refresh');

                },
                error: function () {
                    alert("删除失败");
                },
                complete: function () {   //请求完成时执行的函数
                }
            });
        }
    }
});

//添加功能
$("#addRobot").click(
    function () {
        var robotname = $('#robotname');
        var position = $('#position');
        var type = $('#type');
        var description = $('#description');
        var data = {
            rid : 0,
            name: robotname.val(),
            position: position.val(),
            type: type.val(),
            description: description.val()
        }
        $.ajax({
            url: "/robotInfo/robotAdd",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json",
            success: function () {
                alert("添加成功");
                $("#tb_departments").bootstrapTable('refresh');
                $('#addModal').modal('hide');
            },
            error: function () {
                alert("添加失败" );
                $('#addModal').modal('hide');
            },
            complete: function () {
            }
        });
    });

//修改功能
$("#btn_edit").click(
    function () {
        var arrselections = $("#tb_departments").bootstrapTable('getSelections');
        console.log("arrselections = " + JSON.stringify(arrselections));
        if(arrselections.length > 1){
            console.log("arrselections1 = " + JSON.stringify(arrselections));
            alert("只能选择一行进行修改");
            return;
        }
        if(arrselections.length <= 0){
            console.log("arrselections0 = " + JSON.stringify(arrselections));
            alert("请选择有效数据");
            return;
        }
        var name = arrselections[0].name;
        var type = arrselections[0].type;
        var position = arrselections[0].position;
        var description = arrselections[0].description;
        $('#revname').val(name);
        $('#revtype').val(type);
        $('#revposition').val(position);
        $('#revdescription').val(description);
        $('#revModal').modal('show');
    });
$("#revRobot").click(
    function () {
        var arrselections = $("#tb_departments").bootstrapTable('getSelections');
        console.log("arrselections = " + JSON.stringify(arrselections));
        var data = {
            rid : arrselections[0].rid,
            name:$('#revname').val(),
            position:  $('#revposition').val(),
            type:  $('#revtype').val(),
            description: $('#revdescription').val()
        }
        $.ajax({
            url: "/robotInfo/robotMod",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json",
            success: function () {
                alert("修改成功");
                $("#tb_departments").bootstrapTable('refresh');
                $('#revModal').modal('hide');
            },
            error: function () {
                alert("修改失败" );
                $('#addModal').modal('hide');
            },
            complete: function () {
            }
        });
    });

















/*导航栏切换时间，初始加载tab页#home*/
// $(".nav li").click(function () {
//     var _a = $(this).find("a");
//     if($(_a.attr("href")).html()==""){   //判断页面是否已加载
//         $(_a.attr("href")).addClass("active").siblings().removeClass("active");
//         //tab页点击事件对应的页面元素置为"active"样式，其他兄弟页面移除"active"样式
//         $(_a.attr("href")).load(_a.attr("tt"));
//         //加载样式为"active"的页面
//     }
// });















//confirm窗口 优化
(function ($) {
    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                            modal.find('.cancel').click(function () {
                                callback(false);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () {
                    },
                    onShown: function (e) {
                    }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
})(jQuery);

