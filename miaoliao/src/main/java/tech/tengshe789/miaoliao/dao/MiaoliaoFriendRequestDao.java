package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaoliao.domain.MiaoliaoFriendRequest;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;
import tech.tengshe789.miaoliao.vo.FriendRequestVO;

import java.util.List;

@Mapper
@Component
public interface MiaoliaoFriendRequestDao {
    @Select("select accept_user_id from miaoliao_friend_request where send_user_id= #{myUserId}")
    public MiaoliaoFriendRequest searchFriendResquest(@Param("myUserId")String myUserId);

    @Insert("insert into miaoliao_friend_request" +
            "(id, send_user_id,accept_user_id,request_date_time) " +
            "values(#{id}, #{sendUserId}, #{acceptUserId}, #{requestDateTime})")
    public MiaoliaoFriendRequest saveFriendRequest(MiaoliaoFriendRequest userRequest);

    @Select("select sender.id as sendUserId,sender.username as sendUsername,  " +
            "sender.face_image as sendFaceImage ,sender.nickname as sendNickname" +
            "from miaoliao_friend_request fr left join miaoliao_user sender" +
            "on fr.send_user_id = sender" +
            "where fr.accept_user_id = #{acceptUserId}")
    public List<FriendRequestVO> queryFriendRequestList(@Param("acceptUserId") String acceptUserId);

    @Delete("delete from miaoliao_friend_request " +
            "where send_user_id = #{sendId} and accept_user_id = #{acceptUserId}")
    public void deleteFriendRequestList(@Param("sendId") String sendId,
                                        @Param("acceptUserId")String acceptUserId);

}
