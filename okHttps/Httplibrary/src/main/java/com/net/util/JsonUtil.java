package com.net.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class JsonUtil {

    /**
     *
     */
    private static final String STRING_NULL = "null";
    private static final String BOOL_VALUE_TRUE = "true";
    private static final String BOOL_VALUE_1 = "1";
    private static final String DEFAULT_VALUE_STRING = "";
    //验证标识
    public static final String RETURN_SUCCESS="0000";

    /**
     * 从字符串获取json对象,如果发生异常则返回null
     */
    public static JSONObject getJsonObjectFromString(String jsonstr) {
        try {
            JSONObject obj;
            obj = new JSONObject(jsonstr);
            return obj;
        } catch (JSONException e) {

        }

        return null;
    }

    /**
     * 获得字符串
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回默认值, 则返回""
     */
    public static String getString(JSONObject json, String name) {
        return getString(json, name, DEFAULT_VALUE_STRING);
    }

    /**
     * 获得字符串
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static String getString(JSONObject json, String name,
                                   String defaultValue) {
        String result = defaultValue;
        if (checkJson(json, name)) {
            try {
                result = json.getString(name);
            } catch (JSONException e) {
                result = defaultValue;

            }
        }
        return result;
    }

    /**
     * 获得整数
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回默认值, 则返回-1
     */
    public static int getInt(JSONObject json, String name) {
        return getInt(json, name, -1);
    }

    /**
     * 获得整数
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static int getInt(JSONObject json, String name, int defaultValue) {
        int result = defaultValue;
        if (checkJson(json, name)) {
            try {
                result = json.getInt(name);
            } catch (JSONException e) {
                result = defaultValue;

            }
        }
        return result;
    }

    /**
     * 获得长整数
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回-1
     */
    public static long getLong(JSONObject json, String name) {
        return getLong(json, name, -1);
    }

    /**
     * 获得长整数
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static long getLong(JSONObject json, String name, long defaultValue) {
        long result = defaultValue;
        if (checkJson(json, name)) {
            try {
                result = json.getLong(name);
            } catch (JSONException e) {
                result = defaultValue;

            }
        }
        return result;
    }

    /**
     * 获得double
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static double getDouble(JSONObject json, String name,
                                   double defaultValue) {
        double result = defaultValue;
        if (checkJson(json, name)) {
            try {
                result = json.getDouble(name);
            } catch (JSONException e) {
                result = defaultValue;

            }
        }
        return result;
    }

    /**
     * 如果为1则true,否则为false
     *
     * @param json
     * @param name
     * @return
     */
    public static boolean getIntBoolean(JSONObject json, String name) {
        return getBoolean(json, name);
    }

    /**
     * 获得boolean
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回false
     */
    public static boolean getBoolean(JSONObject json, String name) {
        return getBoolean(json, name, false);
    }

    /**
     * 获得boolean
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static boolean getBoolean(JSONObject json, String name,
                                     boolean defaultValue) {
        boolean result = defaultValue;
        if (checkJson(json, name)) {
            try {
                String value = json.getString(name);
                result = BOOL_VALUE_1.equals(value)
                        || BOOL_VALUE_TRUE.equalsIgnoreCase(value);
            } catch (JSONException e) {
                result = defaultValue;

            }
        }
        return result;
    }

    /**
     * 获得是否为0的boolean值，用于hasnext
     *
     * @param json json对象
     * @param name 要读取的键值
     */
    public static boolean getIsZero(JSONObject json, String name) {
        int intValue = getInt(json, name);
        boolean result = (intValue == 0) ? true : false;
        return result;
    }

    /**
     * 获得date
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回null
     */
    public static Date getDate(JSONObject json, String name, String dateFormat) {
        Date result = null;

        String str_date = getString(json, name);
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            result = formatter.parse(str_date);
        } catch (ParseException e) {
            // TODO 自动自成的异常处理代码，待完善
            e.printStackTrace();
        }

        return result;
    }




    /**
     * 获得timestamp
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回0
     */
    public static long getTimeStamp(JSONObject json, String name) {
        return getTimeStamp(json, name, 0);
    }

    /**
     * 获得timestamp
     *
     * @param json         json对象
     * @param name         要读取的键值
     * @param defaultValue 默认值,如果发生异常则返回默认值
     * @return
     */
    public static long getTimeStamp(JSONObject json, String name,
                                    long defaultValue) {
        return getLong(json, name, defaultValue);
    }




    /**
     * 获得jsonObject
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回new JSONObject
     */
    public static JSONObject getJsonObject(JSONObject json, String name) {
        JSONObject result = null;
        if (checkJson(json, name)) {
            try {
                result = json.getJSONObject(name);
            } catch (JSONException e) {
                result = new JSONObject();

                Object object = null;
                try {
                    object = json.get(name);
                } catch (JSONException e1) {
                }
                if (object == null) {
                    object = STRING_NULL;
                }

            }
        } else {
            result = new JSONObject();
        }
        return result;
    }

    /**
     * 获得jsonArray
     *
     * @param json json对象
     * @param name 要读取的键值
     * @return 如果发生异常则返回new JSONArray
     */
    public static JSONArray getJsonArray(JSONObject json, String name) {
        JSONArray result = null;
        if (checkJson(json, name)) {
            try {
                result = json.getJSONArray(name);
            } catch (JSONException e) {
                result = new JSONArray();

            }
        } else {
            result = new JSONArray();
        }
        return result;
    }

    /**
     * 检查json合法性
     *
     * @param json
     * @return
     */
    public static boolean checkJson(JSONObject json, String keyName) {
        boolean result = true;
        if (result && json == null) {
            result = false;
        }
        if (result && !json.has(keyName)) {
            result = false;
        }
        if (result && json.isNull(keyName)) {
            result = false;
        }
        return result;
    }

    /**
     * 获取JSON KEY值
     * @param json  JSON数据
     * @param key   名字
     * @return      返回KEY值
     */
    public static String getStringData(String json,String key){
        JSONObject obj= null;
        String str="";
        try {
            obj = new JSONObject(json);
            str=obj.optString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static double getDoubleData(String json,String key){
        JSONObject obj= null;
        double str=0;
        try {
            obj = new JSONObject(json);
            str=obj.optDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取JSON KEY值
     * @param json  JSON数据
     * @param key   名字
     * @return      返回KEY值
     */
    public static int getIntData(String json,String key){
        JSONObject obj= null;
        int str=0;
        try {
            obj = new JSONObject(json);
            str=obj.optInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
    

    /**
     * 获取JSON KEY值
     * @param json  JSON数据
     * @param key   名字
     * @return      返回KEY值
     */
    public static float getFloatData(String json,String key){
        JSONObject obj= null;
        float str=0;
        try {
            obj = new JSONObject(json);
            str=(float) obj.optDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * 获取JSON KEY值
     * @param json  JSON数据
     * @param key   名字
     * @return      返回KEY值
     */
    public static BigDecimal getBigDecimalData(String json,String key){
      
        BigDecimal num = BigDecimal.ZERO;
        try {
        	JSONObject obj = new JSONObject(json);
            num  = new BigDecimal(obj.optString(key));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return num;
    }
/*
    *//**
     * 获取响应
     * @param json
     * @return
     *//*
    public  static Header getReturnCode(String json){
        Header h=new Header();
        String str=JsonUtil.getStringData(json,"header");
        JSONObject obj= null;

        try {
            obj = new JSONObject(str);

            h.code=obj.optString("code");
            h.message=obj.optString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return h;
    }*/
}
