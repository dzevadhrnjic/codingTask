package com.zira.codingtask.service;

import com.zira.codingtask.controller.model.AccessTokenDTO;
import com.zira.codingtask.repository.UserRepository;
import com.zira.codingtask.exception.InvalidEmailOrPasswordException;
import com.zira.codingtask.repository.model.User;
import com.zira.codingtask.repository.model.UserLogin;
import com.zira.codingtask.util.HashUtils;
import com.zira.codingtask.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    HashUtils hashUtils = new HashUtils();
    TokenUtil tokenUtil = new TokenUtil();

    @Autowired
    UserRepository userRepository;

    public AccessTokenDTO loginUser(UserLogin userLogin) {

        AccessTokenDTO accessToken = new AccessTokenDTO();

        User loginUser = userRepository.getUser(userLogin.getEmail(),
                hashUtils.generateHash(userLogin.getPassword()));

        if (loginUser == null) {
            throw new InvalidEmailOrPasswordException("Invalid email or password, try again");
        } else {
            Long userId = loginUser.getId();
            accessToken.setAccessToken(tokenUtil.jwt(userId));
        }
        return accessToken;
    }
}
