package com.fire.begin.compare.base;

/**
 * @Auther: nacl
 * @Date: 2018/10/3 0003 11:13
 * @Description:
 */
public class ComparatorFactory {

    public static ModelCompare getComparator(Class modleClass) {

        return new SingleValueCompare();

    }
}
