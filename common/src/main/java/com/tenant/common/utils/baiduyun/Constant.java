package com.tenant.common.utils.baiduyun;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO 百度网盘基本常量信息
 */
@Configuration
@ConfigurationProperties(prefix = "baiduyun")  //通过set设置值，不能为静态方法
public class Constant {
   public static   String APP_ID;
    public static   String APP_NAME;
    public static   String APP_KEY;
    public static  String SECRET_KEY;
    public static   String SING_key;
    public static  String APP_PATH="/apps/"+APP_NAME+"/";

    //单位mb
    // 普通用户单个分片大小固定为4MB（文件大小如果小于4MB，无需切片，直接上传即可），单文件总大小上限为4G。
    //普通会员用户单个分片大小上限为16MB，单文件总大小上限为10G。
    //超级会员用户单个分片大小上限为32MB，单文件总大小上限为20G。
    public static   Integer UNIT=16;


    //获取授权码，需要扫码登陆
    public static   String GET_CODE_URL="https://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id="+APP_KEY+"&redirect_uri=oob&scope=basic,netdisk&display=tv&qrcode=1&force_login=1";

    //获取到的授权码
    public static    String CODE="389853bcabdb033c1bcf3e6b5a6dba61";

    //根据授权码换取token
    public static   String GET_TOKEN_BY_CODE="https://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code&code="+CODE+"&client_id="+APP_KEY+"&client_secret="+SECRET_KEY+"&redirect_uri=oob";

    //获取到的TOKEN
    public static   String RTOKEN="122.fec5f9d6dd1644c2c57c89cc510f7ec8.YBMpVZwjo9y5kSMFnVmSMJL9dj25T5X02gjLwV8.1J2sEw";
    public static  String ATOKEN="121.89d2a1b72b0709230d21042747612647.Ym02m6pQdpiPYDOcTWW_iQ5gFBzbvbrhedXtR6w.6V8rqw";
    public static   String refresh_token="122.af917d5e4cd0fe24b485cd3c5719a479.Ym9WJ6ezc5u-AbigTXfJmh30bp6DU3SYvm-kpxY.tUTc9g";


    //操作文件 copy, mover, rename, delete
    public static     String FILE_MANAGER_URL=" https://pan.baidu.com/rest/2.0/xpan/file";

    //预上传
    public static   String GET_READY_FILE_URL="https://pan.baidu.com/rest/2.0/xpan/file";

    //分片上传
    public static   String SLICING_UPLOAD_FILE_URL="https://d.pcs.baidu.com/rest/2.0/pcs/superfile2";

    //下载文件
    public static  String DOWN_LOUE_URL="https://pan.baidu.com/rest/2.0/xpan/multimedia";

    //文件搜索
    public static  String FILE_SEARCH="https://pan.baidu.com/rest/2.0/xpan/file?method=search";
    //刷新accesstoken
    public static   String REFRESH_ACCESS_TOKEN="https://openapi.baidu.com/oauth/2.0/token?grant_type=refresh_token&refresh_token="+refresh_token+"&client_id=yVxY7N7nkEvleTovLam3geLng0f7Gqeb&client_secret=XCnkW8D2uzogaRPmhsxGi1lvgwjyyPnG";
    //获取容量
    public static   String QUOTA="https://pan.baidu.com/api/quota?access_token=";
    //管理文件
    public static   String MANAGER="https://pan.baidu.com/rest/2.0/xpan/file";

      public  void setAppId(String appId) {
       APP_ID = appId;
      }

      public  void setAppName(String appName) {
       APP_NAME = appName;
      }

      public  void setAppKey(String appKey) {
       APP_KEY = appKey;
      }

      public  void setSecretKey(String secretKey) {
       SECRET_KEY = secretKey;
      }

      public  void setSING_key(String SING_key) {
       Constant.SING_key = SING_key;
      }
}

