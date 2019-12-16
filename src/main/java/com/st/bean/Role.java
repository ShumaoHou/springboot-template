package com.st.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shumao
 * @description 角色
 * @date 2019/12/6 15:29
 */
@Data
public class Role {

    @Id
    private Long id;
    private String name;
    private String description;
    private List<Permission> permissions = new ArrayList<>();

}
