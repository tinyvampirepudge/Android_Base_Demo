package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: Serializable接口和自定义序列化/反序列化方法  实现java深度克隆
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:17
 * @Version TODO
 */
public class CloneClass18 implements Serializable {
    private String name;
    private List<CloneClass19> class19List;

    public CloneClass18() {
    }

    public CloneClass18(String name, List<CloneClass19> class19List) {
        this.name = name;
        this.class19List = class19List;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CloneClass19> getClass19List() {
        return class19List;
    }

    public void setClass19List(List<CloneClass19> class19List) {
        this.class19List = class19List;
    }

    @Override
    public String toString() {
        return "CloneClass18{" +
                "name='" + name + '\'' +
                ", class19List=" + class19List +
                '}';
    }
}
