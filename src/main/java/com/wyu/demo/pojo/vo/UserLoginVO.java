package com.wyu.demo.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    //private Integer id;
    private String username;
    private String password;
    private String captcha;
    private String token;
}
