package com.tenant.common.utils;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.*;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("rawtypes")
public class BaseUtils {
	
	/** 自定义随机码串(o,l没有加入) */
    private static final char[] r = {'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 't', 'n', '6', 'b', 'g', 'h'};
    /** 随机字符串 */
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();
    
	private static Logger logger = LoggerFactory.getLogger(BaseUtils.class);
	/**
	 * 随机生成6位数字验证码
	 */
	public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
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
	
	 /**
     * 根据ID生成13位随机码
     */
    public static String toSerialCode(int id) {
        char[] buf = new char[32];
        int charPos = 32;
        while((id / r.length) > 0) {
            int ind = id % r.length;
            buf[--charPos] = r[ind];
            id /= r.length;
        }
        buf[--charPos] = r[(id % r.length)];
        String str = new String(buf, charPos, (32 - charPos));
        if(str.length() < 13) {									// 不够长度的自动随机补全
            StringBuilder sb = new StringBuilder();
            Random rnd = new Random();
            sb.append(r[rnd.nextInt(r.length)]);
            for(int i=1; i < 13 - str.length(); i++) {
                sb.append(r[rnd.nextInt(r.length)]);
            }
            str+=sb.toString();
        }
        return str;
    }
    
    /**
     * 解析支付宝异步回调数据
     */
    public static Map<String, String> analysisNotify(Map map){
    	try {
    		Map<String,String> params = new HashMap<>();
			for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
	            String name = (String) iter.next();
	            String[] values = (String[]) map.get(name);
	            String valueStr = "";
	            for (int i = 0; i < values.length; i++) {
	                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
	            }
	            //乱码解决，这段代码在出现乱码时使用。
	            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
	            params.put(name, valueStr);
	        }
			return params;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
    }
    
    /**
     * 创建UUID
     */
    public static String createUUID() {
    	String uuid = UUID.randomUUID().toString();
    	return "A" + uuid.replaceAll("-", "").toUpperCase();
    }
    
    
    /**
     * 获取随机字符串
     */
    public static String generateNonceStr() {
        char[] nonceChars = new char[32];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
    
    /**
     * 生成签名.
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals("sign")) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
            {
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        return MD5Utils.createMd5(sb.toString()).toUpperCase();
    }
    
    /**
     * 将Map转换为XML格式的字符串
     */
    public static String mapToXml(Map<String, String> data) {
    	try {
    		Document document = new DocumentImpl();
        	Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key: data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
            try {
                writer.close();
            }
            catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
            }
            return output;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
    }
    
    /**
     * XML格式字符串转换为Map
     */
    public static Map<String, String> xmlToMap(String strXML) {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setXIncludeAware(false);
            documentBuilderFactory.setExpandEntityReferences(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception e) {
				logger.error(e.getMessage(), e);
            }
            return data;
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    /** 
     * 获取本机IP地址 
     */  
    public static String localIp(){  
        String ip = "127.0.0.1";  
		Enumeration allNetInterfaces;  
        try {  
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();              
            while (allNetInterfaces.hasMoreElements()) {  
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();  
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();  
                for (InterfaceAddress add : InterfaceAddress) {  
                    InetAddress Ip = add.getAddress();  
                    if (Ip != null && Ip instanceof Inet4Address) {  
                        ip = Ip.getHostAddress();  
                    }  
                }  
            }  
        } catch (SocketException e) {
        	e.printStackTrace();
        }  
        return ip;  
    }
    
    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
    	try {
    		if (!data.containsKey("sign") ) {
    	        return false;
    	    }
    		String sign = data.get("sign");
    		return generateSignature(data, key).equals(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
       return false;
    }
    
}
