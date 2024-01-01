package com.example.web.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.web.model.User;

@Mapper
public interface UserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = "username,password,introduction,created_date";
    String SELECT_FIELDS = "id,username,password,introduction,created_date";

    int addUser(User user);
    User getUserByUsername(@Param("username") String username);
    User getUserById(@Param("id") Long id);
}
