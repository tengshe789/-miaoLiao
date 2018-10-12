package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:25
 **/
@Mapper
public interface MiaoliaoUserDao {
    public MiaoliaoUser queryFriendRequestList();
}
