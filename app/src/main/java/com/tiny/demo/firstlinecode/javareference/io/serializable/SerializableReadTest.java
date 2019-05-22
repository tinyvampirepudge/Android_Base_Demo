package com.tiny.demo.firstlinecode.javareference.io.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019/5/13 2:48 PM
 * @Version TODO
 */
public class SerializableReadTest {
    public static void main(String[] args) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("output.txt"));
            ABean aBean = (ABean) ois.readObject();
            System.out.println("result:" + aBean);
            ois.close();
            System.out.println("read success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
