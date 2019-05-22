package com.tiny.demo.firstlinecode.javareference.io.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019/5/13 2:45 PM
 * @Version TODO
 */
public class SerializableWriteTest {
    public static void main(String[] args) {
        ABean aBean = new ABean("王蛋蛋的father", "" + new Random().nextInt(100), true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("output.txt"));
            oos.writeObject(aBean);
            oos.close();
            System.out.println("write success aBean:" + aBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
