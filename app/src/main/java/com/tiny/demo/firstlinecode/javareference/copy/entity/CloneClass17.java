package com.tiny.demo.firstlinecode.javareference.copy.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Description: Serializable接口和自定义序列化/反序列化方法  实现java深度克隆
 * 针对Object类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:17
 * @Version TODO
 */
public class CloneClass17 implements Serializable{
    private String name;
    private CloneClass18 class18;

    public CloneClass17() {
    }

    public CloneClass17(String name, CloneClass18 class18) {
        this.name = name;
        this.class18 = class18;
    }

    public Object deepClone() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        //从流里读出来
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bais);
        return (oi.readObject());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CloneClass18 getClass18() {
        return class18;
    }

    public void setClass18(CloneClass18 class18) {
        this.class18 = class18;
    }

    @Override
    public String toString() {
        return "CloneClass17{" +
                "name='" + name + '\'' +
                ", class18=" + class18 +
                '}';
    }
}
