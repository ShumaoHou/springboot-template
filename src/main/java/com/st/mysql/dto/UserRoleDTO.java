package com.st.mysql.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Shumao
 * @description
 * @date 2019/12/14 下午 08:19
 */
@Data
public class UserRoleDTO {

    @Id
    private Long id;

    private Long userId;

    private List<Long> roleIdList;

}
