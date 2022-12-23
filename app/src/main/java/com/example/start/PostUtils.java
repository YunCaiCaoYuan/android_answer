package com.example.start;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;

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

    public static String Post(String url, byte[] data)
    {

        String msg = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);

            //这里可以写一些请求头的东东...
            conn.addRequestProperty("Content-Type", "application/json");
            //获取输出流
            OutputStream out = conn.getOutputStream();
            out.write(data);
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

    public static String Get(String url) {
        HttpURLConnection conn = null;
        try {
            //利用String url构建URL对象
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                Log.d( "GetQues: ", response);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

    private static String getStringFromInputStream(InputStream is) {
        String state = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            is.close();
            //把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
            state = os.toString();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }


}