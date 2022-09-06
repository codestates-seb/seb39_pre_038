package com.pre_38.pre_project.member.token;

import com.pre_38.pre_project.member.dto.TokenResponse;
import com.pre_38.pre_project.member.repository.MemberRepository;
import com.pre_38.pre_project.member.support.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@Component
public class JwtTokenProvider {
    private final SecretKey key;
    private final String COOKIE_REFRESH_TOKEN_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1800L;
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 3600L * 24 * 14;
    private final String AUTHORITIES_KEY = "role";

    @Autowired
    private MemberRepository memberRepository;

    public JwtTokenProvider(@Value("${app.auth.token.secret-key}")String secretKey,
                            @Value("${app.auth.token.refresh-cookie-key}")String cookieKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.COOKIE_REFRESH_TOKEN_KEY = cookieKey;
    }

    public TokenResponse createToken(Long payload) {
        Date now = new Date();
        // accessToken
        String accessToken = Jwts.builder()
                .setSubject(payload.toString())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        // refreshToken
        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new TokenResponse(accessToken, refreshToken);
    }

    public String getPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private void saveRefreshToken(Authentication authentication, String refreshToken) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        Long id = Long.valueOf(user.getUsername());

        memberRepository.updateRefreshToken(id, refreshToken);
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        CustomUserDetails principal = new CustomUserDetails(Long.valueOf(claims.getSubject()), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
        } catch (IllegalStateException e) {
            log.info("잘못된 토큰입니다.");
        }
        return false;
    }

    public long getValiditySeconds() {
        return REFRESH_TOKEN_EXPIRE_LENGTH;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
