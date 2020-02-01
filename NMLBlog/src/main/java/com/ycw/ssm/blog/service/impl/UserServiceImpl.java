package com.ycw.ssm.blog.service.impl;

import com.ycw.ssm.blog.entity.User;
import com.ycw.ssm.blog.mapper.ArticleMapper;
import com.ycw.ssm.blog.mapper.UserMapper;
import com.ycw.ssm.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ycw
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public List<User> listUser() {
        List<User> userList = userMapper.findAll();
        if (userList != null && !userList.isEmpty()) {
            for (User user : userList) {
                Integer articleCount = articleMapper.totalByUserId(user.getUserId());
                user.setArticleCount(articleCount);
            }
        }
        return userList;
    }

    @Override
    public void delete(Integer id) {
        if (id != null) {
            userMapper.delete(id);
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.getUserByUserName(userName);
        return user;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        User user = userMapper.getUserByEmail(userEmail);
        return user;
    }

    @Override
    public void insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userMapper.insertUser(user);
    }

    @Override
    public User getUserByUserId(Integer id) {
        User user = userMapper.getUserByUserId(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User getUserByUserNameOrEmail(String username) {
        User userByUserNameOrEmail = userMapper.findUserByUserNameOrEmail(username);
        return userByUserNameOrEmail;
    }

}
