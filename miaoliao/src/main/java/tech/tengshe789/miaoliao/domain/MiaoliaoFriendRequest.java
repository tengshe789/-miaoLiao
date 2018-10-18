package tech.tengshe789.miaoliao.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:17
 **/
@Getter
@Setter
public class MiaoliaoFriendRequest {

    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Date requestDateTime;

}
