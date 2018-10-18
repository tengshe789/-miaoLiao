package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;

import java.util.List;

@Mapper
@Component
public interface MiaoliaoMyFriendsDao {
    @Select("select my_friend_user_id from miaoliao_my_friends where my_user_id = #{myUserId}")
    public List<String> searchFriendIdByMyId(@Param("myUserId")String myUserId);
}
