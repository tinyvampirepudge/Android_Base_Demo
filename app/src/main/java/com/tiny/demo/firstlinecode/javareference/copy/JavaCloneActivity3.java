package com.tiny.demo.firstlinecode.javareference.copy;

import android.widget.Button;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tinytongtong.tinyutils.LogUtils;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass6;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass7;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass8;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: Cloneable接口和clone方法实现java深度克隆
 * 针对Map类型
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:04
 * @Version TODO
 */
public class JavaCloneActivity3 extends BaseActivity {

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.btn_java_clone_2)
    Button btnJavaClone2;
    @BindView(R.id.txt2)
    TextView txt2;
    private CloneClass6 cloneClass6;
    private CloneClass6 cloneClass61;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone3;
    }

    @Override
    protected void buildContentView() {
        cloneClass6 = new CloneClass6();
        cloneClass6.setName("CloneClass6");
        HashMap<String, CloneClass7> class7HashMap = new HashMap<>();
        CloneClass7 cloneClass7 = new CloneClass7();
        cloneClass7.setName("CloneClass7");
        class7HashMap.put("CloneClass7", cloneClass7);
        HashMap<String, CloneClass8> class8HashMap = new HashMap<>();
        CloneClass8 cloneClass8 = new CloneClass8();
        cloneClass8.setName("CloneClass8");
        class8HashMap.put("CloneClass8", cloneClass8);
        cloneClass7.setMap(class8HashMap);
        cloneClass6.setMap(class7HashMap);

        cloneClass61 = (CloneClass6) cloneClass6.clone();

        //HashMap的深复制
        //clone方法和pubAll方法的区别
        HashMap<String, String> mapSource = new HashMap<>();
        HashMap<String, String> mapDeepClone = new HashMap<>();
        HashMap<String, String> mapShadowClone = new HashMap<>();
        mapSource.put("1", "1");
        //hashmap deep clone
        mapDeepClone = (HashMap<String, String>) mapSource.clone();
        //hash shadow clone
        mapShadowClone.putAll(mapSource);
        LogUtils.INSTANCE.e("mapSource.hashCode() --> " + mapSource.hashCode());
        LogUtils.INSTANCE.e("mapDeepClone.hashCode() --> " + mapDeepClone.hashCode());
        LogUtils.INSTANCE.e("mapShadowClone.hashCode() --> " + mapShadowClone.hashCode());
        mapSource.put("1", "2");
        mapDeepClone.put("4", "5");
        mapShadowClone.put("7", "8");
        LogUtils.INSTANCE.e("mapSource.toString() --> " + mapSource.toString());
        LogUtils.INSTANCE.e("mapDeepClone.toString() --> " + mapDeepClone.toString());
        LogUtils.INSTANCE.e("mapShadowClone.toString() --> " + mapShadowClone.toString());
    }

    @Override
    protected void initViewData() {
        showData(txt1);
    }

    public void showData(TextView txt1) {
        txt1.setText("");
        txt1.append(cloneClass6.toString());
        txt1.append("\n");
        txt1.append(cloneClass61.toString());
        txt1.append("\n");
        txt1.append("cloneClass6.hashCode() --> " + cloneClass6.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass6.getMap().hashCode() --> " + cloneClass6.getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass6.getMap().get(\"CloneClass7\").getMap().hashCode() --> " + cloneClass6.getMap().get("CloneClass7").getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass6.getMap().get(\"CloneClass7\").getMap().get(\"CloneClass8\").hashCode() --> " + cloneClass6.getMap().get("CloneClass7").getMap().get("CloneClass8").hashCode());
        txt1.append("\n");
        txt1.append("cloneClass61.hashCode() --> " + cloneClass61.hashCode());
        txt1.append("\n");
        txt1.append("cloneClass61.getMap().hashCode() --> " + cloneClass61.getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass61.getMap().get(\"CloneClass7\").getMap().hashCode() --> " + cloneClass61.getMap().get("CloneClass7").getMap().hashCode());
        txt1.append("\n");
        txt1.append("cloneClass61.getMap().get(\"CloneClass7\").getMap().get(\"CloneClass8\").hashCode() --> " + cloneClass61.getMap().get("CloneClass7").getMap().get("CloneClass8").hashCode());
    }

    @OnClick(R.id.btn_java_clone_2)
    public void onViewClicked() {
        cloneClass61.setName("abc");
        cloneClass61.getMap().get("CloneClass7").setName("def");
        cloneClass61.getMap().get("CloneClass7").getMap().get("CloneClass8").setName("ghi");
        showData(txt2);
    }
}
