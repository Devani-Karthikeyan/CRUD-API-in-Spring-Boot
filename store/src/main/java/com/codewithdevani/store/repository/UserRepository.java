package com.codewithdevani.store.repository;

import com.codewithdevani.store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
