package com.scauzj.chatroom.controller;

import com.scauzj.chatroom.domain.AjaxResult;
import com.scauzj.chatroom.domain.dto.MessageDto;
import com.scauzj.chatroom.service.MessageService;
import com.scauzj.chatroom.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private MessageService messageService;

    // 群发消息
    @PostMapping("/sendMessageToAll")
    public AjaxResult sendGroupMessage(@RequestBody MessageDto messageDto) {
        try {
            Long sendId = messageDto.getSendId();
            String message = messageDto.getMessage();
            webSocketServer.sendMessageToAll(String.valueOf(sendId), message);
            messageService.insertMessage(sendId, 0L, message);
        } catch (Exception e) {
            return AjaxResult.error("发送消息失败");
        }
        return AjaxResult.success();
    }

    // 私聊
    @PostMapping("sendMessage")
    public AjaxResult sendPrivateMessage(@RequestBody MessageDto messageDto) {
        try {
            Long sendId = messageDto.getSendId();
            Long toId = messageDto.getToId();
            String message = messageDto.getMessage();
            webSocketServer.sendMessage(String.valueOf(sendId), String.valueOf(toId), message);
            messageService.insertMessage(sendId, toId, message);
        } catch (Exception e) {
            return AjaxResult.error("发送消息失败");
        }
        return AjaxResult.success();
    }

    // 获取群发消息
    @GetMapping("/getMessageToAll")
    public AjaxResult getGroupMessage() {
        return AjaxResult.success(messageService.getGroupMessageList());
    }

    // 获取私聊消息
    @GetMapping("/getMessage")
    public AjaxResult getPrivateMessage(@RequestParam Long sendId, @RequestParam Long toId) {
        return AjaxResult.success(messageService.getMessageList(sendId, toId));
    }
}
