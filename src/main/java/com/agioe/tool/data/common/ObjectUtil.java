package com.agioe.tool.data.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yshen
 * @since 2018/10/10
 */
public class ObjectUtil {
    /**
     * object list 2 entity list
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E> List<Object> toObject(List<E> list) {
        List<Object> objList = new ArrayList<Object>();
        for (Object e : list) {
            Object obj = (Object) e;
            objList.add(obj);
        }
        return objList;
    }
}
