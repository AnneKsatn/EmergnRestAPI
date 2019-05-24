package com.emergn.EmergnTeskTask.repo;

import com.emergn.EmergnTeskTask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByLogin(String login);
    User findByEmail(String email);
}
