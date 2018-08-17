package com.tiny.demo.firstlinecode.kotlin.primer.project11;

/**
 * @Description: $desc$
 * @Author wangjianzhou@qding.me
 * @Version $version$
 * @Date 2018/8/17 2:36 PM
 */
public class EqualsJavaDemo {

    static class Abc {
        private Integer a;

        public Abc(Integer a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return String.valueOf(a);
        }
    }

    static class Def {
        private Integer a;

        public Def(Integer a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public static void main(String[] args) {
        String string = "string";
        String newString = new String("string");
        System.out.println(string == newString);
        System.out.println(string.equals(newString));

        Abc abc1 = new Abc(10);
        System.out.println(abc1);
    }
}
