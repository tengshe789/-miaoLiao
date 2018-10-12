package tech.tengshe789.miaoliao.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 20:20
 **/
@RestController
@RequestMapping("/demo")
public class SampleController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello wordld";
    }
}
