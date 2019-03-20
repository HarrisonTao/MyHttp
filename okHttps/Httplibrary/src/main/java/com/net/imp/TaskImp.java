package com.imp;

import android.content.Context;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public interface TaskImp {
    /**
     * 请求前
     * @param response
     */
    public void  onCreateTask(String response);

    /**
     * 请求中
     * @param s
     * @return
     */
    public String onTaskBackground(Map<String, String> s);

    /**
     * 请求后
     * @param str
     */
    public void onTaskPost(String str);

    /**
     * 错误出现回调
     */
    public void onError(Context context, String str);
}
