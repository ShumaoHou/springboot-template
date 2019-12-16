package com.st.mysql.mapper;

import com.st.bean.User;
import com.st.mysql.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shumao
 * @description
 * @date 2019/12/6 17:29
 */
@Mapper
public interface SecurityMapper {

    // User

    public UserDTO loginUserByName(String name);

    public User findUserByName(String name);

}
