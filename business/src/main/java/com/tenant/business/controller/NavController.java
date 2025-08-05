package com.tenant.business.controller;

import com.tenant.business.domain.TbTenantConfig;
import com.tenant.business.domain.TbTenantNotice;
import com.tenant.business.service.ITbTenantConfigService;
import com.tenant.business.service.ITbTenantNoticeService;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 头像管理Controller
 * 
 * @author luanyu
 * @date 2025-05-03
 */
@Controller
@RequestMapping("/nav")
@ResponseBody
public class NavController extends BaseController
{
    private String prefix = "business/Avatar";

    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbTenantNoticeService tbTenantNoticeService;

    @GetMapping("/tenantNotice")
    @ResponseBody
    public AjaxResult tenantNotice(Long id)
    {
        TbTenantNotice temp = new TbTenantNotice();
        temp.setTenantId(id==null?0l:id);
        List<TbTenantNotice> list = tbTenantNoticeService.selectTbTenantNoticeList(temp);
        return success(list);
    }

    @GetMapping("/tenantConfig")
    @ResponseBody
    public AjaxResult tenantConfig(Long id)
    {
        TbTenantConfig config = tbTenantConfigService.selectTbTenantConfigByTenantId(id==null?0l:id);
        TbTenantConfig temp = new TbTenantConfig();
        temp.setClientUrl(config.getClientUrl());
        temp.setNavUrl(config.getNavUrl());
        return success(config);
    }
}
