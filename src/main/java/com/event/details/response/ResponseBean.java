package com.event.details.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBean {
    private Object data;
    private String errorCode;
    private String errorDesc;

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorDesc() {
        return errorDesc;
    }
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString(){
        return "Data: "+data+", Error Code: "+errorCode+", Error Description: "+errorDesc;
    }
}
