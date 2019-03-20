package com.net.imp;

import android.content.Context;


import com.net.util.HttpLog;
import com.net.util.JsonUtil;

import java.util.Map;

/**
 * 网络强求回调类
 */
public abstract  class ResponseTask implements com.imp.TaskImp {
    @Override
    public void onCreateTask(String str) {

    }

    @Override
    public String onTaskBackground( Map<String, String> map) {
        return null;
    }

    @Override
    public void onTaskPost(String response) {

    }



    @Override
    public void onError(Context context,String response) {
     //   String header = JsonUtil.getStringData(response, "header");
        String code = JsonUtil.getStringData(response, "state");
        if (code.equals("E445")){
            HttpLog.showDLog("当前网络不可用！");
        }else if(code.equals("E444")){
            HttpLog.showDLog( "当前网络异常!");
        }
    }
}
