package com.scauzj.chatroom.service.impl;

import com.scauzj.chatroom.domain.Message;
import com.scauzj.chatroom.mapper.MessageMapper;
import com.scauzj.chatroom.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 根据发送者id和接收者id获取最后一条消息
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @return 聊天信息
     */
    @Override
    public String getLastMessageById(Long sendId, Long toId) {
        return messageMapper.getLastMessageById(sendId, toId);
    }

    /**
     * 保存聊天信息
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @param message 聊天信息
     * @return 是否保存成功
     */
    @Override
    public boolean insertMessage(Long sendId, Long toId, String message) {
        return messageMapper.insertMessage(sendId, toId, message) > 0;
    }

    /**
     * 根据发送者id和接收者id获取聊天记录（私聊）
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @return 聊天记录
     */
    @Override
    public List<Message> getMessageList(Long sendId, Long toId) {
        List<Message> messageList = messageMapper.selectMessageList(sendId, toId);
        return messageList;
    }

    /**
     * 获取群聊记录
     * @return 群聊记录
     */
    @Override
    public List<Message> getGroupMessageList() {
        return messageMapper.selectGroupMessageList();
    }

    /**
     * 获取群聊最后一条消息
     * @return 群聊最后一条消息
     */
    @Override
    public String getGroupLastMessage() {
        return messageMapper.getGroupLastMessage();
    }
}
