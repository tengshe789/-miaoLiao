package tech.tengshe789.miaoliao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"tech.tengshe789", "org.n3r.idworker"})
public class MiaoliaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoliaoApplication.class, args);
    }
}
