package com.tenant.business.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.tenant.business.Global;
import com.tenant.business.domain.TbDrawRecord;
import com.tenant.common.utils.StringUtils;
import okhttp3.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.GZIPInputStream;

public class DrawClientUtils {
    public static TbDrawRecord fetchJnd(String no){
        return fetchJnd(Global.jndApiCurrentType,no);
    }
    public static TbDrawRecord fetchJndLast(){
        return fetchJnd(Global.jndApiCurrentType,null);
    }
    public static TbDrawRecord fetchJnd(int way,String no){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<TbDrawRecord> cs = new ExecutorCompletionService<>(executor);
        TbDrawRecord record = null;
        Future<TbDrawRecord> future1 = cs.submit(() -> getJndWay1(no));
        Future<TbDrawRecord> future2 = cs.submit(() -> getJndWay2(no));
        Future<TbDrawRecord> future3 = cs.submit(() -> getJndWay3(no));

        try {
            // 等待首个有效结果（非null）
            for (int i = 0; i < 2; i++) {
                Future<TbDrawRecord> completed = cs.poll(20, TimeUnit.SECONDS); // 总超时20秒
                if (completed == null) break; // 超时退出

                TbDrawRecord result = completed.get();
                if (result != null) {
                    record = result;
                    DrawUtils.calDrawWithResult(record);
                    break; // 获取有效结果后退出
                }
            }
            return record;
        } catch (Exception e) {
            // 其他异常
        }finally {
            future1.cancel(false); // false表示不发送中断信号
            future2.cancel(false);
            future3.cancel(false);
            executor.shutdown();
        }

        return record;
    }

