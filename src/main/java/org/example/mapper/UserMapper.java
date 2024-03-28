package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.entity.User;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectByPrimaryKey(Integer id);

    @Update("UPDATE user SET name = #{name} WHERE id = #{id}")
    int updateByPrimaryKey(User user);
}