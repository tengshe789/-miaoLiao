package tech.tengshe789.miaoliao.service;

import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.tengshe789.miaoliao.dao.MiaoliaoUserDao;
import tech.tengshe789.miaoliao.domain.MiaoliaoChatMsg;
import tech.tengshe789.miaoliao.domain.MiaoliaoFriendRequest;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;
import tech.tengshe789.miaoliao.fdfs.FastDFSClient;
import tech.tengshe789.miaoliao.utils.FileUtils;
import tech.tengshe789.miaoliao.utils.MD5Utils;
import tech.tengshe789.miaoliao.utils.QRCodeUtils;

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
    private MiaoliaoUserDao userDao;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * @Description: 判断用户名是否存在
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public boolean queryUserPwd(String username, String pwd) throws Exception {
        MiaoliaoUser dataSoureceUser = userDao.getByUsername(username);
        if (dataSoureceUser.getPassword().equals( MD5Utils.getMD5Str(pwd))){
            //需要用equals方法
            return true;
        }else {
            return false;
        }
    }

    /**
     * @Description: 用户注册
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public boolean saveUser(MiaoliaoUser user) throws Exception {
        //设置杂七杂八
        String userId = sid.nextShort();
        user.setId(userId);
        user.setNickname(user.getUsername());
        user.setFaceImage("");
        user.setFaceImageBig("");
        user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        user.setCid(user.getCid());

        /*
         * 设置qrCode
         */
        // qrcode的本地保存路径
        String qrCodePath = "D://"+userId+"QR.png";
        //qrcode的内容
        String content = "miaoliao_qrcode:" + user.getUsername();
        //创建
        qrCodeUtils.createQRCode(qrCodePath,content);
        //转换格式
        MultipartFile qrFile = FileUtils.fileToMultipart(qrCodePath);
        //上传
        String qrCodeUrl = fastDFSClient.uploadQRCode(qrFile);
        //up to database
        user.setQrcode(qrCodeUrl);


        log.info("用户： "+user.getUsername()+"  正在注册");
        return userDao.saveUser(user);
    }

    /**
     * @Description: 修改用户头像
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void updateUserImg(MiaoliaoUser user){
        userDao.updateUserImg(user);
    }

    /**
     * @Description: 修改用户Nickname
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void updateUserNickname(MiaoliaoUser user){
        userDao.updateUserNickname(user);
    }

    /**
     * @Description: 搜索朋友的前置条件
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public Integer preconditionSearchFriends(String myUserId, String friendUsername){
        return 0;
    }

    /**
     * @Description: 根据用户名查询用户对象
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public MiaoliaoUser queryUserInfoByUsername(String username){
        return null;
    }

    /**
     * @Description: 添加好友请求记录，保存到数据库
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void sendFriendRequest(String myUserId, String friendUsername){

    }

    /**
     * @Description: 查询好友请求
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public List<MiaoliaoFriendRequest> queryFriendRequestList(String acceptUserId){
        return null;
    }

    /**
     * @Description: 删除好友请求记录
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void deleteFriendRequest(String sendUserId, String acceptUserId){

    }

    /**
     * @Description: 通过好友请求
     * 				1. 保存好友
     * 				2. 逆向保存好友
     * 				3. 删除好友请求记录
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void passFriendRequest(String sendUserId, String acceptUserId){

    }

    /**
     * @Description: 查询好友列表
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public List<MiaoliaoFriendRequest> queryMyFriends(String userId){
        return null;
    }

    /**
     * @Description: 保存聊天消息到数据库
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public String saveMsg(MiaoliaoChatMsg chatMsg){
        return null;
    }

    /**
     * @Description: 批量签收消息
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void updateMsgSigned(List<String> msgIdList){

    }

    /**
     * @Description: 获取未签收消息列表
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public List<MiaoliaoChatMsg> getUnReadMsgList(String acceptUserId){
        return null;
    }
}
