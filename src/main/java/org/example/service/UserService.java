package org.example.service;

import org.example.annotation.Master;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.entity.User;
import org.example.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Master
    public User getUserByMaster(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }
}