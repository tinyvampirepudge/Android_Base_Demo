package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass11;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass12;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass13;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class JavaCloneActivity5 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_2)
    Button btnJavaClone2;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass11 cloneClass11;
    private CloneClass11 cloneClass111;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone5;
    }

    @Override
    protected void buildContentView() {
        List<CloneClass13> class13s = new ArrayList<>();
        class13s.add(new CloneClass13("CloneClass13"));
        List<CloneClass12> class12s = new ArrayList<>();
        class12s.add(new CloneClass12("CloneClass12", class13s));
        cloneClass11 = new CloneClass11("CLoneClass11", class12s);
        cloneClass111 = cloneClass11.clone();
    }

    @Override
    protected void initViewData() {
        showData(txt1);
    }

    @OnClick(R.id.btn_java_clone_2)
    public void onViewClicked() {
        cloneClass111.setName("abc");
        cloneClass111.getClass12List().get(0).setName("def");
        cloneClass111.getClass12List().get(0).getClass13List().get(0).setName("ghi");
        showData(txt2);
    }

    private void showData(TextView txt1) {
        txt1.setText("");
        txt1.append(cloneClass11.toString());
        txt1.append("\n");
        txt1.append(cloneClass111.toString());
        txt1.append("\n");
        txt1.append("cloneClass11.hashCode() --> " + cloneClass11.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass11.getClass12List().hashCode() --> " + cloneClass11.getClass12List().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass11.getClass12List().get(0).hashCode() --> " + cloneClass11.getClass12List().get(0).hashCode());
        txt1.append("\n");
        txt1.append("cloneClass111.hashCode() --> " + cloneClass111.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass111.getClass12List().hashCode() --> " + cloneClass111.getClass12List().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass111.getClass12List().get(0).hashCode() --> " + cloneClass111.getClass12List().get(0).hashCode());
        txt1.append("\n");
    }
}
