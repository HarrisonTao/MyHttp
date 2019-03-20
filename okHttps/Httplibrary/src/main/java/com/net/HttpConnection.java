package com.net;

import android.app.Activity;

import com.net.util.HttpLog;
import com.net.util.StatConfig;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * HttpConntion 封装了一系列对HTTP 请求的方法
 * 处理HTTP 提交
 * 依赖okHttp
 * okHttp 只是处理访问HTTP服务这个环节的任务，它不能在 Callback 回调中处理UI。只能通过Handle 来处理 。
 * 它可以和AsyncTask一起使用 使用同步方法 来处理   ，可以自由监控  请求前，请求中，请求后
 */
public class HttpConnection {
    /**
     * okHttp get异步处理
     *
     * @param url      访问URL
     * @param callback 回调
     */
    public static void httpGetAsyn(String url, Callback callback) {
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        //设置URL路径
        builder.url(url);

        Request re = builder.build();

        Call call = http.newCall(re);

        call.enqueue(callback);
    }


    /**
     * okHttp get同步处理  需要放到次线程开启
     *
     * @param url
     * @return 返回处理结果 null 有IO异常
     */
    public static String httpGet(Activity activity, String url, Map<String, String> map) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        StringBuffer bu = new StringBuffer();
        bu.append(url);
        if(map!=null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bu.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        builder.url(bu.toString());
        //输出请求参数
        HttpLog.showDLog(bu.toString());

        Request re = builder.build();

        Call call = http.newCall(re);
        try {
            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                //sResponse = "header:{code:" + response.code() + "}";
                sResponse = StatConfig.ERROR_CODE_E444;
            }
        } catch (Exception e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);
        return sResponse;
    }


    /**
     * okHttp get同步处理  需要放到次线程开启
     *
     * @param map       请求参数
     * @param headerMap 请求头部
     * @param url       请求路径
     * @return 返回处理结果 null 有IO异常
     */
    public static String httpGet(Activity activity, String url, Map<String, String> map, Map<String, String> headerMap) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        StringBuffer bu = new StringBuffer();
        bu.append(url);
        if (headerMap != null) {
            //设置请求头部
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (map != null) {
            //设置请求参数
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bu.append("&" + entry.getKey() + "=" + entry.getValue());
            }
        }
        builder.url(bu.toString());
        //输出请求参数
        HttpLog.showDLog(bu.toString());

        Request re = builder.build();

        Call call = http.newCall(re);
        try {
            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                //sResponse = "header:{code:" + response.code() + "}";
                sResponse = StatConfig.ERROR_CODE_E444;
            }
        } catch (Exception e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);
        return sResponse;
    }

    /**
     * post 同步
     *
     * @param url url
     * @param map post 请求参数
     * @return 返回结果集合
     */
    public static String httpPost(Activity activity, String url, Map<String, String> map) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();


        //设置请求参数
        FormBody.Builder formBody = new FormBody.Builder();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                formBody.addEncoded(entry.getKey(), entry.getValue());
                HttpLog.showDLog(entry.getKey() + "=" + entry.getValue());
            }
        }
        //设置请求路径
        builder.url(url);
        HttpLog.showDLog(url);
        //确认参数
        RequestBody body = formBody.build();

        //加入请求参数
        builder.post(body);
        try {
            Request re = builder.build();

            Call call = http.newCall(re);

            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                sResponse = StatConfig.ERROR_CODE_E444;
                // sResponse = "{header:{code:" + response.code() + "}";
            }
        } catch (IOException e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);

        return sResponse;
    }

    /**
     * post 同步
     *
     * @param url       url
     * @param map       post 请求参数
     * @param headerMap 请求头部
     * @return 返回结果集合
     */
    public static String httpPost(Activity activity, String url, Map<String, String> map, Map<String, String> headerMap) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        if (headerMap != null) {
            //设置请求头部
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //设置请求参数 5971
        FormBody.Builder formBody = new FormBody.Builder();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                formBody.addEncoded(entry.getKey(), entry.getValue());
                HttpLog.showDLog(entry.getKey() + "=" + entry.getValue());
            }
        }

        //设置请求路径
        builder.url(url);

        HttpLog.showDLog(url);

        //确认参数
        RequestBody body = formBody.build();

        //加入请求参数
        builder.post(body);
        try {
            Request re = builder.build();

            Call call = http.newCall(re);

            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                sResponse = StatConfig.ERROR_CODE_E444;
                // sResponse = "{header:{code:" + response.code() + "}";
            }
        } catch (IOException e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);

        return sResponse;
    }

    /**
     * post 同步
     *
     * @param url url
     * @param map post 请求参数
     * @return 返回结果集合
     */
    public static String httpImagePost(Activity activity, String url, Map<String, String> map) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        //设置请求参数
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBody.add(entry.getKey(), entry.getValue());
            //HttpLog.showDLog("请求KEY"+entry.getKey(),entry.getValue());
        }
        /*String data =getHttpData(map);
        //设置请求参数
        formBody.addEncoded("data", data);

        String header=getClientHeader(activity, map);
        //设置请求头
        formBody.addEncoded("header", header);*/

        //设置请求路径
        builder.url(url);

        HttpLog.showDLog(url);

        //确认参数
        RequestBody body = formBody.build();
        //加入请求参数
        builder.post(body);
        try {
            Request re = builder.build();

            Call call = http.newCall(re);

            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                sResponse = StatConfig.ERROR_CODE_E444;
                // sResponse = "{header:{code:" + response.code() + "}";
            }
        } catch (IOException e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);

        return sResponse;
    }


    /**
     * post 同步
     *
     * @param url url
     * @param map post 请求参数
     * @return 返回结果集合
     */
    public static Response httpPost(String url, Map<String, String> map) {
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        //设置请求参数
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBody.addEncoded(entry.getKey(), entry.getValue());
        }
        //设置请求路径
        builder.url(url);
        //确认参数
        RequestBody body = formBody.build();
        //加入请求参数
        builder.post(body);

        Request re = builder.build();

        Call call = http.newCall(re);
        try {
            response = call.execute();

        } catch (IOException e) {
            return response;
        }

        return response;
    }


    /**
     * 上传图片参数
     *
     * @param fType 上传文件类型 text/x-markdown; charset=utf-8
     * @param fMap  文件集合
     * @param map   参数集合
     * @param url   访问路径
     */
    public static String hpptMultipartFile(String fType, Map<String, File> fMap, Map<String, String> map, String url) {
        Response response = null;
        String sResponse = "";
        final MediaType MEDIA_TYPE_FILE = MediaType.parse(fType);

        OkHttpClient mOkHttpClient = new OkHttpClient();

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();

        multipartBody.setType(MultipartBody.FORM);
        //添加参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
            HttpLog.showDLog(entry.getKey() + "=" + entry.getValue());
        }
        //添加文件
        for (Map.Entry<String, File> entry : fMap.entrySet()) {

            multipartBody.addFormDataPart(entry.getKey(), entry.getValue().getName(), RequestBody.create(MEDIA_TYPE_FILE, entry.getValue()));
        }

        RequestBody requestBody = multipartBody.build();

        Request request = new Request.Builder()
                //  .header("Authorization", "Client-ID " + "...")
                .url(url).post(requestBody).build();

        Call call = mOkHttpClient.newCall(request);
        try {
            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                sResponse = StatConfig.ERROR_CODE_E444;
                // sResponse = "{header:{code:" + response.code() + "}";
            }
        } catch (IOException e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);
        return sResponse;
    }

    /**
     * post 异步处理
     *
     * @param url      路径
     * @param map      请求参数
     * @param callback 回调
     */
    public static void httpPostAsyn(String url, Map<String, String> map, Callback callback) {
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();

        Request.Builder builder = new Request.Builder();
        //设置请求参数
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBody.addEncoded(entry.getKey(), entry.getValue());
        }
        //设置请求路径
        builder.url(url);
        //确认参数
        RequestBody body = formBody.build();
        //加入请求参数
        builder.post(body);

        Request re = builder.build();

        Call call = http.newCall(re);

        call.enqueue(callback);


    }


    /**
     * 上传图片参数
     *
     * @param fType 上传文件类型 text/x-markdown; charset=utf-8
     * @param fMap  文件集合
     * @param map   参数集合
     * @param url   访问路径
     */
    public static void sendMultipartFile(String fType, Map<String, File> fMap, Map<String, String> map, String url) {

        final MediaType MEDIA_TYPE_FILE = MediaType.parse(fType);

        OkHttpClient mOkHttpClient = new OkHttpClient();

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();

        multipartBody.setType(MultipartBody.FORM);
        //添加参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
        }
        //添加文件
        for (Map.Entry<String, File> entry : fMap.entrySet()) {

            multipartBody.addFormDataPart(entry.getKey(), entry.getValue().getName(), RequestBody.create(MEDIA_TYPE_FILE, entry.getValue()));
        }

        RequestBody requestBody = multipartBody.build();

        Request request = new Request.Builder()
                //  .header("Authorization", "Client-ID " + "...")
                .url(url).post(requestBody).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.i("wangshu", response.body().string());
            }
        });
    }


    public static String httpFilePost(Activity activity, String url, Map<String, String> map) {
        String sResponse = "";
        Response response = null;
        //创建链接对象
        OkHttpClient http = new OkHttpClient();
        final MediaType MEDIA_TYPE_FILE = MediaType.parse("image/png");

        OkHttpClient mOkHttpClient = new OkHttpClient();

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();

        multipartBody.setType(MultipartBody.FORM);

        //添加参数
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multipartBody.addFormDataPart(entry.getKey(), entry.getValue());
        }

        //设置请求路径
        RequestBody requestBody = multipartBody.build();

        HttpLog.showDLog(url);

        try {
            Request re = new Request.Builder().url(url).post(requestBody).build();


            Call call = http.newCall(re);

            response = call.execute();
            if (response.code() == 200) {
                sResponse = response.body().string();
            } else {
                sResponse = StatConfig.ERROR_CODE_E444;
                // sResponse = "{header:{code:" + response.code() + "}";
            }
        } catch (IOException e) {
            sResponse = StatConfig.ERROR_CODE_E444;
        }
        HttpLog.showDLog(sResponse);

        return sResponse;
    }

    /**
     * 上传图片
     *
     * @param file 文件
     * @param url  URL
     */
    public static void postAsynFile(File file, String url) {
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        // File file = new File("/sdcard/wangshu.txt");
        Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.i("wangshu", response.body().string());
            }
        });


    }
}
