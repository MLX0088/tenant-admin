package com.tenant.common.utils.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ID生成器工具类
 * 
 * @author tenant
 */
public class IdUtils
{
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }
    /**
     * 生成订单号
     */
    public static String createOrderId() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = format.format(new Date());
            Random random = new Random();
            random.setSeed(System.currentTimeMillis() + random.toString().hashCode());
            String ss = "0123456789";
            String s = "";
            for (int i = 0; i < 5; i++) {
                int n = random.nextInt(ss.length());
                char r = ss.charAt(n);
                s = s + r;
            }
            return (dateStr + s);
        } catch (Exception e) {
            return null;
        }
    }
}
