package com.tenant.common.utils.baiduyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tenant.common.constant.Constants;
import com.tenant.common.core.redis.RedisCache;
import com.tenant.common.utils.StringUtils;
import com.tenant.common.utils.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 文件上传到百度云
 * @author 栾钰
 */
@Service
public class UploadService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected RedisCache redisCache;
    /**
     *
     * @param file  文件
     * @param businessId  业务id,用此来区分文件夹
     * @return 创建成功的文件json
     */
    public String upload(File file,String businessId) {
        try {
            String access_token = redisCache.getCacheObject(Constants.SYS_CONFIG_KEY+"access_token_baidu");
            String quota = HttpUtils.doget(Constant.QUOTA+access_token);
            logger.info("当前的网盘容量信息为：{}",quota);
            Integer  errno = (Integer) JSON.parseObject(quota).get("errno");
            String refresh_token = redisCache.getCacheObject(Constants.SYS_CONFIG_KEY+"refresh_token_baidu");
            if (errno == 111|| access_token ==null||errno == -6) {
                logger.info("accesstoken到期重新获取");
                Constant.ATOKEN = access_token;
                Constant.refresh_token = refresh_token;
                String refresh = HttpUtils.doget(Constant.REFRESH_ACCESS_TOKEN);
                JSONObject jsonObject = JSON.parseObject(refresh);
                access_token =(String) jsonObject.get("access_token");
                refresh_token =(String) jsonObject.get("refresh_token");
                logger.info("获取到新的access_token：{},refresh_token:{}",access_token,refresh_token);
                Constant.refresh_token=refresh_token;
                Constant.ATOKEN=access_token;
                redisCache.setCacheObject(Constants.SYS_CONFIG_KEY+"refresh_token_baidu",refresh_token);
                redisCache.setCacheObject(Constants.SYS_CONFIG_KEY+"access_token_baidu",access_token,new BigDecimal(jsonObject.getLong("expires_in")).intValue(), TimeUnit.SECONDS);
            }else {

                Constant.refresh_token=refresh_token;
                Constant.ATOKEN=access_token;
            }
            String create = FileUtils.save(file.getParent(), file.getName().replaceAll(" ",""), "/apps/" + Constant.APP_NAME + "/" + businessId);
            return create;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 获取文件信息并保存在redis
     * @param fs_id
     * @return
     */
    public   JSONObject saveToRedis(String fs_id) {
        String url = Constant.DOWN_LOUE_URL + "?method=filemetas&access_token=" + redisCache.getCacheObject(Constants.SYS_CONFIG_KEY+"access_token_baidu")+ "&fsids=[" + fs_id + "]&dlink=1&thumb=1";
        String s = null;
        try {
            s = HttpUtils.doget(url);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        JSONObject sJsonObject = JSON.parseObject(s);
        JSONArray jsonArray = sJsonObject.getJSONArray("list");
        JSONObject jsonObjectClient=null;
        if (!jsonArray.isEmpty()) {
            jsonObjectClient = jsonArray.getJSONObject(0);
        }
        redisCache.setCacheObject(fs_id,jsonObjectClient,8*60*60,TimeUnit.SECONDS);
        return jsonObjectClient;
    }

    /**
     * 删除文件
     * @param path
     */
    public  void delete(List<String> path){
         HashMap<String, String> map = new HashMap<>();
         JSONArray objects = new JSONArray();
         for (String s : path) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("path",s);
             objects.add(jsonObject);
         }
         map.put("filelist",objects.toJSONString());
        if (objects.size()>0) {
            logger.info("开始删除百度云文件：{}",objects);
            String baidu = HttpUtils.postForm(Constant.MANAGER + "?method=filemanager&opera=delete&async=0&access_token=" + redisCache.getCacheObject(Constants.SYS_CONFIG_KEY+"access_token_baidu"), map);
            Integer  errno = (Integer) JSON.parseObject(baidu).get("errno");
            if (errno == 0) {
                logger.info("百度云文件：{}删除成功",objects.toJSONString());
            }
        }
     }
     public   JSONArray fileList (String dir, Integer start, Integer limit){

         String url = Constant.MANAGER + "?method=list&access_token=" + redisCache.getCacheObject(Constants.SYS_CONFIG_KEY+"access_token_baidu") + "&start=" + start + "&limit=" + limit;
         if (!StringUtils.isEmpty(dir)) {
             url+= "&dir=" + dir;
         }
         String doget = HttpUtils.doget(url);
         Integer  errno = (Integer) JSON.parseObject(doget).get("errno");
         JSONArray jSONArray=new JSONArray();
         if (errno == 0) {
              jSONArray = (JSONArray)JSON.parseObject(doget).get("list");
         }

         return jSONArray;
     }
}
