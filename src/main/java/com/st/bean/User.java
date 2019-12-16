package com.st.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shumao
 * @description 用户
 * @date 2019/12/6 15:28
 */
@Data
public class User {

    @Id
    private Long id;
    private String name;
    private String password;
    private String introduction;
    private String avatar;
    private List<Role> roles = new ArrayList<>();

}
