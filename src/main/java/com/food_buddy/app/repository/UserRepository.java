package com.food_buddy.app.repository;

import com.food_buddy.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    @Query(value="Select * from foodbuddy.users u where u.username=?1 or u.email=?1", nativeQuery = true)
    public User findByUsernameOrEmail(String usernameOrEmail);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
