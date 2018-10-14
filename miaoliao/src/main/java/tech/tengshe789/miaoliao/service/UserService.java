package tech.tengshe789.miaoliao.service;

import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.tengshe789.miaoliao.dao.MiaoliaoUserDao;
import tech.tengshe789.miaoliao.domain.MiaoliaoChatMsg;
import tech.tengshe789.miaoliao.domain.MiaoliaoFriendRequest;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;
import tech.tengshe789.miaoliao.utils.MD5Utils;

import java.util.List;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-13 09:42
 **/
@Service
@Slf4j
public class UserService {
    @Autowired
    MiaoliaoUserDao userDao;

    @Autowired
    Sid sid;

    /**
     * @Description: 判断用户名是否存在
     */
    @Transactional
    public boolean queryUsernameIsExist(String username){
        if (userDao.getByUsername(username) == null){
            return false;
        }else {
            return true;
        }
    }

    /**
     * @Description: 查询用户密码是否正确
     */
    @Transactional
    public boolean queryUserPwd(String username, String pwd) throws Exception {
        MiaoliaoUser dataSoureceUser = userDao.getByUsername(username);
        if (dataSoureceUser.getPassword().equals( MD5Utils.getMD5Str(pwd))){//需要用equals方法
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Description: 用户注册
     */
    public boolean saveUser(MiaoliaoUser user) throws Exception {
        user.setId(sid.nextShort());
        user.setNickname(user.getUsername());
        user.setFaceImage("");
        user.setFaceImageBig("");
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user.setQrcode("");
        user.setCid(user.getCid());
        log.info("用户： "+user.getUsername()+"  正在注册");
        return userDao.saveUser(user);
    }

    /**
     * @Description: 修改用户记录
     */
    @Transactional
    public void updateUserInfo(MiaoliaoUser user){
        userDao.updateUserInfo(user);
    }

    /**
     * @Description: 搜索朋友的前置条件
     */
    @Transactional
    public Integer preconditionSearchFriends(String myUserId, String friendUsername){
        return 0;
    }

    /**
     * @Description: 根据用户名查询用户对象
     */
    @Transactional
    public MiaoliaoUser queryUserInfoByUsername(String username){
        return null;
    }

    /**
     * @Description: 添加好友请求记录，保存到数据库
     */
    @Transactional
    public void sendFriendRequest(String myUserId, String friendUsername){

    }

    /**
     * @Description: 查询好友请求
     */
    @Transactional
    public List<MiaoliaoFriendRequest> queryFriendRequestList(String acceptUserId){
        return null;
    }

    /**
     * @Description: 删除好友请求记录
     */
    @Transactional
    public void deleteFriendRequest(String sendUserId, String acceptUserId){

    }

    /**
     * @Description: 通过好友请求
     * 				1. 保存好友
     * 				2. 逆向保存好友
     * 				3. 删除好友请求记录
     */
    @Transactional
    public void passFriendRequest(String sendUserId, String acceptUserId){

    }

    /**
     * @Description: 查询好友列表
     */
    @Transactional
    public List<MiaoliaoFriendRequest> queryMyFriends(String userId){
        return null;
    }

    /**
     * @Description: 保存聊天消息到数据库
     */
    @Transactional
    public String saveMsg(MiaoliaoChatMsg chatMsg){
        return null;
    }

    /**
     * @Description: 批量签收消息
     */
    @Transactional
    public void updateMsgSigned(List<String> msgIdList){

    }

    /**
     * @Description: 获取未签收消息列表
     */
    @Transactional
    public List<MiaoliaoChatMsg> getUnReadMsgList(String acceptUserId){
        return null;
    }
}
