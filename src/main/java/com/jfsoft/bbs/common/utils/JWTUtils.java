package com.jfsoft.bbs.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2018/3/25 下午8:56
 * @Description TODO(一句话描述类作用)
 */
public class JWTUtils {

    // 过期时间6 * 4 * 365小时
    private static final long EXPIRE_TIME = 6 * 60 * 60 * 1000 * 4 * 365;

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpire(String token) {
        final Base64.Decoder decoder = Base64.getDecoder();
        String playload = token.substring(token.indexOf(".") + 1, token.lastIndexOf("."));
        JSONObject playloadJson = null;
        try {
            playloadJson = JSONObject.parseObject(new String(decoder.decode(playload), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        long currentTime = System.currentTimeMillis();
        long expireTime = playloadJson.getLong("exp");
        if (currentTime - expireTime > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "eyJleHAiOjE1NTAyMTIwNTUsImFyZWFjb2RlIjoiMTAwIiwidXNlcm5hbWUiOiJhZG1pbiJ9";

        final Base64.Decoder decoder = Base64.getDecoder();
        System.out.println(new String(decoder.decode(str), "UTF-8"));
    }

    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param unionId 用户唯一ID
     * @return 加密的token
     */
    public static String sign(String userId, String unionId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(unionId);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("unionId", unionId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
