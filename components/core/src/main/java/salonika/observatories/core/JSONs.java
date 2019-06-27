package salonika.observatories.core;

import com.alibaba.fastjson.JSONObject;

import java.util.function.Consumer;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 11:02
 * @Description: JSONs
 */
public class JSONs {

    /**
     * 定义 JSONObject
     */
    public static void gen(JSONObject json, Consumer<JSONObject> consumer) {
        consumer.accept(json);
    }

    /**
     * 生成 JSONObject
     */
    public static JSONObject gen(Consumer<JSONObject> consumer) {
        JSONObject json = new JSONObject();
        consumer.accept(json);
        return json;
    }

}
