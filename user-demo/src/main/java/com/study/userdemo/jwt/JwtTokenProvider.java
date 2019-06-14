package com.study.userdemo.jwt;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// jwt 工具类
// 这个博客中描述了jwt是什么东西
// http://www.jianshu.com/p/576dbf44b2ae
@Component
public class JwtTokenProvider {
    @Autowired
    private JwtConfiguration configuration;

    public JwtTokenProvider(JwtConfiguration configuration) {
        this.setConfiguration(configuration);
    }

    public void setConfiguration(JwtConfiguration jwtConfiguration){
        this.configuration = jwtConfiguration;
    }

    public String createToken(Claims claims){
        String compactJws = Jwts.builder().setPayload(JSONObject.toJSONString(claims))
                                          .compressWith(CompressionCodecs.DEFLATE)
                                          .signWith(SignatureAlgorithm.HS512, configuration.getSecretKeySpec()).compact();
        return compactJws;
    }

    /** token转换 */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(configuration.getSecretKeySpec()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
