package com.tiny.demo.firstlinecode.javareference;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tinytongtong.tinyutils.LogUtils;

/**
 * Java基础精选
 */
public class JavaBasisActivity extends BaseActivity {
    public static final String TAG = JavaBasisActivity.class.getSimpleName();

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_basis;
    }

    @Override
    protected void buildContentView() {
        //Integer的比较
        Integer a = 128, b = 128;
        Integer c = 127, d = 127;
        LogUtils.INSTANCE.e(TAG, "a==b --> " + (a == b));//false
        LogUtils.INSTANCE.e(TAG, "c==d --> " + (c == d));//true
        /**
         * 其实当我们给一个Integer对象赋一个int值的时候，会调用Integer类的静态方法valueOf，
         * 让我们看下源代码是怎么实现的.
         *   public static Integer valueOf(int i) {
         *      if (i >= IntegerCache.low && i <= IntegerCache.high)
         *          return IntegerCache.cache[i + (-IntegerCache.low)];
         *      return new Integer(i);
         *   }
         *  而在IntegerCache类中，
         *  static final int low = -128;
         *  static final int high = 127;
         * 如果整型字面量的值介于-128到127之间，就不会new一个新的Integer对象，而是直接引用常量池中的Integer对象，
         * 所以上面的运行结果是a==b=false，而c==d=true。
         */

        //String的比较
        LogUtils.INSTANCE.e(TAG, "---------------------------------");
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");

        LogUtils.INSTANCE.e(TAG, "s1 == s2 --> " + (s1 == s2));//true
        LogUtils.INSTANCE.e(TAG, "s1 == s3 --> " + (s1 == s3));//false
        /**
         * 按照==的语法来看， 首先s1、s2、s3是三个不同的对象，常理来说，输出都会是false。然而程序的运行结果确实
         * true、false。第二个输出false可以理解，第一个输出true就又让人费解了。
         * 我们知道一些基本类型的变量和对象的引用变量都是在函数的栈内存中分配，而堆内存中则存放new出来的对象和数组。
         * 然而除此之外还有一块区域叫做常量池。
         * 像我们通常想String s1 = "abc"；这样申明的字符串对象，其值就是存储在常量池中。当我们创建String s1 = "abc"
         * 这样一个对象之后，"abc"就存储到了常量池（也可叫做字符串池）中。
         * 当我们创建引用String s2 = "abc" 的时候，Java底层会优先在常量池中查找是否存在"abc"，如果存在则让s2指向这个值，
         * 不会重新创建，如果常量池中没有则创建并添加的池中。这就是为什么答案是true 和false的原因。
         */
        LogUtils.INSTANCE.e(TAG, "---------------------------------");

        //Integer和int的比较
        Integer j = new Integer(128);
        int k = 128;
        Integer m = new Integer(6);
        Integer n = new Integer(6);
        LogUtils.INSTANCE.e(TAG, "j==k --> " + (j == k));//true
        LogUtils.INSTANCE.e(TAG, "m==n --> " + (m == n));//false
        /**
         * c == d=false，我觉得没什么好说的，可能有的小伙伴要问了不是-128-127被缓存起来了么？
         * 但是我们这里的Integer是new出来的，并不是用的缓存，所以结果是false。
         * a == b=true，大家注意一下这里的b是int类型，当int和Integer做==比较的时候，Integer类型会自动拆箱，
         * 也就是把Integer转成int类型，所以这里进行比较的是int类型的值，所以结果即为true。
         */
    }

    @Override
    protected void initViewData() {

    }
}