    public static void main(String[] args) {

        getJndWay1("3318286");
        getJndWay2("3318286");
        getJndWay3("3318286");
    }
    public static TbDrawRecord getJndWay1(String no){
        TbDrawRecord record = null;
        try {
            String url = "http://api.jndhdhdushsjs28bsjsjsb6628dhdbdksn557.com/api/jnd_lotto";
            String jsonResponse = DrawClientUtils.sendGetRequest(url);
            if(StringUtils.isEmpty(jsonResponse)){
                return record;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            JsonNode partitionsNode = rootNode.get("partitions");
            for (JsonNode node : partitionsNode) {
                if(StringUtils.isEmpty(no) || no.equals(node.get("issue").toString())){
                    record = new TbDrawRecord();
                    record.setResultOne(Integer.parseInt(node.get("num1").toString()));
                    record.setResultTwo(Integer.parseInt(node.get("num2").toString()));
                    record.setResultThree(Integer.parseInt(node.get("num3").toString()));
                    record.setResultSum(Integer.parseInt(node.get("numTotal").toString()));
                    record.setPeriodNumber(node.get("issue").toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdf.parse(node.get("theoryTime").toString().replaceAll("\"","")));
                    cal.add(Calendar.SECOND,210);
                    record.setNextOpenTime(cal.getTime());
                    record.setNextPeriodNumber((Integer.parseInt(record.getPeriodNumber())+1)+"");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    public static TbDrawRecord getJndWay2(String no){
        TbDrawRecord record = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String url = "https://www.kuai28.com/portal/api/lottery_details?type=jnd28&date="+sdf.format(new Date());
            String jsonResponse = DrawClientUtils.sendGetRequest(url);
            if(StringUtils.isEmpty(jsonResponse)){
                return record;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode listNode = rootNode.path("data").path("list");  // 提取 data->list 节点
            for (JsonNode node : listNode) {
                if(StringUtils.isEmpty(no) || no.equals(node.get("full_expect").toString().replaceAll("\"",""))){
                    String[] resultCode = node.get("open_code").toString().replaceAll("\"","").split(",");
                    record = new TbDrawRecord();
                    record.setResultOne(Integer.parseInt(resultCode[0]));
                    record.setResultTwo(Integer.parseInt(resultCode[1]));
                    record.setResultThree(Integer.parseInt(resultCode[2]));
                    record.setResultSum(Integer.parseInt(node.get("sum").toString()));
                    record.setPeriodNumber(node.get("full_expect").toString().replaceAll("\"",""));

                    sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdf.parse(node.get("open_time").toString().replaceAll("\"","")));
                    cal.add(Calendar.SECOND,210);
                    record.setNextOpenTime(cal.getTime());
                    record.setNextPeriodNumber((Integer.parseInt(record.getPeriodNumber())+1)+"");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return record;
    }
    public static TbDrawRecord getJndWay3(String no){
        TbDrawRecord record = null;
        try {
            String url = "http://jnd28.pro.el984.shop/open/API/Web/1/MyOpens?lt=2";
            String jsonResponse = DrawClientUtils.sendGetRequest(url);
            if(StringUtils.isEmpty(jsonResponse)){
                return record;
            }
            jsonResponse = new String(jsonResponse.getBytes("ISO-8859-1"), "UTF-8");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode listNode = rootNode.path("data");  // 提取 data->list 节点
            for (JsonNode node : listNode) {
                if(StringUtils.isEmpty(no) || no.equals(node.get("section").toString().replaceAll("\"",""))){
                    boolean isOpen = node.get("isOpen").asBoolean();
                    if(isOpen){
                        String[] resultCode = node.get("middleCode").toString().replaceAll("\"","").split(",");
                        record = new TbDrawRecord();
                        record.setResultOne(Integer.parseInt(resultCode[0]));
                        record.setResultTwo(Integer.parseInt(resultCode[1]));
                        record.setResultThree(Integer.parseInt(resultCode[2]));
                        record.setResultSum(record.getResultOne()+record.getResultTwo()+record.getResultThree());
                        record.setPeriodNumber(node.get("section").toString().replaceAll("\"",""));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(Long.parseLong(node.get("openTime").toString().replaceAll("\"","")));
                        cal.add(Calendar.SECOND,210);
                        record.setNextOpenTime(cal.getTime());
                        record.setNextPeriodNumber((Integer.parseInt(record.getPeriodNumber())+1)+"");
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    private static final OkHttpClient sharedClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)   // 连接超时
            .readTimeout(20, TimeUnit.SECONDS)     // 读取响应超时
            .writeTimeout(10, TimeUnit.SECONDS)    // 上传数据超时
            .connectionPool(new ConnectionPool(50, 5, TimeUnit.MINUTES)) // 连接池
            .protocols(Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1)) // 协议优先级
            .build();
    public static String sendGetRequest(String url) {
        String result = null;
        try {
            java.security.Security.setProperty("networkaddress.cache.ttl", "300");
            long time1 = new Date().getTime();
            if(url.contains("?")){
                url += "&t="+time1;
            }else{
                url += "?t="+time1;
            }
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept-Encoding", "gzip") // 启用压缩
                    .build();

            try (Response response = sharedClient.newCall(request).execute()) {
                byte[] bytes = response.body().bytes();
                // 检查是否为 GZIP 流
                if (bytes.length >= 2 && bytes[0] == (byte) 0x1F && bytes[1] == (byte) 0x8B) {
                    try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(bytes))) {
                        bytes = ByteStreams.toByteArray(gzip); // 使用 Guava 或手动实现
                    }
                }
                result = new String(bytes, StandardCharsets.UTF_8) ;
                long time2 = new Date().getTime();
                System.out.println(new Date().toString()+" 网络请求耗时："+(time2-time1)/1000+" 地址："+url);
                return result;
            }catch (Exception e1){
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static TbDrawRecord fetchAm(String no){
        return fetchAm(Global.amApiCurrentType,no);
    }
    public static TbDrawRecord fetchAmLast(){
        return fetchAm(Global.amApiCurrentType,null);
    }
    public static TbDrawRecord fetchAm(int way,String no){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<TbDrawRecord> cs = new ExecutorCompletionService<>(executor);
        TbDrawRecord record = null;
        Future<TbDrawRecord> future1 = cs.submit(() -> getAmWay1(no));

        try {
            // 等待首个有效结果（非null）
            for (int i = 0; i < 1; i++) {
                Future<TbDrawRecord> completed = cs.poll(20, TimeUnit.SECONDS); // 总超时20秒
                if (completed == null) break; // 超时退出

                TbDrawRecord result = completed.get();
                if (result != null) {
                    record = result;
                    DrawUtils.calDrawWithResult(record);
                    break; // 获取有效结果后退出
                }
            }
            return record;
        } catch (Exception e) {
            // 其他异常
        }finally {
            future1.cancel(false); // false表示不发送中断信号
            executor.shutdown();
        }

        return record;
    }


    public static TbDrawRecord getAmWay1(String no){
        TbDrawRecord record = null;
        try {
            String url = "http://api.jndhdhdushsjs28bsjsjsb6628dhdbdksn557.com/api/mc_lotto";
            String jsonResponse = DrawClientUtils.sendGetRequest(url);
            if(StringUtils.isEmpty(jsonResponse)){
                return record;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            JsonNode partitionsNode = rootNode.get("partitions");
            for (JsonNode node : partitionsNode) {
                if(StringUtils.isEmpty(no) || no.equals(node.get("issue").toString())){
                    record = new TbDrawRecord();
                    record.setResultOne(Integer.parseInt(node.get("num1").toString()));
                    record.setResultTwo(Integer.parseInt(node.get("num2").toString()));
                    record.setResultThree(Integer.parseInt(node.get("num3").toString()));
                    record.setResultSum(Integer.parseInt(node.get("numTotal").toString()));
                    record.setPeriodNumber(node.get("issue").toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdf.parse(node.get("theoryTime").toString().replaceAll("\"","")));
                    cal.add(Calendar.SECOND,210);
                    record.setNextOpenTime(cal.getTime());
                    record.setNextPeriodNumber((Integer.parseInt(record.getPeriodNumber())+1)+"");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return record;
    }


    public static TbDrawRecord fetchTw(String no){
        return fetchTw(Global.twApiCurrentType,no);
    }
    public static TbDrawRecord fetchTwLast(){
        return fetchTw(Global.twApiCurrentType,null);
    }
    public static TbDrawRecord fetchTw(int way,String no){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletionService<TbDrawRecord> cs = new ExecutorCompletionService<>(executor);
        TbDrawRecord record = null;
        Future<TbDrawRecord> future1 = cs.submit(() -> getTwWay1(no));

        try {
            // 等待首个有效结果（非null）
            for (int i = 0; i < 1; i++) {
                Future<TbDrawRecord> completed = cs.poll(20, TimeUnit.SECONDS); // 总超时20秒
                if (completed == null) break; // 超时退出

                TbDrawRecord result = completed.get();
                if (result != null) {
                    record = result;
                    DrawUtils.calDrawWithResult(record);
                    break; // 获取有效结果后退出
                }
            }
            return record;
        } catch (Exception e) {
            // 其他异常
        }finally {
            future1.cancel(false); // false表示不发送中断信号
            executor.shutdown();
        }

        return record;
    }


    public static TbDrawRecord getTwWay1(String no){
        TbDrawRecord record = null;
        try {
            String url = "http://api.jndhdhdushsjs28bsjsjsb6628dhdbdksn557.com/api/tw2_lotto";
            String jsonResponse = DrawClientUtils.sendGetRequest(url);
            if(StringUtils.isEmpty(jsonResponse)){
                return record;
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            JsonNode partitionsNode = rootNode.get("partitions");
            for (JsonNode node : partitionsNode) {
                if(StringUtils.isEmpty(no) || no.equals(node.get("issue").toString())){
                    record = new TbDrawRecord();
                    record.setResultOne(Integer.parseInt(node.get("num1").toString()));
                    record.setResultTwo(Integer.parseInt(node.get("num2").toString()));
                    record.setResultThree(Integer.parseInt(node.get("num3").toString()));
                    record.setResultSum(Integer.parseInt(node.get("numTotal").toString()));
                    record.setPeriodNumber(node.get("issue").toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdf.parse(node.get("theoryTime").toString().replaceAll("\"","")));
                    cal.add(Calendar.SECOND,300);
                    record.setNextOpenTime(cal.getTime());
                    record.setNextPeriodNumber((Integer.parseInt(record.getPeriodNumber())+1)+"");
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return record;
    }
}
