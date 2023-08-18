package com.byritium.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

public class JwtUtils {
    // TOKEN的有效期一天（S）
    private static final int TOKEN_TIME_OUT = 3600;
    // 加密KEY
    private static final String TOKEN_ENCRYPT_KEY = "DA1MX9EehP7grZPcAjpozuy7CI7gDw3eSDsQPZtVuck=";
    // 最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    // 生产ID
    public static String createToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        claimMaps.put("id", id);
        long currentTime = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_ENCRYPT_KEY);
        return JWT.create()
                // 将 user id 保存到 token 里面
                .withAudience(id.toString())
                // 五分钟后token过期
                .withExpiresAt(Instant.ofEpochSecond(currentTime + 3600 * 1000))
                // token 的密钥
                .sign(algorithm);
    }

    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */

    public static String get(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static boolean checkSign(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_ENCRYPT_KEY);

            JWTVerifier verifier = JWT.require(algorithm)

                    // .withClaim("username", username)

                    .build();

            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token 无效，请重新获取");

        }

    }

    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            //下面调用方法的参数决定了生成密钥的长度，可以修改为128, 192或256
            kg.init(256);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String secret = Base64.getEncoder().encodeToString(b);
            System.out.println(secret);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("没有此算法");
        }

    }
}
