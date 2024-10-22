package com.scauzj.chatroom.service.impl;


import com.scauzj.chatroom.domain.Account;
import com.scauzj.chatroom.domain.vo.FriendsVo;
import com.scauzj.chatroom.mapper.AccountMapper;
import com.scauzj.chatroom.service.AccountService;
import com.scauzj.chatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
    /**
     * 根据用户名查找账号信息
     * @param username 用户名
     * @return 账号信息
     */
    @Override
    public Account findAccountByUsername(String username) {
        return accountMapper.selectAccountByUsername(username);
    }

    /**
     * 注册账号
     * @param account 账号信息
     * @return 注册成功返回true，否则返回false
     */
    @Override
    public boolean registerAccount(Account account) {
        String password = encoder.encode(account.getPassword());
        account.setPassword(password);
        account.setRole("user");
        account.setRegisterTime(new Date());

        return accountMapper.insertAccount(account) > 0;
    }

    /**
     * 通过id查找好友
     *
     * @param id 账号ID
     * @return 好友列表
     */
    @Override
    public List<FriendsVo> findFriendsById(Long id) {
        List<Account> friends =  accountMapper.selectFriendsById(id);
        // 获取最后一条消息
        List<FriendsVo> friendsVoList = new ArrayList<>();
        String message = messageService.getGroupLastMessage();
        FriendsVo friendsVo = new FriendsVo(0L, "群聊", "https://tse1-mm.cn.bing.net/th/id/OIP-C.ZdYtv8Cw01EGBOvpDSpblAAAAA?rs=1&pid=ImgDetMain", message);
        friendsVoList.add(friendsVo);
        for (Account friend : friends) {
            message = messageService.getLastMessageById(id, friend.getId());
            friendsVo = new FriendsVo(friend.getId(), friend.getUsername(), friend.getAvatar(), message);
            friendsVoList.add(friendsVo);
       }
        return friendsVoList;
    }
}
