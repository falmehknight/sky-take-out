package com.sky.mapper;

import com.sky.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     *
     * @Author TanYingHao
     * @Description 根据openid查询信息
     * @Date 19:26 2023/8/30
     * @Param [openid]
     * @return com.sky.entity.User
     **/
    @Select("select * from user where openid = #{openid}")
    User getByOpenId(String openid);
    /**
     *
     * @Author TanYingHao
     * @Description 插入新用户
     * @Date 19:31 2023/8/30
     * @Param [user]
     * @return void
     **/
    void insert(User user);
}
