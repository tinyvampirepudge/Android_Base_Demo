package com.tiny.demo.firstlinecode.javareference.copy;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass17;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass18;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass19;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Java序列化和反序列化
 * note:
 * ①所有需要深复制的类都需要实现Serializable接口
 * ②在最外层的Object里面实现deepClone()方法即可
 * ③内部引用的对象的地址值虽然是相同的，但是对复制后的对象进行修改，是不影响原对象的。
 */
public class JavaCloneActivity7 extends BaseActivity {
    public static final String TAG = JavaCloneActivity7.class.getSimpleName();

    private CloneClass17 cloneClass17;
    private CloneClass17 cloneClass171;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone7;
    }

    @Override
    protected void buildContentView() {
        cloneClass17 = new CloneClass17();
        cloneClass17.setName("CloneClass17");
        CloneClass18 cloneClass18 = new CloneClass18();
        cloneClass18.setName("CloneClass18");
        List<CloneClass19> cloneClass19List = new ArrayList<>();
        for (int j = 0; j < 1; j++) {
            CloneClass19 cloneClass19 = new CloneClass19();
            cloneClass19.setName("CloneClass19");
            HashMap<String, CloneClass20> class20HashMap = new HashMap<>();
            CloneClass20 cloneClass20 = new CloneClass20();
            cloneClass20.setName("CloneClass20");
            class20HashMap.put("CloneClass20", cloneClass20);
            cloneClass19.setMap(class20HashMap);
            cloneClass19List.add(cloneClass19);
        }
        cloneClass18.setClass19List(cloneClass19List);
        cloneClass17.setClass18(cloneClass18);

        try {
            cloneClass171 = (CloneClass17) cloneClass17.deepClone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.e(TAG, "cloneClass17.toString() --> " + cloneClass17.toString());
        LogUtils.e(TAG, "cloneClass171.toString() --> " + cloneClass171.toString());
        LogUtils.e(TAG, "cloneClass17.hashCode() --> " + cloneClass17.hashCode());
        LogUtils.e(TAG, "cloneClass17.getClass18().getName().hashCode() --> " + cloneClass17.getClass18().getName().hashCode());
        LogUtils.e(TAG, "cloneClass171.hashCode() --> " + cloneClass171.hashCode());
        LogUtils.e(TAG, "cloneClass171.getClass18().getName().hashCode() --> " + cloneClass171.getClass18().getName().hashCode());

        cloneClass171.setName("abc");
        cloneClass171.getClass18().setName("def");
        cloneClass171.getClass18().getClass19List().get(0).setName("ghi");
        cloneClass171.getClass18().getClass19List().get(0).getMap().get("CloneClass20").setName("jkl");
        LogUtils.e(TAG, "after cloneClass17.toString() --> " + cloneClass17.toString());
        LogUtils.e(TAG, "after cloneClass171.toString() --> " + cloneClass171.toString());
    }

    @Override
    protected void initViewData() {

    }
}
