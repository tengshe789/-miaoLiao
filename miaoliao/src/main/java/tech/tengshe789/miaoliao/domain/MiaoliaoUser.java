package tech.tengshe789.miaoliao.domain;

import lombok.Data;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:16
 **/
@Data
public class MiaoliaoUser {
    private String id;

    /**
     * 用户名，账号，秒聊号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */
    private String faceImage;

    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 新用户注册后默认后台生成二维码，并且上传到fastdfs
     */
    private String qrcode;

    private String cid;


}
