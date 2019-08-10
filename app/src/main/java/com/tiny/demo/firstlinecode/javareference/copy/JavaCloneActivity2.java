package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass3;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass4;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass5;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对List类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:04
 * @Version TODO
 */
public class JavaCloneActivity2 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_2)
    Button btnJavaClone2;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass3 cloneClass3;
    private CloneClass3 cloneClass31;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone2;
    }

    @Override
    protected void buildContentView() {
        cloneClass3 = new CloneClass3();
        cloneClass3.setName("cloneClass3");
        List<CloneClass4> cloneClass4List = new ArrayList<>();
        for (int j = 0; j < 1; j++) {
            CloneClass4 cloneClass4 = new CloneClass4();
            cloneClass4.setName("cloneClass3" + j);
            List<CloneClass5> cloneClass5List = new ArrayList<>();
            for (int m = 0; m < 1; m++) {
                CloneClass5 cloneClass5 = new CloneClass5();
                cloneClass5.setName("cloneClass5" + m);
                cloneClass5List.add(cloneClass5);
            }
            cloneClass4.setClass5List(cloneClass5List);
            cloneClass4List.add(cloneClass4);
        }
        cloneClass3.setClass4List(cloneClass4List);

        cloneClass31 = (CloneClass3) cloneClass3.clone();
    }

    @Override
    protected void initViewData() {
        //展示原有数据。
        showData(txt1);
    }

    @OnClick(R.id.btn_java_clone_2)
    public void onViewClicked() {
        cloneClass31.setName("abc");
        cloneClass31.getClass4List().get(0).setName("def");
        cloneClass31.getClass4List().get(0).getClass5List().get(0).setName("ghi");
        showData(txt2);
    }

    private void showData(TextView txt1) {
        txt1.setText("");
        txt1.append(cloneClass3.toString());
        txt1.append("\n");
        txt1.append(cloneClass31.toString());
        txt1.append("\n");
        txt1.append("cloneClass3.hashCode() --> " + cloneClass3.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass3.getClass4List().hashCode() --> " + cloneClass3.getClass4List().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass3.getClass4List().get(0).hashCode() --> " + cloneClass3.getClass4List().get(0).hashCode());
        txt1.append("\n");
        txt1.append("cloneClass31.hashCode() --> " + cloneClass31.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass31.getClass4List().hashCode() --> " + cloneClass31.getClass4List().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass31.getClass4List().get(0).hashCode() --> " + cloneClass31.getClass4List().get(0).hashCode());
        txt1.append("\n");
    }
}
