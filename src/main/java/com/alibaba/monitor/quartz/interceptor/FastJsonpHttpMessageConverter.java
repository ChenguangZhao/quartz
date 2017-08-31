package com.alibaba.monitor.quartz.interceptor;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/25
 */

import java.io.IOException;
import java.io.OutputStream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.monitor.quartz.pojos.AjaxResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * @author chenguang.zcg
 * @Title:
 * @Description:
 * @Author:chenguang.zcg
 * @Since:2017年2月28日
 * @Version:1.1.0
 */
public class FastJsonpHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    protected boolean supports(Class<?> clazz) {
        return AjaxResult.class == clazz;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException,
        HttpMessageNotWritableException {
        AjaxResult ajaxResult = (AjaxResult)obj;
        if (StringUtils.isEmpty(ajaxResult.getCallback())) {
            super.writeInternal(ajaxResult.getJsonObject(), outputMessage);
            return;
        }
        OutputStream out = outputMessage.getBody();
        byte[] bytes;
        if (this.getCharset() == UTF8) {
            byte[] jsonBytes;
            if (this.getFeatures() != null) {
                jsonBytes = JSON.toJSONBytes(ajaxResult.getJsonObject(), this.getFeatures());
            } else {
                jsonBytes = JSON.toJSONBytes(ajaxResult.getJsonObject(), new SerializerFeature[0]);
            }
            bytes = compile(ajaxResult.getCallback(), jsonBytes);
        } else {
            String text;
            if (this.getFeatures() != null) {
                text =
                    ajaxResult.getCallback() + "(" + JSON.toJSONString(ajaxResult.getJsonObject(), getFeatures())
                        + ");";
            } else {
                text = ajaxResult.getCallback() + "(" + JSON.toJSONString(ajaxResult.getJsonObject()) + ");";
            }

            bytes = text.getBytes(this.getCharset());
        }

        out.write(bytes);
    }

    /**
     * 将回调函数名和两个括号以及真正数据拼装起来
     *
     * @param jsonpFunction jsonp加在返回数据开头的回头函数名称
     * @param jsonBytes     真正需要返回的json格式的数据
     * @return 最终需要发出的字节数组
     */
    private byte[] compile(String jsonpFunction, byte[] jsonBytes) {
        byte[] beginList = (jsonpFunction + "(").getBytes(UTF8);
        byte[] endList = ");".getBytes(UTF8);
        byte[] res = new byte[beginList.length + jsonBytes.length + endList.length];
        System.arraycopy(beginList, 0, res, 0, beginList.length);
        System.arraycopy(jsonBytes, 0, res, beginList.length, jsonBytes.length);
        System.arraycopy(endList, 0, res, beginList.length + jsonBytes.length, endList.length);
        return res;
    }

}