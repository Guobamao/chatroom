package com.scauzj.chatroom.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String avatar;
    private Date registerTime;
}
