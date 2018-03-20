package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.modular.system.model.ClientInfo;
import com.stylefeng.guns.modular.system.service.IClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This file is part of xjl-admin Project
 * Created by bzzz (bzzz@space-walker.cn) on 2018/3/19 19:04
 * Copyright (c) 2018 www.space-walker.cn
 */

@RestController
@RequestMapping("/client")
public class ClientCtrl extends BaseController {

    @Autowired
    private IClientInfoService clientInfoService;

    /**
     * 新增客户端管理
     */
    @RequestMapping(value = "/add")
    public Object add(@RequestBody ClientInfo clientInfo) {
        try{
            clientInfoService.insert(clientInfo);
        } catch (Exception e){
            return new ErrorTip(500, "添加失败，用户名、电话必填");
        }
        return SUCCESS_TIP;
    }

}
