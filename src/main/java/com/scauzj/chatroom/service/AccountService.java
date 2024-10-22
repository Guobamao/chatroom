package com.scauzj.chatroom.service;

import com.scauzj.chatroom.domain.Account;
import com.scauzj.chatroom.domain.vo.FriendsVo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {

    /**
     * 根据用户名查找账号信息
     * @param username 用户名
     * @return 账号信息
     */
    Account findAccountByUsername(String username);

    /**
     * 注册账号
     * @param account 账号信息
     * @return 注册成功返回true，否则返回false
     */
    boolean registerAccount(Account account);

    /**
     * 通过id查找好友
     *
     * @param id 账号ID
     * @return 好友列表
     */
    List<FriendsVo> findFriendsById(Long id);
}
