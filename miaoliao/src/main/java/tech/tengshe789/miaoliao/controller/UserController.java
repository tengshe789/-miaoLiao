package tech.tengshe789.miaoliao.controller;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;
import tech.tengshe789.miaoliao.result.CodeMsg;
import tech.tengshe789.miaoliao.result.Result;
import tech.tengshe789.miaoliao.service.UserService;
import tech.tengshe789.miaoliao.utils.MD5Utils;
import tech.tengshe789.miaoliao.vo.UserVo;

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
    UserService userService;

    @PostMapping("/do_login")
    @ResponseBody
    public Result<UserVo> registOrLogin(@RequestBody MiaoliaoUser user) throws Exception {
        if (StringUtil.isNullOrEmpty(user.getUsername()) ||
                StringUtil.isNullOrEmpty(user.getPassword())) {
            return Result.error(CodeMsg.NULL_USERNAME_PASSWORD);
        }
        boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
        if (usernameIsExist) {
            //登陆
            MiaoliaoUser dataSourceUser = userService.queryUserPwd(user.getUsername(),
                    MD5Utils.getMD5Str(user.getPassword()));
            if (dataSourceUser == null) {
                return Result.error(CodeMsg.ERROR_PWD);
            }else {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(dataSourceUser, userVo);
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

}
