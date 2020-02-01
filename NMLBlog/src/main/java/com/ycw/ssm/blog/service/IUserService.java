package com.ycw.ssm.blog.service;

import com.ycw.ssm.blog.entity.User;

import java.util.List;

/**
 * @author ycw
 */
public interface IUserService {
    /**
     *
     * @return
     */
    List<User> listUser();

    void delete(Integer id);

    User getUserByUserName(String userName);

    User getUserByEmail(String userEmail);

    void insertUser(User user);

    User getUserByUserId(Integer id);

    void updateUser(User user);

    User getUserByUserNameOrEmail(String username);
}
