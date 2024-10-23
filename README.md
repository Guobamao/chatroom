# 实时聊天网页系统

## 项目简介

本项目是一个基于Java WebSocket的实时聊天网页系统，采用Spring Boot、MyBatis、Spring Security、Vue.js等技术栈。系统支持用户注册、登录、实时消息发送与接收，以及聊天记录查看功能。

## 技术栈

- **后端**：
  - Spring Boot
  - Spring Security
  - MyBatis
  - WebSocket
  - MySQL

- **前端**：
  - Vue.js

## 功能特性

- 用户注册与登录
- 实时消息发送与接收
- 查看历史聊天记录
- 用户权限管理
- 响应式网页设计

## 安装与运行

### 环境要求

- JDK 1.8 或更高版本
- Node.js（用于前端）
- MySQL 数据库

### 安装步骤

1. **克隆仓库**

   ```bash
   git clone https://github.com/Guobamao/chatroom.git
   cd chatroom
   ```

2. **配置数据库**

   在MySQL中创建数据库chat，并执行 `db.sql` 文件中的建表语句。

3. **后端项目**
   - 修改 `application.yml` 配置文件，设置数据库连接信息。
   - 运行Spring Boot应用：

4. **前端项目**

   - 安装依赖：
   ```bash
   cd chatroom-frontend
   npm install
   ```
   - 启动前端应用：
   ```bash
   npm run dev
   ```

### 访问

- 后端默认运行在 `http://localhost:8081`
- 前端默认运行在 `http://localhost:5137`

## 项目预览
登录界面
![图片1](https://github.com/user-attachments/assets/15d4ae2c-2087-42d7-8b1e-f5589d1e9d39)
注册界面
![图片2](https://github.com/user-attachments/assets/d1749d0f-39ab-4abc-9442-a19f593a6f48)
聊天列表
![图片3](https://github.com/user-attachments/assets/dd1e591d-30bd-47a5-aeda-bfa8cd4d286c)
私聊界面
![图片4](https://github.com/user-attachments/assets/e4b68c24-e81c-4261-a102-5bf998d6a141)
群聊界面
![图片5](https://github.com/user-attachments/assets/aaf0065e-5fd7-46de-b8c6-ea64571bc0cd)
新消息提醒
![图片6](https://github.com/user-attachments/assets/6cf940be-f6d7-4f98-b9d2-ea3fcae24b32)





## 贡献

欢迎提交问题和功能请求！如有兴趣贡献代码，请发起Pull Request。

## 许可证

本项目使用 MIT 许可证，详情请查看 [LICENSE](LICENSE)。

## 联系

如需更多信息，请联系项目维护者。
