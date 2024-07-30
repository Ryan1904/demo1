package com.wyu.demo.pojo.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wyu.jwt")
@Data
public class JwtProperties {


}
