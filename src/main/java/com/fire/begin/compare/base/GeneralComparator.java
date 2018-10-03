package com.fire.begin.compare.base;

import com.fire.begin.utils.ReflectObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: nacl
 * @Date: 2018/10/3 0003 10:29
 * @Description: 通用属性比较器
 */

@Component
public class GeneralComparator implements ModelCompare {

    @Override
    public Diff compare(Object newData, Object oldData) {
        if (newData.equals(oldData)) {
            return null;
        }
        List<Diff> subDiffList = new ArrayList<>();
        Class classInfo = newData.getClass();
        Field[] fields = classInfo.getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);


            Object newSubData = ReflectObjectUtils.getValueByField(newData, field);

            Object oldSubData = ReflectObjectUtils.getValueByField(oldData, field);

            if (ClassUtils.isPrimitiveOrWrapper(field.getType()) || String.class.equals(field.getType())) {


                if (!Objects.equals(newSubData, oldSubData)) {
                    Diff diff = new Diff();
                    diff.setKey(field.getName());

                    diff.setType(field.getType());

                    diff.setNewModle(newSubData);

                    diff.setOldModel(oldSubData);

                    subDiffList.add(diff);
                }


            } else {


                Diff diff = compare(newSubData, oldSubData);

                if (diff != null) {
                    subDiffList.add(diff);
                }
            }

        }

        if (subDiffList.isEmpty()) {
            return null;
        } else {

            Diff diff = new Diff();

            diff.setKey(newData.getClass().getName());
            diff.setType(newData.getClass());

            diff.setNewModle(newData);
            diff.setOldModel(oldData);

            diff.setSubDiff(subDiffList);
            return diff;
        }

    }
}
