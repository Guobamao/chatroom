package com.scauzj.chatroom.domain.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long sendId;
    private Long toId;
    private String message;
}
