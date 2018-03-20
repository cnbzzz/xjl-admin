/**
 * 客户端管理管理初始化
 */
var ClientInfo = {
    id: "ClientInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClientInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle'},
        {title: '单位', field: 'organization', visible: true, align: 'center', valign: 'middle'},
        {title: '邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
        {
            title: '机器码', field: 'computerCode', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                if (value && value.length > 16) {
                    value = value.substring(0, 16).concat("...")
                }
                return value;
            }
        },
        {
            title: '注册码', field: 'authCode', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index) {
                if (value && value.length > 16) {
                    value = value.substring(0, 16).concat("...")
                }
                return value;
            }
        },
        {title: '推荐人帐号', field: 'recomUsername', visible: false, align: 'center', valign: 'middle'},
        {title: '推荐人电话', field: 'recomPhone', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ClientInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ClientInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加客户端管理
 */
ClientInfo.openAddClientInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户端管理',
        area: ['900px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/clientInfo/clientInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户端管理详情
 */
ClientInfo.openClientInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户端管理详情',
            area: ['900px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/clientInfo/clientInfo_update/' + ClientInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户端管理
 */
ClientInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/clientInfo/delete", function (data) {
            Feng.success("删除成功!");
            ClientInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("clientInfoId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询客户端管理列表
 */
ClientInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ClientInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClientInfo.initColumn();
    var table = new BSTable(ClientInfo.id, "/clientInfo/list", defaultColunms);
    table.setPaginationType("server");
    ClientInfo.table = table.init();
});
