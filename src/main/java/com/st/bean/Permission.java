package com.st.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Shumao
 * @description 权限
 * @date 2019/12/6 15:29
 */
@Data
public class Permission {

    @Id
    private Long id;
    private String name;
    private String description;

}
