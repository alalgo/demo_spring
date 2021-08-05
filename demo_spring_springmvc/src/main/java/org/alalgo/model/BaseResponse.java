package org.alalgo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {	 
    private Integer code;
    private String msg;
    private T data;
    public static BaseResponse<Object> Success(Object data){
    	return new BaseResponse<Object>(200, "success", data);
    }
    public static BaseResponse<Object> fail(Integer code,String msg){
    	return new BaseResponse<Object>(code, msg, null);
    }    
    public static BaseResponse<Object> DefaultFail(){
    	return new BaseResponse<Object>(500, "fail", null);
    }  
    public static BaseResponse<Object> DefaultSuccess(){
    	return new BaseResponse<Object>(200, "success", null);
    }     
}
