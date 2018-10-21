package tech.tengshe789.miaoliao.controller;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Code;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.tengshe789.miaoliao.bo.UserBO;
import tech.tengshe789.miaoliao.domain.MiaoliaoFriendRequest;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;
import tech.tengshe789.miaoliao.fdfs.FastDFSClient;
import tech.tengshe789.miaoliao.result.CodeMsg;
import tech.tengshe789.miaoliao.result.Result;
import tech.tengshe789.miaoliao.service.UserService;
import tech.tengshe789.miaoliao.utils.FileUtils;
import tech.tengshe789.miaoliao.utils.MD5Utils;
import tech.tengshe789.miaoliao.vo.FriendRequestVO;
import tech.tengshe789.miaoliao.vo.UserVo;

import java.util.List;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-13 09:35
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSClient fastDFSClient;

    /**
     * 用户登陆
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/do_login")
    public Result<UserVo> registOrLogin(@RequestBody MiaoliaoUser user) throws Exception {
        if (StringUtil.isNullOrEmpty(user.getUsername()) ||
                StringUtil.isNullOrEmpty(user.getPassword())) {
            return Result.error(CodeMsg.NULL_USERNAME_PASSWORD);
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if (usernameIsExist) {
            //登陆
            boolean dataSourceUser = userService.queryUserPwd(user.getUsername(),
                    user.getPassword());
            if (dataSourceUser == false) {
                return Result.error(CodeMsg.ERROR_PWD);
            }else {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user, userVo);
                return Result.success(userVo);
            }
        } else {
            //注册
            userService.saveUser(user);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return Result.success(userVo);
    }

    /**
     * 设置用户头像
     * @param userBo
     * @return
     * @throws Exception
     */
    @PostMapping("/upload_face_base64")
    public Result<MiaoliaoUser> uploadFaceBase64(@RequestBody UserBO userBo) throws Exception {
        //定义服务器路径（不能放到c盘，c盘有保护）
        String userFacePath = "D:\\" + userBo.getUserId() + "userface64.png";
        //获取前端base64字符串
        String base64Data = userBo.getFaceData();
        //转换为文件
        FileUtils.base64ToFile(userFacePath,base64Data);
        MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
        //上传
        String url = fastDFSClient.uploadBase64(faceFile);
        log.info(url);

        //获取缩略图的url
        String thump = "_80*80.";
        String[] split = url.split("\\.");
        String thumpImgUrl = split[0] + thump + split[1];

        //更新头像
        MiaoliaoUser user = new MiaoliaoUser();
        user.setId(userBo.getUserId());
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);
        userService.updateUserImg(user);

        return Result.success(user);
    }

    /**
     * 设置用户nickname
     * @param userBo
     * @return
     * @throws Exception
     */
    @PostMapping("/setNickname")
    public Result<MiaoliaoUser> setNickname(@RequestBody UserBO userBo) throws Exception {
        //更新头像
        MiaoliaoUser user = new MiaoliaoUser();
        user.setId(userBo.getUserId());
        user.setNickname(userBo.getNickname());
        userService.updateUserImg(user);
        return Result.success(user);
    }

    /**
     * 搜索好友
     * @param myUserId
     * @param friendUsername
     * @return
     * @throws Exception
     */
    @PostMapping("/search")
    public Result<UserVo> searchUser(@RequestParam("myUserId")String myUserId,
                                     @RequestParam("friendUsername")String friendUsername) throws Exception {
        if (StringUtil.isNullOrEmpty(myUserId) ||
                StringUtil.isNullOrEmpty(friendUsername)){
            return Result.error(CodeMsg.NULL_USER);
        }
        int code = userService.searchFriends(myUserId,friendUsername);
        if (code == 1 ){
            return Result.error(CodeMsg.NULL_USER);
        }else if (code == 2){
            return Result.error(CodeMsg.NAME_IS_MINE);
        }else{
            UserVo userVo = new UserVo();
            MiaoliaoUser friend = userService.queryUserByUsername(friendUsername);
            BeanUtils.copyProperties(friend,userVo);
            return Result.success(userVo);
        }
    }

    /**
     * 添加好友请求
     * @param myUserId
     * @param friendUsername
     * @return
     * @throws Exception
     */
    @PostMapping("/addFriendRequest")
    public Result<String> addFriendRequest(String myUserId, String friendUsername) {
        if (StringUtil.isNullOrEmpty(myUserId) ||
                StringUtil.isNullOrEmpty(friendUsername)){
            return Result.error(CodeMsg.NULL_USER);
        }

        boolean isResquest = userService.sendFriendResquest(myUserId, friendUsername);
        if (isResquest == false){
            return Result.error(CodeMsg.ADD_FRIEND_ERROR);
        }else {
            return Result.success("添加成功！！！");
        }

    }

    /**
     * 查询好友请求
     * @param myUserId
     * @return
     */
    @PostMapping("/queryFriendRequests")
    public Result<List> queryFriendRequests(String myUserId){
        if (StringUtil.isNullOrEmpty(myUserId)){
            return Result.error(CodeMsg.CLIENT_ERROR);
        }
        return Result.success(userService.queryFriendRequestList(myUserId));
    }

    /**
     * 接受对方 通过或忽略好友请求
     * @param acceptUserId
     * @param sendUserId
     * @param operType 1是确认好友请求 2是忽略
     * @return
     */
    @PostMapping("/operFriendRequest")
    public Result<List> operFriendRequest(String acceptUserId, String sendUserId,Integer operType){
        if (StringUtil.isNullOrEmpty(acceptUserId) || StringUtil.isNullOrEmpty(sendUserId) ||
                operType == null){
            return Result.error(CodeMsg.INVALID_DATA);
        }
        if (operType == 1){
            //确认好友请求时
            userService.saveFriends(sendUserId,acceptUserId);
            userService.saveFriends(acceptUserId,sendUserId);
            userService.deleteFriendRequest(sendUserId,acceptUserId);
        }else if (operType == 0){
            //忽略好友请求时
            userService.deleteFriendRequest(sendUserId,acceptUserId);
        }

        //查询好友请求
        List<FriendRequestVO> friendRequestVOS = userService.queryFriendRequestList(acceptUserId);
        return Result.success(friendRequestVOS);
    }

    @PostMapping("/getUnReadMsgList")
    public Result<List> getUnReadMsgList(String myUserId){
        if (StringUtil.isNullOrEmpty(myUserId)){
            return Result.error(CodeMsg.CLIENT_ERROR);
        }
        return Result.success(userService.queryFriendRequestList(myUserId));
    }

    @PostMapping("/myFriends")
    public Result<List> myFriends(String myUserId){
        return null;
    }


}
