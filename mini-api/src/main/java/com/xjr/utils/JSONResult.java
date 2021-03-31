package com.xjr.utils;

/**
 * @Description: 自定义响应数据结构
 * 这个类是提供给门户，ios，安卓，微信商城用的
 * 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错，token过期也是该错误
 * 555：异常抛出信息
 */
public class JSONResult {

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok;    // 不使用

    public JSONResult() {

    }

    public JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //判断状态是否成功
    public Boolean isOK() {
        return this.status == 200;
    }

    //创建成功消息
    public JSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    //创建一个消息
    public static JSONResult build(Integer status, String msg, Object data) {
        return new JSONResult(status, msg, data);
    }

    //创建无返回值的成功消息
    public static JSONResult ok() {
        return new JSONResult(null);
    }

    //创建有返回值的成功消息
    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    //1、返回有错误信息
    public static JSONResult errorMsg(String msg) {
        return new JSONResult(500, msg, null);
    }

    //2、返回有bean map
    public static JSONResult errorMap(Object data) {
        return new JSONResult(501, "bean验证错误", data);
    }

    //3、返回Token校验错误
    public static JSONResult errorTokenMsg(String msg) {
        return new JSONResult(502, msg, null);
    }

    //4、返回其他错误
    public static JSONResult errorException(String msg) {
        return new JSONResult(555, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
