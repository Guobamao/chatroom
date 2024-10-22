package com.scauzj.chatroom.domain;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

public record AjaxResult<T> (int code, T data, String message) {

    public static <T> AjaxResult<T> success() {
        return success(null);
    }
    public static <T> AjaxResult<T> success(T data) {
        return new AjaxResult<>(200, data, "success");
    }

    public static <T> AjaxResult<T> error(int code, String message) {
        return new AjaxResult<>(code, null, message);
    }

    public static <T> AjaxResult<T> error(String message) {
        return error(400, message);
    }

    public static <T> AjaxResult<T> unAuthorized(String message){
        return error(401, message);
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
