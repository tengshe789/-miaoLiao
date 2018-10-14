package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.tengshe789.miaoliao.domain.MiaoliaoUser;

/**
 * @program: miaoliao
 * @description:
 * @author: tEngSHe789
 * @create: 2018-10-12 21:25
 **/
@Mapper
@Component
public interface MiaoliaoUserDao {
    @Select("select * from miaoliao_user where id = #{id}")
    public MiaoliaoUser getById(@Param("id")String id);

    @Select("select * from miaoliao_user where username = #{username}")
    public MiaoliaoUser getByUsername(@Param("username")String username);

    @Insert("insert into miaoliao_user(id, username,password,face_image,face_image_big," +
            "nickname,qrcode,cid) values(#{id}, #{username}, #{password}, #{faceImage}, #{faceImageBig}, " +
            "#{nickname},#{qrcode},#{cid})")
    public boolean saveUser(MiaoliaoUser user);

    @Update("update miaoliao_user set face_image =#{faceImage},face_image_big = #{faceImageBig}" +
                        " where id = #{id}")
    public void updateUserInfo(MiaoliaoUser user);




}
