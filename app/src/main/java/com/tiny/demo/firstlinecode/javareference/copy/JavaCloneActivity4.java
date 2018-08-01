package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass10;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass9;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */
public class JavaCloneActivity4 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_2)
    Button btnJavaClone2;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass9 cloneClass9;
    private CloneClass9 cloneClass91;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone4;
    }

    @Override
    protected void buildContentView() {
        cloneClass9 = new CloneClass9("CloneClass9", new CloneClass10("CloneClass10"));
        cloneClass91 = cloneClass9.clone();
    }

    @Override
    protected void initViewData() {
        showData(txt1);
    }

    @OnClick(R.id.btn_java_clone_2)
    public void onViewClicked() {
        cloneClass91.setName("afasdf");
        cloneClass91.setClass10(new CloneClass10("sss"));
        showData(txt2);
    }

    private void showData(TextView txt1) {
        txt1.setText("");
        txt1.append(cloneClass9.toString());
        txt1.append("\n");
        txt1.append(cloneClass91.toString());
        txt1.append("\n");
        txt1.append("cloneClass9.hashCode() --> " + cloneClass9.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass9.getClass10().hashCode() --> " + cloneClass9.getClass10().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass91.hashCode() --> " + cloneClass91.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass91.getClass10().hashCode() --> " + cloneClass91.getClass10().hashCode());
    }
}
