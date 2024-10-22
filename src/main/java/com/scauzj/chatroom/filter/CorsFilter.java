package com.scauzj.chatroom.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(-102)
public class CorsFilter extends HttpFilter {
    @Value("${spring.security.filter.allow-origin}")
    String allowOrigin;
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.addCorsHeader(request,response);
        chain.doFilter(request,response);
    }

    private void addCorsHeader(HttpServletRequest request,HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin",allowOrigin);
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers","Authorization,Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","true");
    }
}