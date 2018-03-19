package cn.cust.netty.param;

/**
 * Created by zhangbing on 2018/3/6.
 */

import java.util.concurrent.atomic.AtomicLong;


public class Request {

    private String command;

    private Object content;



    private final long id;

    public static final AtomicLong nid = new AtomicLong(0);


    public Request() {
        id = nid.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }


}

