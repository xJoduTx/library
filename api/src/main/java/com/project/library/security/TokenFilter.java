package com.project.library.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class TokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtCore jwtCore;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerAuth = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        System.out.println("Проверка фильтрчейна");
        Enumeration<String> enume = request.getHeaderNames();
        while (enume.hasMoreElements()) {
            String header = enume.nextElement();
            String value = request.getHeader(header);
            System.out.printf("\t%s - %s \n", header, value);
        }
        try {
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                jwt = headerAuth.substring(7); // Убираем "Bearer "
            }
            if (jwt != null) {
                username = jwtCore.getNameFromJwt(jwt);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка в TokenFilter: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
