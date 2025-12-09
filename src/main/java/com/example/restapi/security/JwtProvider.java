package com.example.restapi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretString;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey secretKey;

    // JwtProvider가 생성되고 빈에 등록되기 전 실행
    @PostConstruct
    public void init(){
        // HS256
        this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
    }

    // JWT 토큰 발행
    public String createToken(String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username) // 사용자 정보
                .issuedAt(now) // 발행된 현재시간
                .expiration(expiryDate) //만료날짜
                .signWith(secretKey) // 시크릿키
                .compact(); // 발행
    }
    // claims 검증
    private Claims getClaims(String token){
        return Jwts.parser() // 토큰을 읽는 매서드
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload(); // 복호화
    }

    // 토큰에서 사용자명 추출
    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token){
        try {
            getClaims(token);
            return true;
        }catch (ExpiredJwtException e){
            System.out.println("만료된 JWT입니다.");
        }catch (UnsupportedJwtException e){
            System.out.println("지원하지 않는 JWT입니다.");
        }catch (MalformedJwtException e){
            System.out.println("잘못된 JWT입니다.");
        }catch (SignatureException e){
            System.out.println("서명이 잘못되었습니다.");
        }catch (IllegalArgumentException e){
            System.out.println("토큰이 없습니다.");
        }

        return false;
    }
}
