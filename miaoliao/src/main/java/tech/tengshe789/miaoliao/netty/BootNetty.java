package tech.tengshe789.miaoliao.netty;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: miaoliao
 * @description: 当spring 容器将所有的bean都初始化完成后，就启动netty
 * @author: tEngSHe789
 * @create: 2018-10-12 21:48
 **/
@Component
public class BootNetty implements ApplicationListener<ContextRefreshedEvent> {
// spring容器初始化完成后就会执行该方法。
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 防止有两个context
        if (event.getApplicationContext().getParent() == null){
            WebSocketServer.getInstance().start();
        }
    }
}
