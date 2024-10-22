package com.scauzj.chatroom.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class AccountVo {
    private Long id;
    private String username;
    private String role;
    private String avatar;
    private Date registerTime;
}
