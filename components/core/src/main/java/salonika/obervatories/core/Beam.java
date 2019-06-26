package salonika.obervatories.core;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/26 22:49
 * @Description: Beam
 * * a interesting bean
 */
public class Beam {

    /**
     * id
     */
    private String id;

    private List<String> visitors = new LinkedList<>();

    /**
     * mess
     */
    private JSONObject mess = new JSONObject();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<String> visitors) {
        this.visitors = visitors;
    }

    public JSONObject getMess() {
        return mess;
    }

    public void setMess(JSONObject mess) {
        this.mess = mess;
    }
}
