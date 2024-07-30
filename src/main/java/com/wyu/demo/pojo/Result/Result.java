package com.wyu.demo.pojo.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String msg; //响应信息 描述字符串
    private Integer code;//响应码
    private Object data; //返回的数据


    //成功响应 无数据返回
    //TODO 结果返回格式不符合需求文档
    public static Result success(){
        return new Result("success",200,null);
    }

    //成功响应 返回数据
    public static Result success(Object data){
        return new Result("success",200,data);
    }

    //失败响应
    public static Result error(String msg,int code, boolean codeShow){
        return new Result(msg,code,false);
    }


}
