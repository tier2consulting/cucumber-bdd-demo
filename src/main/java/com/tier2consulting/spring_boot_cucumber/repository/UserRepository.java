package com.tier2consulting.spring_boot_cucumber.repository;

import com.tier2consulting.spring_boot_cucumber.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getAllByUsernameLike(String username);

}