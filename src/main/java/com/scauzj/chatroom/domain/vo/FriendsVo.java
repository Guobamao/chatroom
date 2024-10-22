package com.scauzj.chatroom.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendsVo {
    private Long id;
    private String username;
    private String avatar;
    private String message;
}
