package com.scauzj.chatroom.mapper;

import com.scauzj.chatroom.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    /**
     * 根据发送者id和接收者id获取最后一条消息
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @return 聊天信息
     */
    String getLastMessageById(Long sendId, Long toId);

    /**
     * 保存聊天信息
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @param message 聊天信息
     * @return 是否保存成功
     */
    int insertMessage(Long sendId, Long toId, String message);

    /**
     * 根据发送者id和接收者id获取聊天记录（私聊）
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @return 聊天记录
     */
    List<Message> selectMessageList(Long sendId, Long toId);

    /**
     * 获取群聊记录
     * @return 群聊记录
     */
    List<Message> selectGroupMessageList();

    /**
     * 获取群聊最后一条消息
     * @return 群聊最后一条消息
     */
    String getGroupLastMessage();
}
