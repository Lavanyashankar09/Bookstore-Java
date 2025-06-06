package com.lavanya.bookstore.service.impl;

import com.lavanya.bookstore.dto.UserInfoDto;
import com.lavanya.bookstore.entity.UserInfo;
import com.lavanya.bookstore.mapper.UserInfoMapper;
import com.lavanya.bookstore.repository.UserInfoRepository;
import com.lavanya.bookstore.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        UserInfo userInfo = UserInfoMapper.toEntity(userInfoDto);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return UserInfoMapper.toDto(userInfo);
    }
}
