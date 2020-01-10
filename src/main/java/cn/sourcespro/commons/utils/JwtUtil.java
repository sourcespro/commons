package cn.sourcespro.commons.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt就是基于json签发token和校验token的一种机制。主要功能是权限验证和存储加密的信息。
 * jwt由3部分组成(base64解密工具可以解密)：
 * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
 * eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
 * SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
 * header(头信息):解密为{"alg": "HS256","typ": "JWT"}是算法和类型。
 * playload(荷载):解密为{"sub": "1234567890","name": "John Doe","iat": 1516239022} 载荷就是存放有效信息的地方。
 * verify signature(校验签名):由 base64UrlEncode(header) + "." +base64UrlEncode(payload)。
 *
 * @author zhang
 */
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 在验证或签名实例中使用的密钥(自定义和密码加盐差不多)
     */
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQL34hHgGfdf>?N<:{LWPW";

    /**
     * 签发用户
     */
    private static final String ISSUER = "user";


    /**
     * 生成token
     * @param key key
     * @param value value
     * @param maxAge 有效时长ms
     * @return token
     */
    public static String create(String key, String value, long maxAge) {
        if (key == null || value == null) {
            logger.info("参数key={},value={}不能为空", key, value);
            return null;
        }
        try {
            //使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //通过调用 JWT.create()来创建 jwt实例
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    //设置过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + maxAge));
            //索赔:添加自定义声明值,完成荷载的信息
            builder.withClaim(key, value);
            //签署:调用sign()传递算法实例
            return builder.sign(algorithm);
        } catch (JWTCreationException e) {
            logger.error("无效的签名配置", e);
        }
        return null;
    }

    /**
     * 生成token
     * @param claims 参数
     * @param maxAge 有效时长ms
     * @return token
     */
    public static String create(Map<String, String> claims, long maxAge) {
        try {
            //使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //通过调用 JWT.create()来创建 jwt实例
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    //设置过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + maxAge));
            //索赔:添加自定义声明值,完成荷载的信息
            claims.forEach(builder::withClaim);
            //签署:调用sign()传递算法实例
            return builder.sign(algorithm);
        } catch (JWTCreationException e) {
            logger.error("无效的签名配置", e);
        }
        return null;
    }

    /**
     * 验证token，并返回数据
     * @param token token
     * @return 令牌中定义的声明
     */
    public static Map<String, String> verify(String token) {
        if (token == null) {
            logger.info("参数token={}不能为空", token);
            return null;
        }
        try {
            //使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //验证令牌的签名
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            //针对给定令牌执行验证
            DecodedJWT jwt = verifier.verify(token);
            //获取令牌中定义的声明
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>(map.size());
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch (JWTVerificationException e) {
            logger.error("校验失败或token已过期!", e);
        }
        return null;
    }

    /**
     * 验证token，并返回数据
     * @param token token
     * @param key key
     * @return 令牌中定义的声明
     */
    public static String verify(String token, String key) {
        if (token == null || key == null) {
            logger.info("参数token={}，key={}不能为空", token, key);
            return null;
        }
        try {
            //使用HS256算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //验证令牌的签名
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            //针对给定令牌执行验证
            DecodedJWT jwt = verifier.verify(token);
            //获取令牌中定义的声明
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>(map.size());
            for (Map.Entry<String, Claim> entry : map.entrySet()) {
                String k = entry.getKey();
                if (key.equals(k)) {
                    return entry.getValue().asString();
                }
            }
        } catch (JWTVerificationException e) {
            logger.error("校验失败或token已过期!", e);
        }
        return null;
    }

}