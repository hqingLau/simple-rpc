package cn.orzlinux.rpc;

import lombok.Data;

/**
 * RPC的返回
 * @author hqingLau
 **/
@Data
public class Response {
    //返回码0成功，非0失败
    private int code;
    //如果是错误，错误原因
    private String message = "ok";
    //返回的数据
    private Object data;
}
