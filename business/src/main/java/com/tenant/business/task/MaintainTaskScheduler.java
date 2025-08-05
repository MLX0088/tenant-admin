package com.tenant.business.task;

import com.tenant.business.Global;
import com.tenant.business.domain.*;
import com.tenant.business.service.*;
import com.tenant.business.utils.DrawClientUtils;
import com.tenant.business.utils.DrawUtils;
import com.tenant.common.core.domain.entity.SysUser;
import com.tenant.common.utils.StringUtils;
import com.tenant.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Component
public class MaintainTaskScheduler {

    @Autowired
    private TaskScheduler taskScheduler; // 注入线程池调度器

    @PostConstruct
    public void init() {

        Runnable taskPause = () -> {
            first();
        };
        Thread thread = new Thread(taskPause);
        thread.start();
    }

    @Transactional
    private void first() {

        // 定义时间段（19:00-20:00 和 23:55-次日07:00）
        LocalTime start1 = LocalTime.of(19, 0);  // 19:00
        LocalTime end1 = LocalTime.of(19, 30);    // 20:00

        LocalTime start2 = LocalTime.of(23, 55); // 23:55
        LocalTime end2 = LocalTime.of(7, 0);     // 07:00

        // 获取当前时间
        LocalTime now = LocalTime.now();

        // 检查是否在任一范围内
        boolean inFirstRange = isTimeInRange(now, start1, end1);
        boolean inSecondRange = isTimeInRange(now, start2, end2);

        if(inFirstRange){
            Global.jndIsMaintain = true;
        }else{
            Global.jndIsMaintain = false;
        }

        if(inSecondRange){
            Global.twIsMaintain = true;
        }else{
            Global.twIsMaintain = false;
        }

        Runnable task = () -> {
            first();     // 递归调度下一次执行
        };

        taskScheduler.schedule(task, new Date(new Date().getTime()+1000));
    }

    /**
     * 判断时间是否在范围内（支持跨天时间段）
     * @param target 待判断时间
     * @param start  开始时间
     * @param end    结束时间
     * @return 是否在范围内
     */
    public boolean isTimeInRange(LocalTime target, LocalTime start, LocalTime end) {
        if (end.isBefore(start)) {
            // 跨天逻辑（如 23:55-07:00）
            return !target.isBefore(start) || !target.isAfter(end);
        } else {
            // 同一天逻辑（如 19:00-20:00）
            return !target.isBefore(start) && !target.isAfter(end);
        }
    }
}
