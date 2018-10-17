package tech.tengshe789.miaoliao.service;

import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.tengshe789.miaoliao.dao.MiaoliaoMyFriendsDao;
import tech.tengshe789.miaoliao.dao.MiaoliaoUserDao;
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
    private MiaoliaoMyFriendsDao miaoliaoMyFriendsDao;

    @Autowired
    private Sid sid;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * 判断用户名是否存在
     * @param username
     * @return
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
     * 查询用户密码是否正确
     * @param username
     * @param pwd
     * @return
     * @throws Exception
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
     * 用户注册
     * @param user
     * @return
     * @throws Exception
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
     * 修改用户头像
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void updateUserImg(MiaoliaoUser user){
        userDao.updateUserImg(user);
    }

    /**
     * 修改用户Nickname
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public void updateUserNickname(MiaoliaoUser user){
        userDao.updateUserNickname(user);
    }

    /**
     * @Description: 搜索朋友
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor =  {Exception.class})
    public Integer searchFriends(String myUserId, String friendUsername){
        MiaoliaoUser friend = userDao.getByUsername(friendUsername);

        //查询用户是否存在
        if (friend == null){
            //1 表示不存在
            return 1;
        }
        //先查询用户是否是你自己的
        if(myUserId.equalsIgnoreCase(friend.getId())){
            //2 用户是自己
            return 2;
        }

        //如果已经是你好友了
        List<String> friendList = miaoliaoMyFriendsDao.searchFriendIdByMyId(myUserId);
        if(friendList.contains(friendUsername)){
            return 3;
        }

        return 0;
    }



}
