package tech.tengshe789.miaoliao.domain;

import java.util.Date;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:16
 **/
public class MiaoliaoChatMsg {
    private String id;

    private String sendUserId;

    private String acceptUserId;

    private String msg;

    /**
     * 消息是否签收状态
     1：签收
     0：未签收

     */
    private Integer signFlag;

    /**
     * 发送请求的事件
     */
    private Date createTime;
}
