package com.agriculture.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agriculture.user_service.service.model.User;
import java.util.Optional;

//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
//}

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
