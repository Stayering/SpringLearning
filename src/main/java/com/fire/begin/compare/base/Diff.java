package com.fire.begin.compare.base;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Diff<M> {


    private String key;

    private Class  type;
    private M oldModel;
    private M newModle;
    private List<Diff> subDiff;


}
