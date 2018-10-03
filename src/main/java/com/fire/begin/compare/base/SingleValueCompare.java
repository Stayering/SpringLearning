package com.fire.begin.compare.base;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @Auther: nacl
 * @Description: 基本属性比较器
 */
@Component
public class SingleValueCompare implements ModelCompare {


    @Override
    public Diff compare(Object newData, Object oldData) {

        if (ClassUtils.isPrimitiveOrWrapper(newData.getClass()) || newData.getClass().equals(String.class)) {

            if (newData.equals(oldData)) {
                return null;
            }
            Diff diff = new Diff();

            diff.setKey(newData.getClass().getName());
            diff.setNewModle(newData);
            diff.setOldModel(oldData);
            return diff;
        }
        return null;
    }
}
