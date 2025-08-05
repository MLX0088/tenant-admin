package com.tenant.quartz.task;

import com.tenant.business.service.ITbChatMessageService;
import com.tenant.business.service.ITbVipPayService;
import com.tenant.business.service.ITbWinReportService;
import com.tenant.business.service.impl.TbWinReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tenant.common.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时任务调度测试
 * 
 * @author tenant
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    ITbWinReportService tbWinReportService;
    @Autowired
    ITbVipPayService tbVipPayService;
    @Autowired
    ITbChatMessageService tbChatMessageService;
    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    public void genWinDataDaily()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,-1);
        String date = sdf.format(cal.getTime());
        int count = tbWinReportService.getDataCount(date);
        if(count > 0){
            System.out.println("取消执行："+date+" 已经存在数据");
        }
        tbWinReportService.genWinDataDaily(date);
    }

    public void updateVipGradeDaily()
    {
        tbVipPayService.updateVipGradeDaily();
    }

    public void updateStatusForYesterday()
    {
        tbChatMessageService.updateStatusForYesterday();
        tbChatMessageService.deleteTbChatMessageForDays();
    }
}
