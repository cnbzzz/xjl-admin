package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.OperationLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.ClientInfo;
import com.stylefeng.guns.modular.system.service.IClientInfoService;

import java.io.Serializable;

/**
 * 客户端管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-19 18:38:06
 */
@Controller
@RequestMapping("/clientInfo")
public class ClientInfoController extends BaseController {

    private String PREFIX = "/system/clientInfo/";

    @Autowired
    private IClientInfoService clientInfoService;

    /**
     * 跳转到客户端管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clientInfo.html";
    }

    /**
     * 跳转到添加客户端管理
     */
    @RequestMapping("/clientInfo_add")
    public String clientInfoAdd() {
        return PREFIX + "clientInfo_add.html";
    }

    /**
     * 跳转到修改客户端管理
     */
    @RequestMapping("/clientInfo_update/{clientInfoId}")
    public String clientInfoUpdate(@PathVariable Long clientInfoId, Model model) {
        ClientInfo clientInfo = clientInfoService.selectById(clientInfoId);
        model.addAttribute("item",clientInfo);
        LogObjectHolder.me().set(clientInfo);
        return PREFIX + "clientInfo_edit.html";
    }

    /**
     * 获取客户端管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<ClientInfo> wrapper = null;
        if(ToolUtil.isNotEmpty(condition)){
            wrapper = new EntityWrapper<ClientInfo>().like("phone", condition);
        }
        Page<ClientInfo> page = new PageFactory<ClientInfo>().defaultPage();
        return packForBT(clientInfoService.selectPage(page, wrapper));
    }

    /**
     * 新增客户端管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClientInfo clientInfo) {
        clientInfoService.insert(clientInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户端管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long clientInfoId) {
        clientInfoService.deleteById(clientInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户端管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClientInfo clientInfo) {
        clientInfoService.updateById(clientInfo);
        return SUCCESS_TIP;
    }

    /**
     * 客户端管理详情
     */
    @RequestMapping(value = "/detail/{clientInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("clientInfoId") Long clientInfoId) {
        return clientInfoService.selectById(clientInfoId);
    }
}
