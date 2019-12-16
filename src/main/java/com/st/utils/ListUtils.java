package com.st.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shumao
 * @description
 * @date 2019/12/16 下午 01:51
 */
public class ListUtils {

    /**
     * 获得 largeList 不在 smallList 的剩余列表，相当于 largeList - smallList
     * @param largeList 原始列表
     * @param smallList 被减列表
     * @param <T> 列表元素类型
     * @return subList 剩余列表
     */
    public static <T> List<T> getSubList(List<T> largeList, List<T> smallList) {
        List<T> subList = new ArrayList<>();
        for(T t : largeList) {
            if (!smallList.contains(t)) {
                subList.add(t);
            }
        }
        return subList;
    }

}
