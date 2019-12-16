package com.st.service.impl;

import com.st.bean.User;
import com.st.mysql.dto.UserDTO;
import com.st.mysql.mapper.SecurityMapper;
import com.st.result.UserDTOResult;
import com.st.result.UserResult;
import com.st.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shumao
 * @description
 * @date 2019/12/6 15:33
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityMapper securityMapper;

    // User

    @Override
    public UserDTOResult loginUserByName(String name) {
        UserDTOResult result = new UserDTOResult();
        UserDTO userDTO = securityMapper.loginUserByName(name);
        result.setData(userDTO);
        return result;
    }

    @Override
    public UserResult getUserByName(String username) {
        UserResult result = new UserResult();
        User user = securityMapper.findUserByName(username);
        result.setData(user);
        return result;
    }

}
