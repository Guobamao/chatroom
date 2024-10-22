package com.scauzj.chatroom.controller;

import com.scauzj.chatroom.domain.AjaxResult;
import com.scauzj.chatroom.domain.vo.FriendsVo;
import com.scauzj.chatroom.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // 获取好友列表
    @GetMapping("/friends")
    public AjaxResult<List<FriendsVo>> getFriends(@RequestParam Long id) {
        return AjaxResult.success(accountService.findFriendsById(id));
    }
}
