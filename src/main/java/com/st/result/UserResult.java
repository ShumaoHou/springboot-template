package com.st.result;

import com.st.bean.User;
import lombok.Data;

/**
 * @author Shumao
 * @description
 * @date 2019/12/15 下午 05:47
 */
@Data
public class UserResult extends Result {

    private User data = null;

}
