package com.scauzj.chatroom.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id; // 消息ID
    private Long sendId; // 发送者ID
    private Long toId; // 接收者ID
    private String message; // 消息内容
}
