package com.tiny.demo.firstlinecode.javareference.io.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019/5/13 2:49 PM
 * @Version TODO
 */
public class ABean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String age;
    private Boolean gender;

    public ABean() {
    }

    public ABean(String name, String age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


//    private void writeObject(ObjectOutputStream out) throws IOException {
//        out.defaultWriteObject();
//    }
//
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        in.defaultReadObject();
//    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("name", String.class),
            new ObjectStreamField("age", String.class),
            new ObjectStreamField("gender", Boolean.class),
    };

    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("name", this.name);
        fields.put("age", this.age);
        fields.put("gender", this.gender);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        name = (String) fields.get("name", name);
        age = (String) fields.get("age", age);
        gender = (Boolean) fields.get("gender", gender);
    }

    @Override
    public String toString() {
        return "ABean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
