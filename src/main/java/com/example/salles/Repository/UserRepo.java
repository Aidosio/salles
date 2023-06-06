package com.example.salles.Repository;

import com.example.salles.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(String phone);
    @Query("SELECT u FROM User u WHERE u.role = 'OWNER'")
    List<User> findAllOwners();
}
