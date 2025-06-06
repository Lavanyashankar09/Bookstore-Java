package com.lavanya.bookstore.repository;

import com.lavanya.bookstore.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByUserName(String userName);

}
