package com.tiny.demo.firstlinecode.javareference.copy;

import android.os.Bundle;
import android.widget.TextView;

import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass17;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass18;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass19;
import com.tiny.demo.firstlinecode.javareference.copy.entity.CloneClass20;
import com.tiny.demo.firstlinecode.javareference.copy.entity.ObjectSerializableDeepCopyBean;
import com.tiny.demo.firstlinecode.javareference.copy.utils.DeepCopyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: Java序列化和反序列化 实现深复制
 * 深复制工具类测试
 * @Author wangjianzhou@qding.me
 * @Date 2019-08-10 10:24
 * @Version
 */
public class JavaCloneActivity8 extends BaseActivity {
    public static final String TAG = JavaCloneActivity8.class.getSimpleName();
    @BindView(R.id.txt_object)
    TextView txtObject;
    @BindView(R.id.txt_object_result)
    TextView txtObjectResult;
    @BindView(R.id.txt_list)
    TextView txtList;
    @BindView(R.id.txt_list_result)
    TextView txtListResult;
    @BindView(R.id.txt_map)
    TextView txtMap;
    @BindView(R.id.txt_map_result)
    TextView txtMapResult;
    @BindView(R.id.txt_array)
    TextView txtArray;
    @BindView(R.id.txt_array_result)
    TextView txtArrayResult;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_java_clone8;
    }

    @Override
    protected void buildContentView() {
        testObject();
        testList();
        testMap();
        testArray();
    }

    @Override
    protected void initViewData() {

    }

    private void testObject() {
        ObjectSerializableDeepCopyBean objectSource = mockObject();
        StringBuilder sbSource = new StringBuilder("普通Object（包含数组、List、Map）源数据：\n");
        sbSource.append("objectSource.toString(): " + objectSource.toString());
        sbSource.append("\n");

        txtObject.setText(sbSource.toString());

        ObjectSerializableDeepCopyBean objectTarget = DeepCopyUtils.deepCopyObject(objectSource);

        objectTarget.setAge(123456);
        objectTarget.getList().get(0).setListName("啊哈哈哈哈");
        objectTarget.getList().get(0).getArray()[0].setListArrayName("猫了个咪");
        objectTarget.getList().get(0).getMap().get("4440").setListMapAge(123456789);

        StringBuilder sbTarget = new StringBuilder("修改Copy后的结果：\n");
        sbTarget.append("objectTarget.toString(): " + objectTarget.toString());
        sbTarget.append("\n");

        txtObjectResult.setText(sbTarget.toString());
    }

    private void testList() {
        List<ObjectSerializableDeepCopyBean> listSource = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean objectSource = mockObject();
            listSource.add(objectSource);
        }

        StringBuilder sbSource = new StringBuilder("List数据（包含数组、List、Map）源数据：\n");
        sbSource.append("listSource.toString(): " + listSource.toString());
        sbSource.append("\n");

        txtList.setText(sbSource.toString());

        List<ObjectSerializableDeepCopyBean> listTarget = DeepCopyUtils.deepCopyObject(listSource);

        ObjectSerializableDeepCopyBean listItemTarget = listTarget.get(1);
        listItemTarget.setAge(123456);
        listItemTarget.getList().get(0).setListName("啊哈哈哈哈");
        listItemTarget.getList().get(0).getArray()[0].setListArrayName("猫了个咪");
        listItemTarget.getList().get(0).getMap().get("4440").setListMapAge(123456789);

        StringBuilder sbTarget = new StringBuilder("修改Copy后的结果：\n");
        sbTarget.append("listTarget.toString(): " + listTarget.toString());
        sbTarget.append("\n");

        txtListResult.setText(sbTarget.toString());
    }

    private void testMap() {
        Map<String, ObjectSerializableDeepCopyBean> mapSource = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean objectSource = mockObject();
            mapSource.put("aaa" + i, objectSource);
        }

        StringBuilder sbSource = new StringBuilder("Map数据（包含数组、List、Map）源数据：\n");
        sbSource.append("mapSource.toString(): " + mapSource.toString());
        sbSource.append("\n");

        txtMap.setText(sbSource.toString());

        Map<String, ObjectSerializableDeepCopyBean> mapTarget = DeepCopyUtils.deepCopyObject(mapSource);

        ObjectSerializableDeepCopyBean listItemTarget = mapTarget.get("aaa0");
        listItemTarget.setAge(123456);
        listItemTarget.getList().get(0).setListName("啊哈哈哈哈");
        listItemTarget.getList().get(0).getArray()[0].setListArrayName("猫了个咪");
        listItemTarget.getList().get(0).getMap().get("4440").setListMapAge(123456789);

        StringBuilder sbTarget = new StringBuilder("修改Copy后的结果：\n");
        sbTarget.append("mapTarget.toString(): " + mapTarget.toString());
        sbTarget.append("\n");

        txtMapResult.setText(sbTarget.toString());
    }

    private void testArray() {

        ObjectSerializableDeepCopyBean[] arraySource = new ObjectSerializableDeepCopyBean[3];
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean objectSource = mockObject();
            arraySource[i] = objectSource;
        }

        StringBuilder sbSource = new StringBuilder("数组数据（包含数组、List、Map）源数据：\n");
        sbSource.append("arraySource.toString(): " + arraySource.toString());
        sbSource.append("\n");

        txtArray.setText(sbSource.toString());

        ObjectSerializableDeepCopyBean[] arrayTarget = DeepCopyUtils.deepCopyObject(arraySource);

        ObjectSerializableDeepCopyBean listItemTarget = arrayTarget[2];
        listItemTarget.setAge(123456);
        listItemTarget.getList().get(0).setListName("啊哈哈哈哈");
        listItemTarget.getList().get(0).getArray()[0].setListArrayName("猫了个咪");
        listItemTarget.getList().get(0).getMap().get("4440").setListMapAge(123456789);

        StringBuilder sbTarget = new StringBuilder("修改Copy后的结果：\n");
        sbTarget.append("arrayTarget.toString(): " + arrayTarget.toString());
        sbTarget.append("\n");

        txtArrayResult.setText(sbTarget.toString());

    }

    private ObjectSerializableDeepCopyBean mockObject() {
        ObjectSerializableDeepCopyBean object = new ObjectSerializableDeepCopyBean();
        object.setName("111");
        object.setAge(111);

        List<ObjectSerializableDeepCopyBean.ListBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean.ListBean listBean = new ObjectSerializableDeepCopyBean.ListBean();
            listBean.setListName("222" + i);
            listBean.setListAge(222 + i);

            Map<String, ObjectSerializableDeepCopyBean.ListBean.ListMapBean> listMapBeanMap = new HashMap<>();
            for (int j = 0; j < 3; j++) {
                ObjectSerializableDeepCopyBean.ListBean.ListMapBean mapBean
                        = new ObjectSerializableDeepCopyBean.ListBean.ListMapBean("333" + j, 333 + j);
                listMapBeanMap.put("444" + j, mapBean);
            }
            listBean.setMap(listMapBeanMap);

            ObjectSerializableDeepCopyBean.ListBean.ListArrayBean[] arrayBeans = new ObjectSerializableDeepCopyBean.ListBean.ListArrayBean[3];
            for (int m = 0; m < 3; m++) {
                ObjectSerializableDeepCopyBean.ListBean.ListArrayBean listArrayBean
                        = new ObjectSerializableDeepCopyBean.ListBean.ListArrayBean("555" + m, 555 + m);
                arrayBeans[m] = listArrayBean;
            }
            listBean.setArray(arrayBeans);

            list.add(listBean);
        }

        Map<String, ObjectSerializableDeepCopyBean.MapBean> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean.MapBean mapBean = new ObjectSerializableDeepCopyBean.MapBean();
            mapBean.setMapName("666" + i);
            mapBean.setMapAge(666 + i);

            List<ObjectSerializableDeepCopyBean.MapBean.MapListBean> mapListBeanList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ObjectSerializableDeepCopyBean.MapBean.MapListBean mapListBean
                        = new ObjectSerializableDeepCopyBean.MapBean.MapListBean("777" + j, 777 + j);
                mapListBeanList.add(mapListBean);
            }
            mapBean.setList(mapListBeanList);

            ObjectSerializableDeepCopyBean.MapBean.MapArrayBean[] mapArrayBeans = new ObjectSerializableDeepCopyBean.MapBean.MapArrayBean[3];
            for (int m = 0; m < 3; m++) {
                ObjectSerializableDeepCopyBean.MapBean.MapArrayBean mapArrayBean =
                        new ObjectSerializableDeepCopyBean.MapBean.MapArrayBean("888" + m, 888 + m);
                mapArrayBeans[m] = mapArrayBean;
            }
            mapBean.setArrays(mapArrayBeans);

            map.put("999" + i, mapBean);
        }
        object.setMap(map);

        ObjectSerializableDeepCopyBean.ArrayBean[] arrayBeans = new ObjectSerializableDeepCopyBean.ArrayBean[3];
        for (int i = 0; i < 3; i++) {
            ObjectSerializableDeepCopyBean.ArrayBean arrayBean = new ObjectSerializableDeepCopyBean.ArrayBean();
            arrayBean.setArrayName("aaa" + i);
            arrayBean.setArrayAge(101010 + i);

            List<ObjectSerializableDeepCopyBean.ArrayBean.ArrayListBean> arrayListBeanList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                ObjectSerializableDeepCopyBean.ArrayBean.ArrayListBean arrayListBean =
                        new ObjectSerializableDeepCopyBean.ArrayBean.ArrayListBean("bbb" + j, 111111 + j);
                arrayListBeanList.add(arrayListBean);
            }
            arrayBean.setList(arrayListBeanList);

            Map<String, ObjectSerializableDeepCopyBean.ArrayBean.ArrayMapBean> arrayMapBeanMap = new HashMap<>();
            for (int m = 0; m < 3; m++) {
                ObjectSerializableDeepCopyBean.ArrayBean.ArrayMapBean arrayMapBean =
                        new ObjectSerializableDeepCopyBean.ArrayBean.ArrayMapBean("ccc" + m, 121212 + m);
                arrayMapBeanMap.put("ddd" + m, arrayMapBean);
            }
            arrayBean.setMap(arrayMapBeanMap);

            arrayBeans[i] = arrayBean;
        }
        object.setArrays(arrayBeans);


        object.setList(list);

        return object;
    }
}
