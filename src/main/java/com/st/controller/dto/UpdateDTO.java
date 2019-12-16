package com.st.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Shumao
 * @description
 * @date 2019/12/16 下午 02:26
 */
@Data
public class UpdateDTO {

    private List<Long> oldList;

    private List<Long> newList;

}
