/**
 * 初始化客户端管理详情对话框
 */
var ClientInfoInfoDlg = {
    clientInfoInfoData : {},
    validateFields: {
        username: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                },
                stringLength: {//检测长度
                    min: 1,
                    max: 30,
                    message: '长度必须在1-20之间'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
                },
                regexp: {//正则验证
                    regexp: /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?|(1[34578]\d{9})$/,
                    message: '请输入正确的固话或手机号码'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ClientInfoInfoDlg.clearData = function() {
    this.clientInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientInfoInfoDlg.set = function(key, val) {
    this.clientInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClientInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClientInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ClientInfo.layerIndex);
}

/**
 * 收集数据
 */
ClientInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('username')
    .set('organization')
    .set('email')
    .set('phone')
    .set('computerCode')
    .set('authCode')
    .set('recomUsername')
    .set('recomPhone');
}

/**
 * 验证数据是否为空
 */
ClientInfoInfoDlg.validate = function () {
    var $clientInfoForm = $('#clientInfoForm');
    $clientInfoForm.data("bootstrapValidator").resetForm();
    $clientInfoForm.bootstrapValidator('validate');
    return $clientInfoForm.data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ClientInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClientInfo.table.refresh();
        ClientInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClientInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/clientInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClientInfo.table.refresh();
        ClientInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.clientInfoInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("clientInfoForm", ClientInfoInfoDlg.validateFields);
});
