package com.wyu.demo.pojo.Result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //在序列化时忽略"null"值
public class Result<T> {
    private String msg; //响应信息 描述字符串
    private Integer code;//响应码
    private T data; //返回的数据
    private Integer expire; //token过期时间
    private String token; //jwt token
    private Boolean codeShow; //显示验证码


    //成功响应 无数据返回
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setMsg("success");
        result.setCode(200);
        return result;
    }

    //成功响应 返回数据和token
    public static <T> Result<T> success( String token, Integer expire) {
        Result<T> result = new Result<>();
        result.setMsg("success");
        result.setCode(200);
        result.setToken(token);
        result.setExpire(expire);
        return result;
    }

    //失败响应
    public static <T> Result<T> error(String msg, int code, boolean codeShow) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setCode(code);
        result.setCodeShow(codeShow);
        return result;
    }

}
