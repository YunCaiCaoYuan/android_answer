package com.example.start;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostUtils {
    public static String LOGIN_URL = "http://192.168.0.17:8080/commit_answer";

    public static String CommitByPost(Integer titleId, Integer answerOpt)
    {

        String msg = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(LOGIN_URL).openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);

            //我们请求的数据:
            //String data = "passwd="+ URLEncoder.encode(String.valueOf(titleId), "UTF-8")+"&number="+ URLEncoder.encode(String.valueOf(answerOpt), "UTF-8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title_id", titleId);
            jsonObject.put("answer_opt", answerOpt);

            //这里可以写一些请求头的东东...
            conn.addRequestProperty("Content-Type", "application/json");
            //获取输出流
            OutputStream out = conn.getOutputStream();
            out.write(jsonObject.toString().getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                msg = new String(message.toByteArray());
                return msg;
            }
        }catch(Exception e){e.printStackTrace();}
        return msg;
    }
}