package com.fatlamb.fattt.vo;

/**
 * Created by hasee on 2017/1/12.
 */
public class BaseVo {
    public int status ;
    public Object result ;
    public String msg ;

    public BaseVo(int status, Object result) {
        this.status = status;
        this.result = result;
    }

    public BaseVo(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static BaseVo success(Object result){
        return new BaseVo(0, result);
    }

    public static BaseVo error(String msg){
        return new BaseVo(1 , msg);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
