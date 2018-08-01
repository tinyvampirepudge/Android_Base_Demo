package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass14;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass15;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass16;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class JavaCloneActivity6 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_2)
    Button btnJavaClone2;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass14 cloneClass14;
    private CloneClass14 cloneClass141;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone6;
    }

    @Override
    protected void buildContentView() {
        CloneClass16 cloneClass16 = new CloneClass16("CloneClass16");
        Map<String, CloneClass16> cloneClass16Map = new HashMap();
        cloneClass16Map.put("CloneClass16", cloneClass16);
        Map<String, CloneClass15> cloneClass15Map = new HashMap<>();
        CloneClass15 cloneClass15 = new CloneClass15("CloneClass15", cloneClass16Map);
        cloneClass15Map.put("CloneClass15", cloneClass15);
        cloneClass14 = new CloneClass14("CloneClass14", cloneClass15Map);
        cloneClass141 = cloneClass14.clone();
    }

    @Override
    protected void initViewData() {
        showData(txt1);
        btnJavaClone2.setOnClickListener(v -> {
            cloneClass141.setName("abc");
            cloneClass141.getMap().get("CloneClass15").setName("def");
            cloneClass141.getMap().get("CloneClass15").getMap().get("CloneClass16").setName("ghi");
            showData(txt2);
        });
    }

    private void showData(TextView txt1) {
        txt1.setText("");
        txt1.append(cloneClass14.toString());
        txt1.append("\n");
        txt1.append(cloneClass141.toString());
        txt1.append("\n");
        txt1.append("\n");
        txt1.append("cloneClass14.hashCode() --> " + cloneClass14.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass14.getMap().hashCode() --> " + cloneClass14.getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass14.getMap().get(\"CloneClass15\").hashCode() --> " + cloneClass14.getMap().get("CloneClass15").hashCode());
        txt1.append("\n");
        txt1.append("cloneClass14.getMap().get(\"CloneClass15\").getMap().get(\"CloneClass16\").hashCode() --> " + cloneClass14.getMap().get("CloneClass15").getMap().get("CloneClass16").hashCode());
        txt1.append("\n");
        txt1.append("\n");
        txt1.append("cloneClass141.hashCode() --> " + cloneClass141.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass141.getMap().hashCode() --> " + cloneClass141.getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass141.getMap().get(\"CloneClass15\").hashCode() --> " + cloneClass141.getMap().get("CloneClass15").hashCode());
        txt1.append("\n");
        txt1.append("cloneClass141.getMap().get(\"CloneClass15\").getMap().get(\"CloneClass16\").hashCode() --> " + cloneClass141.getMap().get("CloneClass15").getMap().get("CloneClass16").hashCode());
    }
}
