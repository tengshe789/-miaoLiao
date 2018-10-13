package tech.tengshe789.miaoliao.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:37
 **/
@Component
@Slf4j
public class WebSocketServer {
    private static class BootWebSocketServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    private EventLoopGroup masterGroup;
    private EventLoopGroup slaveGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WebSocketServer() {
        masterGroup = new NioEventLoopGroup();
        slaveGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(masterGroup, slaveGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitialzer());
    }

    public static WebSocketServer getInstance() {
        return BootWebSocketServer.instance;
    }

    public void start(){
        this.future = server.bind(8888);
        log.info("netty 启动完毕");
    }
}
