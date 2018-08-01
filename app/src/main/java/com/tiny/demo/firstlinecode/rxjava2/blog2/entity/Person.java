package com.tiny.demo.firstlinecode.rxjava2.blog2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 *
 * @author tiny
 * @date 2018/6/7 上午12:53
 */
public class Person {
    private String name;
    private List<Plan> planList = new ArrayList<>();

    public Person(String name, List<Plan> planList) {
        this.name = name;
        this.planList = planList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
