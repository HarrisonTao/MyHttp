package com.net;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


import com.net.httplibrary.R;
import com.net.imp.ResponseTask;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ThreadTask extends AsyncTask < Map<String, String>, String,String>{


    private ResponseTask imp;
    private Activity context;

 //   public AlertDialog dialog_sell = null;
    private Dialog progressDialog;

    private   boolean isOpenDialg=false;


    public ThreadTask(Activity context){
        this.context=context;
        //判断网络是否可用


    }

    public ThreadTask(Activity context, boolean isOpenDialg){
        this.context=context;

        //判断网络是否可用

        this.isOpenDialg=isOpenDialg;

    }
    public void executeTask(Map<String, String> map, ResponseTask imp){
        this.imp=imp;

        if(isOpenDialg){
            dialogwait(context, 1);
        }
        this.execute(map);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        imp.onCreateTask("");
            if(isOpenDialg){
                dialogwait(context, 0);
            }

    }

    @Override
    protected String doInBackground( Map<String, String>... params) {

            return imp.onTaskBackground( params[0]);

    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(isOpenDialg){
            dialogwait(context, 0);
        }

        imp.onTaskPost(response);




    }

    public void dialogwait(Context activity ,int a) {
        if (a == 1) {
            try {
                progressDialog = new Dialog(context);
               // View view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
               // progressDialog.setContentView(view);
             //   ImageView progress = (ImageView) view.findViewById(R.id.progress);
            //    Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.progress);
              //  LinearInterpolator lin = new LinearInterpolator();
             //   operatingAnim.setInterpolator(lin);
            //    progress.startAnimation(operatingAnim);
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
    }

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
