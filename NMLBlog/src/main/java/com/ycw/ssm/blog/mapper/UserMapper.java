package com.ycw.ssm.blog.mapper;

import com.ycw.ssm.blog.entity.User;

import java.util.List;

/**
 * @author ycw
 */
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    void delete(Integer id);

    User getUserByEmail(String userEmail);

    User getUserByUserName(String userName);

    void insertUser(User user);

    User getUserByUserId(Integer id);

    void updateUser(User user);

    User findUserByUserNameOrEmail(String username);
}
