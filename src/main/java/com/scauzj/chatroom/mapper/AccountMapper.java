package com.scauzj.chatroom.mapper;

import com.scauzj.chatroom.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    /**
     * 根据用户名查询账户信息
     * @param username 用户名
     * @return 账号信息
     */
    Account selectAccountByUsername(String username);

    /**
     * 插入账户信息
     * @param account 账号信息
     * @return 插入数量
     */
    int insertAccount(Account account);

    /**
     * 根据id查询好友列表
     * @param id 用户id
     * @return 好友列表
     */
    List<Account> selectFriendsById(Long id);
}
