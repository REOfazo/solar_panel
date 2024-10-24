package com.example.solar_panel.service;

import com.example.solar_panel.entity.UserEntity;
import com.example.solar_panel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UserService {
        private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }
    public UserEntity getOne(UUID id) {
        return userRepository.getOne(id);
    }
    public String save(UserEntity user) {
        userRepository.save(user);
        return "Column saved is successfully!";
    }
    public String update(UUID id, UserEntity user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setLocation(user.getLocation());
        userEntity.setPassword(user.getPassword());
        userEntity.setEMail(user.getEMail());
        userEntity.setFullName(user.getFullName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userEntity);
        return "Column updated is successfully!";
    }
    public String deleteOne(UUID id) {
        userRepository.deleteById(id);
        return "Colum deleted is successfully!";
    }
    public String deleteAll() {
        userRepository.deleteAll();
        return "Table deleted is successfully!";
    }
}
