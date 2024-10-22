package com.scauzj.chatroom.config;

import com.scauzj.chatroom.domain.Account;
import com.scauzj.chatroom.domain.AjaxResult;
import com.scauzj.chatroom.domain.vo.AccountVo;
import com.scauzj.chatroom.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.io.IOException;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private AccountService accountService;

    // 注入认证过滤器链
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/auth/**", "/ws/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(conf->conf
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure)
                )
                .logout(conf->conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::onUnauthorized)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    // 登录成功返回用户信息给客户端
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException  {
        // 设置响应头
        response.setContentType("application/json;charset=utf-8");
        User user = (User) authentication.getPrincipal();
        Account account = accountService.findAccountByUsername(user.getUsername());
        AccountVo accountVo = new AccountVo();
        BeanUtils.copyProperties(account, accountVo);
        response.getWriter().println(AjaxResult.success(accountVo).asJsonString());
    }

    // 登录失败返回失败信息
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(AjaxResult.unAuthorized(exception.getMessage()).asJsonString());
    }

    // 退出登录成功返回成功信息
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(AjaxResult.success("退出成功").asJsonString());
    }

    // 未认证访问其他接口返回401
    public void onUnauthorized(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(AjaxResult.unAuthorized(exception.getMessage()).asJsonString());
    }
}
