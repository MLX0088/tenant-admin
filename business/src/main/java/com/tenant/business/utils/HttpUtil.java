package com.tenant.business.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class HttpUtil {

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * GET请求
     * @param url 请求地址
     * @param params 请求参数
     * @return JSON对象
     */
    public static JSONObject get(String url, Map<String, String> params) {
        // 构建带参数的URL
        StringBuilder urlBuilder = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            params.forEach((k, v) ->
                    urlBuilder.append(k).append("=").append(v).append("&"));
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        // 发送请求并返回JSON
        String response = restTemplate.getForObject(urlBuilder.toString(), String.class);
        return JSONObject.parseObject(response);
    }

    /**
     * POST表单请求
     * @param url 请求地址
     * @param formParams 表单参数
     * @return JSON对象
     */
    public static JSONObject postForm(String url, Map<String, String> formParams) {
        // 构建表单参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (formParams != null) {
            formParams.forEach(params::add);
        }

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 发送请求
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(params, headers);
        String response = restTemplate.postForObject(url, request, String.class);
        return JSONObject.parseObject(response);
    }

    /**
     * POST JSON请求
     * @param url 请求地址
     * @param jsonParams JSON参数
     * @return JSON对象
     */
    public static JSONObject postJson(String url, Map<String, Object> jsonParams) {
        // 设置JSON请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建JSON请求体
        String jsonBody = JSONObject.toJSONString(jsonParams);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        // 发送请求
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        return JSONObject.parseObject(response.getBody());
    }
}