package com.agioe.tool.data.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yshen
 * @since 2018/10/10
 */
public class ObjectUtil<E> {
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

    public static <E> List<E> toEntity(List<Object> objList) {
        List<E> entityList = new ArrayList<E>();
        for (Object obj : objList) {
            E entity = (E) obj;
            entityList.add(entity);
        }
        return entityList;
    }
}
