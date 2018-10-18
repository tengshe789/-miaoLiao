package tech.tengshe789.miaoliao.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: miaoliao
 * @description: 好友请求发送方的信息
 * @author: tEngSHe789
 * @create: 2018-10-17 21:51
 **/
@Setter
@Getter
public class FriendRequestVO {
    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;
}
