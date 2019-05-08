package com.okhttps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.net.ExecutorsAsyncTask;
import com.net.HttpConnection;
import com.net.HttpTask;
import com.net.imp.ResponseTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends Activity {
    private TextView text;

    Button button;

    String url="http://www.baidu.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (TextView) findViewById(R.id.text_view);

        button = (Button) findViewById(R.id.button);

        //registerForContextMenu(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建连接
               /* HttpTask th=new HttpTask(MainActivity.this,false);
                HashMap map= new HashMap<String, Object>();
                th.executeTask(map,new ResponseTask(){

                    @Override
                    public void onTaskPost(String response) {
                        super.onTaskPost(response);
                    }

                    @Override
                    public String onTaskBackground(Map<String, String> map) {
                        super.onTaskBackground(map);
                        return HttpConnection.httpGet(MainActivity.this,"http://www.baidu.com",null);
                    }
                });*/

                    for (int size = 0; size < 5; size++) {
                        ExecutorsAsyncTask th = new ExecutorsAsyncTask(MainActivity.this, false);
                        HashMap map = new HashMap<String, Object>();
                        final int finalSize = size;
                        th.executeTask(map, new ResponseTask() {

                            @Override
                            public void onTaskPost(String response) {
                                super.onTaskPost(response);
                            }

                            @Override
                            public String onTaskBackground(Map<String, String> map) {
                                super.onTaskBackground(map);


                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss

                                Date date = new Date(System.currentTimeMillis());


                                Log.d("进程--->", "线程ID：" + finalSize + "时间" + simpleDateFormat.format(date));
                                return HttpConnection.httpGet(MainActivity.this,"http://www.google.com",null);
                            }
                        });
                    }


            }
        });





    }








}
