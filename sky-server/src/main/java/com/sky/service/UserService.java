package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    /**
     *
     * @Author TanYingHao
     * @Description 微信登录，调用微信接口服务
     * @Date 19:01 2023/8/30
     * @Param [userLoginDTO]
     * @return com.sky.entity.User
     **/
    User wxLogin(UserLoginDTO userLoginDTO);

}
