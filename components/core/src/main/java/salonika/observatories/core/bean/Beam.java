package salonika.observatories.core.bean;

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

    private List<String> errStack = new LinkedList<>();

    /**
     * mess
     */
    private Mess mess = new Mess();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getErrStack() {
        return errStack;
    }

    public void setErrStack(List<String> errStack) {
        this.errStack = errStack;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }
}
