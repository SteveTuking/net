package cn.cust.netty.param;

/**
 * Created by zhangbing on 2018/3/6.
 */

public class Request {

    private Long id;

    private String command;

    private Object content;

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

    public void setId(Long id) {
        this.id = id;
    }
}

