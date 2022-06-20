package com.moreversal.grabthathoe.common.lib;

import com.moreversal.grabthathoe.common.enums.JwtType;
import com.moreversal.grabthathoe.common.exception.AuthorizationException;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class Jwt {

    @Value("${jwt.secret.access}")
    private String secretAccessKey;

    @Value("${jwt.secret.access}")
    private String secretRefreshKey;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private final UserRepository userRepository;

    public String createToken(User user, JwtType jwtType) {

        Date nowDate = new Date();
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.setTime(nowDate);

        String secretKey = "";

        switch(jwtType) {
            case ACCESS:
                expiredDate.add(Calendar.DATE, 3);
                secretKey = secretAccessKey;
            case REFRESH:
                expiredDate.add(Calendar.DATE, 20);
                secretKey = secretRefreshKey;
        }

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS512");

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", user.getId());
        claimsMap.put("phone", user.getPhone());

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(claimsMap)
                .setExpiration(expiredDate.getTime())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public User validateToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretAccessKey)).parseClaimsJws(token).getBody();
        Optional<User> user = userRepository.findUserByPhone(claims.get("phone", String.class));

        if(!user.isPresent()) {
            throw new AuthorizationException("해당하는 유저 정보가 없습니다.");
        }

        return user.get();
    }

    public String refresh(String refreshToken) {

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretRefreshKey)).parseClaimsJws(refreshToken).getBody();
        Optional<User> user = userRepository.findUserByPhone(claims.get("phone", String.class));

        if(!user.isPresent()) {
            throw new AuthorizationException("해당하는 유저 정보가 없습니다.");
        }

        return createToken(user.get(), JwtType.ACCESS);
    }

    public String extract(HttpServletRequest request, String type) {

        Enumeration<String> headers = request.getHeaders("Authorization");

        while(headers.hasMoreElements()) {
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }
}
