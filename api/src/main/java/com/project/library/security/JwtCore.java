package com.project.library.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtCore {
    @Value("${library.app.secret}")
    private String secret;
    @Value("${library.app.lifetime}")
    private int lifetime;
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "mysecurekey1234567890secureenough1234567890!".getBytes(StandardCharsets.UTF_8)
    );

    // Время жизни токена (в миллисекундах, например, 10 минут)
    private static final long TOKEN_LIFETIME = 10 * 60 * 1000;

    // Метод для генерации токена
    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Устанавливаем subject
                .setIssuedAt(new Date())               // Время создания токена
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFETIME)) // Время истечения
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Подпись токена
                .compact(); // Генерация
    }

    // Метод для получения имени пользователя из токена
    public String getNameFromJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Используем тот же ключ, что и при генерации
                .build()
                .parseClaimsJws(token) // Парсим токен
                .getBody()
                .getSubject(); // Возвращаем subject (имя пользователя)
    }
}
