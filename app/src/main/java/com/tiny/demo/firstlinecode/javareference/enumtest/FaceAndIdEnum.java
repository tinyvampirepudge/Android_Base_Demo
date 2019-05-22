package com.tiny.demo.firstlinecode.javareference.enumtest;

/**
 * @Description: TODO
 * @Author wangjianzhou@qding.me
 * @Date 2019-05-22 15:02
 * @Version TODO
 */
public enum FaceAndIdEnum {
    FACE(0), IDFRONT(1), idback(2);
    private int id;

    FaceAndIdEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static void main(String[] args) {
        FaceAndIdEnum enum1 = FaceAndIdEnum.FACE;
        System.out.println("enum1:" + enum1);
        System.out.println("enum1.getId():" + enum1.getId());
        FaceAndIdEnum enum2 = FaceAndIdEnum.IDFRONT;
        System.out.println("enum2:" + enum2);
        System.out.println("enum2.getId():" + enum2.getId());
        FaceAndIdEnum enum3 = FaceAndIdEnum.idback;
        System.out.println("enum3:" + enum3);
        System.out.println("enum3.getId():" + enum3.getId());
    }
}
