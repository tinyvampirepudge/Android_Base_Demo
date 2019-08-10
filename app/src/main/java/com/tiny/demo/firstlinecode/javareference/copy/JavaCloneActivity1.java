package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass1;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对Object类型
 *
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:04
 * @Version TODO
 */
public class JavaCloneActivity1 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_obj_with_obj)
    Button btnJavaCloneObjWithObj;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass1 cloneClass1;
    private CloneClass1 cloneClass11;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone1;
    }

    @Override
    protected void buildContentView() {
        initCloneObjectContainsObject();
    }

    private void initCloneObjectContainsObject() {
        cloneClass1 = new CloneClass1();
        CloneClass2 cloneClass2 = new CloneClass2();
        cloneClass2.setName2("def");
        cloneClass1.setName("abc");
        cloneClass1.setCloneClass2(cloneClass2);

        cloneClass11 = (CloneClass1) cloneClass1.clone();
        txt1.append(cloneClass1.toString());
        txt1.append("\n");
        txt1.append(cloneClass11.toString());
        txt1.append("\n");
        txt1.append("cloneClass1.hashCode(): " + cloneClass1.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass1.getCloneClass2().hashCode(): " + cloneClass1.getCloneClass2().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass11.hashCode(): " + cloneClass11.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass11.getCloneClass2().hashCode(): " + cloneClass11.getCloneClass2().hashCode());
        txt1.append("\n");
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_java_clone_obj_with_obj)
    public void onViewClicked() {
        cloneClass11.setName("ghi");
        cloneClass11.getCloneClass2().setName2("jkl");
        txt2.append(cloneClass1.toString());
        txt2.append("\n");
        txt2.append(cloneClass11.toString());
    }
}
