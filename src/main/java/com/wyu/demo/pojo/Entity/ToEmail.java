package com.wyu.demo.pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail {
    private String tos; // 邮件接收方，可多人
    private String subject; // 邮件主题
    private String text; // 邮件内容
    private String from;
}
