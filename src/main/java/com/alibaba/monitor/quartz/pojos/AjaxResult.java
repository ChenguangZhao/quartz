package com.alibaba.monitor.quartz.pojos;

import com.alibaba.fastjson.JSONObject;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/25
 */
public class AjaxResult {

    String callback;

    JSONObject jsonObject;

    private AjaxResult(String callback, JSONObject jsonObject) {
        this.callback = callback;
        this.jsonObject = jsonObject;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static AjaxResult errResult(String callback, String message) {
        return new AjaxResult(callback, geneJSONObject(1, message, null));
    }

    public static AjaxResult succResult(String callback) {
        return new AjaxResult(callback, geneJSONObject(0, "ok", null));
    }

    public static AjaxResult succResult(String callback, Object data) {
        return new AjaxResult(callback, geneJSONObject(0, "ok", data));
    }

    private static JSONObject geneJSONObject(int code, String message, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

}
