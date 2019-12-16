package com.st.service;

import com.st.result.UserDTOResult;
import com.st.result.UserResult;

/**
 * @author Shumao
 * @description
 * @date 2019/12/6 15:33
 */
public interface SecurityService {

    // Login

    public UserDTOResult loginUserByName(String name);

    // User

    public UserResult getUserByName(String username);

}
