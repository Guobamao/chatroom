package com.scauzj.chatroom.controller;

import com.scauzj.chatroom.domain.Account;
import com.scauzj.chatroom.domain.AjaxResult;
import com.scauzj.chatroom.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody Account account) {
        if (accountService.findAccountByUsername(account.getUsername()) != null) {
            return AjaxResult.error("用户名已存在");
        }
        return accountService.registerAccount(account) ? AjaxResult.success() : AjaxResult.error("注册失败");
    }
}
