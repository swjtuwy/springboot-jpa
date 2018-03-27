package com.roaddb.paper.exception;

public class InnerException extends Exception {


    private int code;

    public InnerException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String toString() {
        return "Inner exception.code:" + code + ", message:" + getMessage();
    }

}
