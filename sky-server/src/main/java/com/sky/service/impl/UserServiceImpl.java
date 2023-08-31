package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description 用户接口
 * @Author 谭颍豪
 * @Date 2023/8/30 19:02
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    private static final String WX_LOG_IN = "https://api.weixin.qq.com/sns/jscode2session";
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信接口服务，获取当前微信用户的openid
        String openid = getOpenId(userLoginDTO.getCode());
        // 判断openid是否为空，如果为空，则抛出业务异常
        if(openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 判断当前用户是否为新用户
        User user= userMapper.getByOpenId(openid);
        // 如果是新用户，自动完成注册
        if(user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        // 返回用户对象
        return user;
    }

    private String getOpenId(String code) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("appid", weChatProperties.getAppid());
        paraMap.put("secret", weChatProperties.getSecret());
        paraMap.put("js_code", code);
        paraMap.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOG_IN, paraMap);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}
