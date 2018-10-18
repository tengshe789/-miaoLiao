package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaoliao.domain.MiaoliaoFriendRequest;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;

import java.util.List;

@Mapper
@Component
public interface MiaoliaoFriendRequestDao {
    @Select("select accept_user_id from miaoliao_friend_request where send_user_id = #{myUserId}")
    public MiaoliaoFriendRequest searchFriendResquest(@Param("myUserId")String myUserId);

    @Insert("insert into miaoliao_friend_request" +
            "(id, send_user_id,accept_user_id,request_date_time) " +
            "values(#{id}, #{sendUserId}, #{acceptUserId}, #{requestDateTime})")
    public MiaoliaoFriendRequest saveFriendRequest(MiaoliaoFriendRequest userRequest);


}
