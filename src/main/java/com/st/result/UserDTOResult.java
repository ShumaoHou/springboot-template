package com.st.result;

import com.st.mysql.dto.UserDTO;
import lombok.Data;

/**
 * @author Shumao
 * @description
 * @date 2019/12/15 下午 05:47
 */
@Data
public class UserDTOResult extends Result {

    private UserDTO data = null;

}
