package org.activiti.explorer.base;

import java.io.Serializable;

/**
* <b> 结果模型
* </b><br><br><i>Description</i> : 返回的统一结果模型
* <br><br>Date: 2019/6/17 10:41     <br>Author : dxl
*/
public class Result implements Serializable {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 成功与否标志
     */
    private boolean success = true;
    /**
     * 返回状态码，为空则默认200.前端需要拦截一些常见的状态码如403、404、500等
     */
    private Integer status;
    /**
     * 编码，可用于前端处理多语言，不需要则不用返回编码
     */
    private String code;
    /**
     * 相关消息
     */
    private String msg;
    /**
     * 相关数据
     */
    private Object data;


    public Result() {}

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, Integer status) {
    	this(success);
        this.status = status;
    }

    public Result(boolean success, String code, String msg){
        this(success);
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, Integer status, String code, String msg) {
    	this(success);
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Result(boolean success, String code, String msg, Object data){
        this(success);
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
