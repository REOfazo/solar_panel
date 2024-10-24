package com.example.solar_panel.repository;

import com.example.solar_panel.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}
