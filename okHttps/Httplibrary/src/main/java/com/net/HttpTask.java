package com.net;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.net.httplibrary.R;
import com.net.imp.ResponseTask;
import com.net.util.JsonUtil;
import com.net.util.StatConfig;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class HttpTask extends AsyncTask < Map<String, String>, String,String>{


    private ResponseTask imp;
    private Activity context;

 //   public AlertDialog dialog_sell = null;
    private Dialog progressDialog;

    private  boolean isNetworkAvailable=true,isOpenDialg=false;


    public HttpTask(Activity context){
        this.context=context;

        //判断网络是否可用
        this.isNetworkAvailable=isNetworkAvailable(context);

    }

    public HttpTask(Activity context,boolean isOpenDialg){
        this.context=context;

        //判断网络是否可用
        this.isNetworkAvailable=isNetworkAvailable(context);
        this.isOpenDialg=isOpenDialg;

    }

    public void executeTask(Map<String, String> map, ResponseTask imp){
        this.imp=imp;

            //dialogwait(context, isOpenDialg);

        this.execute(map);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //判断是否有网络
        if(isNetworkAvailable) {
            imp.onCreateTask("");
        }else{
            if(isOpenDialg) {
             //   dialogwait(context, false);
            }
            imp.onError(context,StatConfig.ERROR_CODE_E445);
          //  MyToast.showToast(context, "当前网络不可用！");
        }

    }

    @Override
    protected String doInBackground( Map<String, String>... params) {
        if(isNetworkAvailable) {
            return imp.onTaskBackground( params[0]);
        }else{
            return StatConfig.ERROR_CODE_E445;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(isOpenDialg){
            //dialogwait(context, false);
        }
        if(isNetworkAvailable) {
                //获取返回数据状态
                String code = JsonUtil.getStringData(response, "state");
       /*         if (code.equals("E9965") || code.equals("E9998") || code.equals("E9966")) {

                    imp.onError(context,StatConfig.ERROR_CODE_E446);

                    MyToast.showToast(context, "登录超时,请重新登录！");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);

                } else*/
                if (code.equals("E444")||code.equals("E445")) {
                   imp.onError(context, response);
                } else {
                    imp.onTaskPost(response);
                }
        }


    }
    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public boolean isNetworkAvailable(Context activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {

                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 这里创建读取网络时耗时ui
     * @param activity
     * @param statics
     */
  /*  public void dialogwait(Context activity ,boolean statics) {
        if (statics) {
            if(progressDialog!=null && progressDialog.isShowing()){
                return;
            }
            try {
                progressDialog = new Dialog(context, R.style.dialog);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
                progressDialog.setContentView(view);
                ImageView progress = (ImageView) view.findViewById(R.id.progress);
                Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.progress);
                LinearInterpolator lin = new LinearInterpolator();
                operatingAnim.setInterpolator(lin);
                progress.startAnimation(operatingAnim);
                // progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
            }
        }else{
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
        }
    }*/

   /* public void dialogwait(Context activity ,int a) {
        if (a == 1) {
            AlertDialog.Builder builder = null;
            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_wait, null);
            builder = new AlertDialog.Builder(activity);
            builder.setCancelable(false);
            // TextView tv_yes = (TextView)
            // view.findViewById(R.id.buy_tv_dlyes);

            builder.setView(view);
            dialog_sell = builder.create();
            dialog_sell.show();
        } else if (a == 0)
            if(dialog_sell!=null) {
                dialog_sell.dismiss();
            }
    }*/

}
