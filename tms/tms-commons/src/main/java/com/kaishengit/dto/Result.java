package com.kaishengit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    public static final String STATE_SUCCESS = "success";
    public static final String STATE_ERROR = "error";

    public static Result success(Object data){
        Result res = new Result();
        res.state = STATE_SUCCESS;
        res.setData(data);
        return res;
    }

    public Result(String message) {
        this.message = message;
    }

    public Result() {
        super();
    }
    public static Result success(Object data, String message){
        Result res = new Result();
        res.state = STATE_SUCCESS;
        res.setData(data);
        res.setMessage(message);
        return res;
    }

    public static Result success(){
        Result res = new Result();
        res.state = STATE_SUCCESS;
        return res;
    }

    public static Result error(String message){
        Result res = new Result();
        res.state = STATE_ERROR;
        res.setMessage(message);
        return res;
    }

    private String state;
    private String message;
    private Object data;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public static String getStateSuccess() {
        return STATE_SUCCESS;
    }
    public static String getStateError() {
        return STATE_ERROR;
    }

}
