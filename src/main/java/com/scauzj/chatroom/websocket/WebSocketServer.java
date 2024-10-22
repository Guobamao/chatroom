package com.scauzj.chatroom.websocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/ws/{userId}")
public class WebSocketServer {

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 用户ID
    private String userId;

    // 存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<>();

    // 存放在线连接用户信息
    private static ConcurrentHashMap<String, jakarta.websocket.Session> sessionPool = new ConcurrentHashMap<String, jakarta.websocket.Session>();

    /**
     * 连接建立成功调用的方法
     * @param session 会话对象
     * @param userId 传入用户ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            this.session = session;
            this.userId = userId;
            // 将会话对象添加到webSockets中
            webSockets.add(this);
            // 添加到在线用户列表
            sessionPool.put(userId, session);
            System.out.println("客户端：" + userId + "连接成功，当前连接人数：" + webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 消息内容
     * @param userId 传入用户ID
     */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId) {
        System.out.println("客户端：" + userId + "发送消息：" + message);
    }

    /**
     * 连接关闭调用的方法
     * @param userId 传入用户ID
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        try {
            webSockets.remove(this);
            sessionPool.remove(this.userId);
            System.out.println("客户端：" + userId + "断开连接，当前连接人数：" + webSockets.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播给所有用户（群聊）
     * @param message 消息内容
     * @param sendId 发送者ID
     */
    public void sendMessageToAll(String sendId, String message) {
        for (WebSocketServer webSocket : webSockets) {
            try {
                // 判断会话对象是否打开
                if (webSocket.session.isOpen()) {
                    // 构造消息内容并发送(发送者ID, 群发ID:0, 消息内容)
                    webSocket.session.getAsyncRemote().sendText(sendId + ",0," + message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息（私聊）
     * @param sendId 发送者ID
     * @param toId 接收者ID
     * @param message 消息内容
     */
    public void sendMessage(String sendId, String toId,  String message) {
        // 获取接收者会话对象
        Session session = sessionPool.get(toId);
        // 判断会话对象存在且打开
        if (session != null && session.isOpen()) {
            try {
                // 构造消息内容并发送(发送者ID, 接收者ID, 消息内容)
                session.getAsyncRemote().sendText(sendId + "," + toId + "," + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
