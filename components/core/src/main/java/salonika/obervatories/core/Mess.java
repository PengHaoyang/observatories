package salonika.obervatories.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 11:15
 * @Description: Mess
 */
public class Mess extends JSONObject {

    public JSONArray getVisitor() {
        JSONArray visitors = this.getJSONArray("visitors");
        if (null == visitors) {
            visitors = new JSONArray();
            this.put("visitors", visitors);
        }
        return visitors;
    }
}
