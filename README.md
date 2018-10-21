# miaoLiao秒聊通讯

### 项目名称：秒聊通讯

秒聊爱啪啪，一个专注聊天的爱啪啪

### 项目技术：

Java 11、SpringBoot 2、MyBatis 、Druid 1.1、Netty、Log4j、Lombok、MUI、H5+，fastDFS

### 开发工具：

IntelliJ IDEA  x64、Hbuilder、MySQL 80、Tomcat、Linux、Maven3、Git、Navicat

### 项目描述：

*秒聊* 是一种快如闪电的聊天应用。专注聊天功能，没有多余的花哨功能，给您最好的聊天体验。ui方面模仿微信界面，功能有及时聊天，通讯录查看。。

### 项目特点：

1. 基于`SpringBoot`，简化了大量项目配置和`maven`依赖，让您更专注于业务开发，独特的分包方式，代码多而不乱。
2. 使用`netty`主从线程模型（请看我[博客](https://blog.tengshe789.tech/2018/08/25/io%E6%A8%A1%E5%9E%8B/#more)），基于`js`的`websocket`相关`api`实现前后端聊天通信

### 项目界面：

![list](http://resume.tengshe789.tech/static/im.jpg)

### 使用

第一步：

打开连接，https://www.cnblogs.com/chiangchou/p/fastdfs.html，在服务端搭建好相应的`fastDFS`

第二步：



### 联系我：

微信：tengshe789

### 版本迭代Update content：

#### 第8版version 0.21

解决下面的bug😝

#### 第7版version 0.20

找了好久的bug，还是没有想明白。

`Service`中第180行的`sendFriendResquest`方法，查找用户为null，造成空指针错误，为什么呢？？？

+ 增加保存好友
+ 增加删除好友

#### 第6版version 0.19

- 增加了添加好友功能

#### 第5版version 0.18

- 增加了搜索好友功能
- UI重构

#### 第4版version 0.17

+ 增加了为每个用户生成二维码功能

+ UI更像微信了
+ 照着阿里规范重新排了一下代码

#### 第3版version 0.16

+ 增加了更新用户nickname功能

#### 第2版version 0.15

+ 完成用户登陆后，可以自由上传图片的功能。后台文件服务器使用的是`fastDFS`搭建

#### 第1版version 0.1

+ 简单聊天列表界面，后端搭建成功，未写功能

后端项目地址：https://github.com/tengshe789/-miaoliao

前端项目地址：https://github.com/tengshe789/miaoliao-frontend

## 推荐参考资料

闪电侠的netty ： https://juejin.im/book/5b4bc28bf265da0f601301161