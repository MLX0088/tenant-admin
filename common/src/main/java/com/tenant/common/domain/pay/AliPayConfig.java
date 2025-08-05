package com.tenant.common.domain.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.StringUtils;
import com.tenant.common.core.redis.RedisCache;
import com.tenant.common.utils.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支付宝相关配置
 * @date 20220608
 * @author luanyu
 */
public class AliPayConfig {
	private static Logger logger = LoggerFactory.getLogger(AliPayConfig.class);
	// 网关地址
	public final static String ALIPAY_SERVER_URL = "https://openapi.alipay.com/gateway.do";
	// 请求格式，固定值json
	public final static String ALIPAY_FORMAT = "json";
	// 字符集
	public final static String ALIPAY_CHARSET = "utf-8";
	// 签名类型
	public final static String ALIPAY_SIGNTYPE = "RSA2";
	// 支付超时
	public final static String ALIPAY_TIMEOUT_EXPRESS = "30m";
	// 固定值
	public final static String ALIPAY_PRODUCT_CODE_A = "FAST_INSTANT_TRADE_PAY";
	// 固定值
	public final static String ALIPAY_PRODUCT_CODE_B = "QUICK_MSECURITY_PAY";
	// 固定值
	public final static String ALIPAY_NOTIFY_URL = "/nx/pay/aliPayCertNotify";
	// 固定值
	public final static String ALIPAY_RETURN_URL = "/nx/pay/aliPayCertReturn";
	public final static String trade_status1 = "TRADE_SUCCESS";
	public final static String trade_status2 = "TRADE_FINISHED";
	static String privateKey;
	/**
	 * 当面付私钥
	 */
	static String private_key;
	private static AlipayClient alipayClient;
	private static AlipayClient defaultAlipayClient;
	static RedisCache redisCache = SpringUtils.getBean(RedisCache.class);
	public static AlipayClient getAlipayClient() {
		String appid = redisCache.getCacheObject("sys_config:alipay_appid");
		if (alipayClient == null
				|| !privateKey.equals( redisCache.getCacheObject("sys_config:alipay_app_privateKey"))) {
			String certPath = redisCache.getCacheObject("sys_config:appCertPublicKey");
			String alipayPublicCertPath =  redisCache.getCacheObject("sys_config:alipayCertPublicKey");
			String rootCertPath = redisCache.getCacheObject("sys_config:alipayRootCert");

			privateKey = redisCache.getCacheObject("sys_config:alipay_app_privateKey");

			if (!StringUtils.isEmpty(appid) && !StringUtils.isEmpty(privateKey) && !StringUtils.isEmpty(certPath)
					&& !StringUtils.isEmpty(alipayPublicCertPath) && !StringUtils.isEmpty(rootCertPath)) {
				CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
				certAlipayRequest.setServerUrl(AliPayConfig.ALIPAY_SERVER_URL);
				certAlipayRequest.setFormat(AliPayConfig.ALIPAY_FORMAT);
				certAlipayRequest.setCharset(AliPayConfig.ALIPAY_CHARSET);
				certAlipayRequest.setSignType(AliPayConfig.ALIPAY_SIGNTYPE);
				certAlipayRequest.setAppId(appid);
				certAlipayRequest.setPrivateKey(privateKey);
				certAlipayRequest.setCertPath(certPath);
				certAlipayRequest.setAlipayPublicCertPath(alipayPublicCertPath);
				certAlipayRequest.setRootCertPath(rootCertPath);
				// 构造client
				try {
					alipayClient = new DefaultAlipayClient(certAlipayRequest);
				} catch (AlipayApiException e) {
					// TODO Auto-generated catch block
					logger.warn(e.getMessage(), e);
				}
			}
		}
		return alipayClient;

	}

	public static AlipayClient getDefaultAlipayClient() {

	/*	String private_key = (String) servletContext.getAttribute("private_key");
		if (AliPayConfig.private_key == null || !AliPayConfig.private_key.equals(private_key)) {
			String app_id = (String) servletContext.getAttribute("f2f_app_id");
			String alipay_public_key = (String) servletContext.getAttribute("alipay_public_key");
			AliPayConfig.private_key = private_key;
			defaultAlipayClient = new DefaultAlipayClient(ALIPAY_SERVER_URL, app_id, private_key, "json", "UTF-8",
					alipay_public_key, "RSA2");
		}*/

		return defaultAlipayClient;

	}

	public static void setAlipayClient(AlipayClient alipayClient) {
		AliPayConfig.alipayClient = alipayClient;
	}
}
