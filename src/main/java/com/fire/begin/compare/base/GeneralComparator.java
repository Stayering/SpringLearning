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
        if (Objects.equals(newData,oldData)) {
            return null;
        }


        List<Diff> subDiffList = new ArrayList<>();

        if (newData instanceof List) {
            List newList = (List) newData;
            List oldList = (List) oldData;

            for (int i = 0; i < newList.size(); i++) {
                Diff diff = compare(newList.get(i), oldList.get(i));
                if (diff != null) {
                    subDiffList.add(diff);
                }

            }

        } else {
            Class classInfo = newData.getClass();

            if (ReflectObjectUtils.isSimpleClassType(classInfo)) {
                return simpleObjectCompare(newData, oldData);

            } else {
                Field[] fields = classInfo.getDeclaredFields();

                for (Field field : fields) {

                    field.setAccessible(true);

                    Object newSubData = ReflectObjectUtils.getValueByField(newData, field);

                    Object oldSubData = ReflectObjectUtils.getValueByField(oldData, field);

                    //处理基本类型或者基本类型的封装类或者String类型的属性
                    if (ReflectObjectUtils.isSimpleClassType(field.getType())){
                        if (!Objects.equals(newSubData, oldSubData)) {
                            Diff diff = new Diff();
                            diff.setKey(field.getName());

                            diff.setType(field.getType());

                            diff.setNewModle(newSubData);

                            diff.setOldModel(oldSubData);

                            subDiffList.add(diff);
                        }

                    } else{

                        Diff diff = compare(newSubData, oldSubData);

                        if (diff != null) {
                            subDiffList.add(diff);
                        }
                    }
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


    /**
     * 基本类型比较
     *
     * @param newData
     * @param oldData
     * @return
     */
    private Diff simpleObjectCompare(Object newData, Object oldData) {

        Class classInfo = newData.getClass();
        if (!Objects.equals(newData, oldData)) {
            Diff diff = new Diff();
            diff.setKey(classInfo.getName());

            diff.setType(classInfo);

            diff.setNewModle(newData);

            diff.setOldModel(oldData);

            return diff;
        }
        return null;

    }

}
