package com.tenant.business.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.ShiroUtils;
import com.tenant.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.tenant.common.annotation.Log;
import com.tenant.common.enums.BusinessType;
import com.tenant.common.core.controller.BaseController;
import com.tenant.common.core.domain.AjaxResult;
import com.tenant.common.utils.poi.ExcelUtil;
import com.tenant.common.core.page.TableDataInfo;

/**
 * 消息Controller
 * 
 * @author luanyu
 * @date 2025-05-24
 */
@Controller
@RequestMapping("/business/ChatMessage")
public class TbChatMessageController extends BaseController
{
    private String prefix = "business/ChatMessage";

    @Autowired
    private ITbChatMessageService tbChatMessageService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ITbTenantConfigService tbTenantConfigService;
    @Autowired
    private ITbRoomConfigService tbRoomConfigService;
    @Autowired
    private ITbDrawRecordService tbDrawRecordService;
    @Autowired
    private ITbScoreRecordService tbScoreRecordService;
    @Autowired
    private ISysUserService sysUserService;

    @RequiresPermissions("business:ChatMessage:view")
    @GetMapping()
    public String ChatMessage()
    {
        return prefix + "/ChatMessage";
    }


    @GetMapping("/CustomerChat")
    public String CustomerChat()
    {
        return prefix + "/CustomerChat";
    }
    /**
     * 查询消息列表
     */
    @RequiresPermissions("business:ChatMessage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbChatMessage tbChatMessage)
    {
        startPage();
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        return getDataTable(list);
    }

    @GetMapping("/contactList")
    @ResponseBody
    public AjaxResult contactList()
    {
        List<TbChatMessage> list = tbChatMessageService.selectContactList(getSelectDeptId());
        return success(list);
    }

    @GetMapping("/v1/listForGroup")
    @ResponseBody
    public TableDataInfo listForGroup(TbChatMessage tbChatMessage)
    {
        startPage();
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        return getDataTable(list);
    }

    @GetMapping("/v1/listForPrivate")
    @ResponseBody
    public TableDataInfo listForPrivate(TbChatMessage tbChatMessage)
    {
        startPage();
        List<Long> paramList = new ArrayList<>();
        paramList.add(tbChatMessage.getReceiverId());
        if(tbChatMessage.getSenderId() == null){
            tbChatMessage.setSenderId(ShiroUtils.getUserId());
        }
        paramList.add(tbChatMessage.getSenderId());
        tbChatMessage.setPrivateIds(paramList);
        tbChatMessage.setSenderId(null);
        tbChatMessage.setReceiverId(null);
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        return getDataTable(list);
    }

    @PostMapping("/listForPrivate")
    @ResponseBody
    public TableDataInfo listForPrivate2(TbChatMessage tbChatMessage)
    {
        startPage();
        List<Long> paramList = new ArrayList<>();
        paramList.add(tbChatMessage.getReceiverId());
        paramList.add(tbChatMessage.getSenderId());
        tbChatMessage.setPrivateIds(paramList);
        tbChatMessage.setSenderId(null);
        tbChatMessage.setReceiverId(null);
        tbChatMessage.setTenantId(getTenantId());
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        return getDataTable(list);
    }

    @PostMapping("/updateReadStatus")
    @ResponseBody
    public AjaxResult updateReadStatus(TbChatMessage tbChatMessage)
    {
        if(tbChatMessage.getId()==null & (tbChatMessage.getReceiverId()==null || tbChatMessage.getSenderId()==null)){
            return error("缺少必要参数");
        }
        tbChatMessage.setTenantId(getTenantId());
        int count = tbChatMessageService.updateReadStatus(tbChatMessage);
        return toAjax(count);
    }

    @PostMapping("/v1/updateReadStatus")
    @ResponseBody
    public AjaxResult updateReadStatusForApp(@RequestBody TbChatMessage tbChatMessage)
    {
        if(tbChatMessage.getId()==null){
            tbChatMessage.setSenderId(0l);
            tbChatMessage.setReceiverId(ShiroUtils.getUserId());
        }
        int count = tbChatMessageService.updateReadStatus(tbChatMessage);
        return toAjax(count);
    }

    @GetMapping("/v1/unreadCount")
    @ResponseBody
    public AjaxResult unreadCountForApp()
    {
        TbChatMessage tbChatMessage = new TbChatMessage();
        tbChatMessage.setReceiverId(ShiroUtils.getUserId());
        int count = tbChatMessageService.selectUnreadCountByReceiverId(tbChatMessage);
        return success(count);
    }

    @GetMapping("unreadCount")
    @ResponseBody
    public AjaxResult unreadCount()
    {
        TbChatMessage tbChatMessage = new TbChatMessage();
        tbChatMessage.setReceiverId(0l);
        tbChatMessage.setTenantId(getTenantId());
        int count = tbChatMessageService.selectUnreadCountByReceiverId(tbChatMessage);
        return success(count);
    }

    /**
     * 导出消息列表
     */
    @RequiresPermissions("business:ChatMessage:export")
    @Log(title = "消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbChatMessage tbChatMessage)
    {
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        ExcelUtil<TbChatMessage> util = new ExcelUtil<TbChatMessage>(TbChatMessage.class);
        return util.exportExcel(list, "消息数据");
    }

    /**
     * 新增消息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存消息
     */
    @RequiresPermissions("business:ChatMessage:add")
    @Log(title = "消息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbChatMessage tbChatMessage)
    {
        return toAjax(tbChatMessageService.insertTbChatMessage(tbChatMessage));
    }

    /**
     * 修改消息
     */
    @RequiresPermissions("business:ChatMessage:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbChatMessage tbChatMessage = tbChatMessageService.selectTbChatMessageById(id);
        mmap.put("tbChatMessage", tbChatMessage);
        return prefix + "/edit";
    }
    /**
     * 详情消息
     */
    @RequiresPermissions("business:ChatMessage:view")
    @GetMapping("/info/{id}")
    @ResponseBody
    public AjaxResult info(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbChatMessage tbChatMessage = tbChatMessageService.selectTbChatMessageById(id);
        return success(tbChatMessage);
    }

    /**
     * 修改保存消息
     */
    @RequiresPermissions("business:ChatMessage:edit")
    @Log(title = "消息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbChatMessage tbChatMessage)
    {
        return toAjax(tbChatMessageService.updateTbChatMessage(tbChatMessage));
    }

    @PostMapping("/v1/cancelAllScore")
    @ResponseBody
    public AjaxResult cancelAllScore(@RequestBody TbChatMessage tbChatMessage)
    {
        TbDrawRecord draw = tbDrawRecordService.selectTbDrawRecordById(tbChatMessage.getDrawId());
        if(draw.getExpectOpenTime()!=null && draw.getExpectOpenTime().getTime() < (new Date().getTime())+(1000*30)){
            return error("撤单失败，该期已封盘或已开奖");
        }
        tbChatMessage.setSenderId(ShiroUtils.getUserId());
        tbChatMessage.setSuccess(1);
        List<TbChatMessage> list = tbChatMessageService.selectTbChatMessageList(tbChatMessage);
        if(list == null || list.size()==0){
            return error("撤单失败，该期没有投注");
        }

        List<Long> ids = new ArrayList<>();
        for (TbChatMessage message: list) {
            if(message.getSuccess()!=1){
                continue;
            }
            SysUser user = sysUserService.selectUserById(ShiroUtils.getUserId());

            TbScoreRecord score = new TbScoreRecord();
            score.setId(message.getScoreId());
            score.setStatus("3");
            tbScoreRecordService.updateTbScoreRecord(score);
            score = tbScoreRecordService.selectTbScoreRecordById(score.getId());

            message.setSuccess(3);
            tbChatMessageService.updateTbChatMessage(message);

            SysUser userTemp = new SysUser(ShiroUtils.getUserId());
            userTemp.setScore(user.getScore().add(score.getScore()));
            sysUserService.updateUserInfo(userTemp);
            ids.add(message.getId());
        }

        if(ids == null || ids.size()==0){
            return error("撤单失败，该期没有投注");
        }

        return success(ids);
    }

    @PostMapping("/v1/cancelScore")
    @ResponseBody
    public AjaxResult cancelScore(@RequestBody TbChatMessage tbChatMessage)
    {
        tbChatMessage = tbChatMessageService.selectTbChatMessageById(tbChatMessage.getId());
        TbDrawRecord draw = tbDrawRecordService.selectTbDrawRecordById(tbChatMessage.getDrawId());
        SysUser user = sysUserService.selectUserById(ShiroUtils.getUserId());
        if(draw.getExpectOpenTime()!=null && draw.getExpectOpenTime().getTime() < (new Date().getTime())+(1000*30)){
            return error("撤单失败，该期已封盘或已开奖");
        }

        TbScoreRecord score = new TbScoreRecord();
        score.setId(tbChatMessage.getScoreId());
        score.setStatus("3");
        tbScoreRecordService.updateTbScoreRecord(score);
        score = tbScoreRecordService.selectTbScoreRecordById(score.getId());

        tbChatMessage.setSuccess(3);
        tbChatMessageService.updateTbChatMessage(tbChatMessage);

        SysUser userTemp = new SysUser(ShiroUtils.getUserId());
        userTemp.setScore(user.getScore().add(score.getScore()));
        sysUserService.updateUserInfo(userTemp);

        return success();
    }

    /**
     * 删除消息
     */
    @RequiresPermissions("business:ChatMessage:remove")
    @Log(title = "消息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbChatMessageService.deleteTbChatMessageByIds(ids));
    }
}
