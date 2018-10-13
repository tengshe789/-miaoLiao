package tech.tengshe789.miaoliao.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * from miaoliao_user where username = #{username}")
    public MiaoliaoUser getByUsername(@Param("username")String username);

    @Insert("insert into miaoliao_user(id, username,password,face_image,face_image_big," +
            "nickname,qrcode,cid) values(#{id}, #{username}, #{password}, #{faceImage}, #{faceImageBig}, " +
            "#{nickname},#{qrcode},#{cid})")
    public boolean saveUser(MiaoliaoUser user);

}
