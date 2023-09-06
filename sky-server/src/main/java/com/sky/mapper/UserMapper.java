package com.sky.mapper;

import com.sky.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

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

    /**
     *
     * @Author TanYingHao
     * @Description 根据id查询用户
     * @Date 15:32 2023/9/3
     * @Param [userId]
     * @return com.sky.entity.User
     **/
    @Select("select * from user where id = #{userId}")
    User getById(Long userId);
    /**
     *
     * @Author TanYingHao
     * @Description 根据传入的beginTime和endTime查询当前的用户
     * @Date 19:52 2023/9/6
     * @Param [map]
     * @return Integer
     **/
    Integer countByMap(Map map);
}
