package tech.tengshe789.miaoliao.domain;

import lombok.Data;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:16
 **/
@Data
public class MiaoliaoMyFriends {

    private String id;

    /**
     * 用户id
     */
    private String myUserId;

    /**
     * 用户的好友id
     */
    private String myFriendUserId;

}
