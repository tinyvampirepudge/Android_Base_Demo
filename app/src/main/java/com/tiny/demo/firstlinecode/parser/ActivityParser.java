package com.tiny.demo.firstlinecode.parser;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiny.demo.firstlinecode.base.BaseActivity;
import com.tiny.demo.firstlinecode.R;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopListener;
import com.tiny.demo.firstlinecode.customview.pickerview.LoopView;
import com.tiny.demo.firstlinecode.parser.json.CityListBean;
import com.tiny.demo.firstlinecode.parser.plist.PListXMLHandler;
import com.tiny.demo.firstlinecode.parser.plist.PListXMLParser;
import com.tiny.demo.firstlinecode.parser.plist.bean.CityBean;
import com.tiny.demo.firstlinecode.parser.plist.bean.ProvinceBean;
import com.tiny.demo.firstlinecode.parser.plist.domain.Array;
import com.tiny.demo.firstlinecode.parser.plist.domain.Dict;
import com.tiny.demo.firstlinecode.parser.plist.domain.PList;
import com.tiny.demo.firstlinecode.parser.plist.domain.PListObject;
import com.tiny.demo.firstlinecode.parser.plist.domain.Real;
import com.tiny.demo.firstlinecode.common.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析文件的页面：
 * ①json(hashmap类型);
 * ②plist
 */
public class ActivityParser extends BaseActivity {

    private TextView txtLocation;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissSelectLocationDialog();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ActivityParser.class);
        context.startActivity(intent);
    }

    // 所在地数据集合
    private boolean loadSuccess = false;//本地json数据是否加载完成。
    private HashMap<String, List<CityListBean>> mProvinceMap;
    private List<Map.Entry<String, String>> mProvinceMapList;
    private Dialog selectLocationDialog = null;
    //从plist中解析出的数据
    List<ProvinceBean> provinceList = new ArrayList<>();

    @Override
    protected int setContentLayout() {
        return R.layout.activity_parser;
    }

    @Override
    protected void buildContentView() {
        new Thread(() -> initJsonData()).start();
        findViewById(R.id.btn_parser_json).setOnClickListener(v -> initJsonData());

        findViewById(R.id.btn_select_location).setOnClickListener(v -> {
            if (loadSuccess && mProvinceMap != null && mProvinceMap.size() > 0 && mProvinceMapList != null && mProvinceMapList.size() > 0) {
                showSelectLocationDialog();
            } else {
                LogUtils.e("省市数据加载失败。");
            }
        });

        txtLocation = (TextView) findViewById(R.id.txt_selected_location);

        findViewById(R.id.btn_parse_plist).setOnClickListener(v -> parsePlist());
        findViewById(R.id.btn_parse_plist1).setOnClickListener(v -> parsePlistOfficial());
    }

    @Override
    protected void initViewData() {

    }

    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     * 解析的是hashmap类型的json文件。
     * 最后给hashmap文件排序（转换为list）。
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");
            int len = -1;
//            byte[] buf = new byte[1024];//部分中文会乱码。
            byte[] buf = new byte[is.available()];//为了解决部分中文乱码问题，一次读取所有的
            while ((len = is.read(buf)) != -1) {
                sb.append(new java.lang.String(buf, 0, len, "UTF-8"));
            }
            is.close();
            LogUtils.e("sb.toString() --> " + sb.toString());
            if (!TextUtils.isEmpty(sb.toString())) {
                mProvinceMap = new Gson().fromJson(
                        sb.toString(), new TypeToken<HashMap<String, List<CityListBean>>>() {
                        }.getType()
                );

                //遍历hashmap, 并取值。
                HashMap<String, String> mProvinceMap = new HashMap<>();
                Iterator<Map.Entry<String, List<CityListBean>>> iterator = this.mProvinceMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, List<CityListBean>> entry = iterator.next();
                    String key = entry.getKey();
                    ArrayList<CityListBean> value = (ArrayList<CityListBean>) entry.getValue();
                    java.lang.String provinceName = "";
                    if (value != null && value.size() > 0) {
                        provinceName = value.get(0).province;
                        LogUtils.e(provinceName);
                    }
                    mProvinceMap.put(key, provinceName);
                }
                //对mProvinceMap根据key进行排序
                mProvinceMapList = new ArrayList<>(mProvinceMap.entrySet());
                Collections.sort(mProvinceMapList, new Comparator<Map.Entry<String, String>>() {
                    @Override
                    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                });
                for (Map.Entry<String, String> mapping : mProvinceMapList) {
                    LogUtils.e(mapping.getKey() + ":" + mapping.getValue());
                }
                loadSuccess = true;
            }
        } catch (IOException e) {
            LogUtils.e("json文件读取失败");
            e.printStackTrace();
        }
    }

    /**
     * 选择所在地
     */

    private void showSelectLocationDialog() {
        if (selectLocationDialog == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_user_location, null);
            selectLocationDialog = new Dialog(mContext, R.style.transparentFrameWindowStyleFull);
            selectLocationDialog.setContentView(view);
            //对view内部进行设置。
            //loopview setting
            final LoopView loopviewProvince = (LoopView) view.findViewById(R.id.loopview_province);
            final LoopView loopviewCity = (LoopView) view.findViewById(R.id.loopview_city);
            //do not loop,default can loop
            loopviewProvince.setNotLoop();
            loopviewCity.setNotLoop();
            //set loopview text btnTextsize
            loopviewProvince.setTextSize(16f);
            loopviewCity.setTextSize(16f);
            //set Data
            final int[] pos = new int[2];
            final ArrayList<String> pList = new ArrayList<>();
            if (mProvinceMapList != null && mProvinceMapList.size() > 0) {
                for (int j = 0; j < mProvinceMapList.size(); j++) {
                    pList.add(mProvinceMapList.get(j).getValue());
                }
            }
            loopviewProvince.setArrayList(pList);
            resetCityLoopview(pos, loopviewCity);
            loopviewProvince.setListener(new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    pos[0] = item;
                    //reset city loopview
                    resetCityLoopview(pos, loopviewCity);
                }
            });
            loopviewCity.setListener(new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    pos[1] = item;
                }
            });

            view.findViewById(R.id.txt_cancel_dialog_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissSelectLocationDialog();
                }
            });
            view.findViewById(R.id.txt_ok_dialog_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissSelectLocationDialog();
                    String pString = "";
                    String cString = "";
                    String cId = "";
                    String key = "";
                    if (mProvinceMapList != null && mProvinceMapList.size() > pos[0]) {
                        key = mProvinceMapList.get(pos[0]).getKey();
                        LogUtils.e("ok select province --> " + mProvinceMapList.get(pos[0]).getValue());
                        pString = mProvinceMapList.get(pos[0]).getValue();
                    }
                    if (!TextUtils.isEmpty(key) && mProvinceMap != null && mProvinceMap.size() > 0) {
                        List<CityListBean> list = mProvinceMap.get(key);
                        if (list != null && list.size() > pos[1]) {
                            LogUtils.e("ok select city --> " + list.get(pos[1]).name);
                            cString = list.get(pos[1]).name;
                            cId = list.get(pos[1]).id;
                        }
                    }
                    if (!TextUtils.isEmpty(pString) && !TextUtils.isEmpty(cString)
                            && !TextUtils.isEmpty(key) && !TextUtils.isEmpty(cId)) {
                        txtLocation.setText(pString + " " + cString);
                    }
                }
            });

            //设置显示位置、高度、宽度等属性。
            Window window = selectLocationDialog.getWindow();
            //显示在屏幕下方，默认显示在中间。
            window.setGravity(Gravity.BOTTOM);
            // 设置显示动画
            window.setWindowAnimations(R.style.up_down_menu_animstyle);
            //设置下边距为20px.
            //设置高度和宽度。默认水平方向不会铺满屏幕，这里设置后就可以铺满屏幕。
            WindowManager.LayoutParams wl = window.getAttributes();
            wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(wl);
            // 设置点击外围消失
            selectLocationDialog.setCanceledOnTouchOutside(true);
        }
        selectLocationDialog.show();
    }

    private void dismissSelectLocationDialog() {
        if (selectLocationDialog != null && selectLocationDialog.isShowing()) {
            selectLocationDialog.dismiss();
        }
    }

    private void resetCityLoopview(int[] pos, LoopView loopviewCity) {
        pos[1] = 0;
        ArrayList<String> cList = new ArrayList<>();
        String key = "";
        if (mProvinceMapList != null && mProvinceMapList.size() > pos[0]) {
            key = mProvinceMapList.get(pos[0]).getKey();
        }
        if (!TextUtils.isEmpty(key) && mProvinceMap != null && mProvinceMap.size() > 0) {
            List<CityListBean> list = mProvinceMap.get(key);
            for (int j = 0; j < list.size(); j++) {
                cList.add(list.get(j).name);
            }
        }
        loopviewCity.setArrayList(cList);
        loopviewCity.setTotalScrollY(0);
    }

    /**
     * 解析plist文件，必须保证plist文件的正确性。
     */
    private void parsePlist() {
        new Thread(() -> {
            // 基于SAX的实现
            PListXMLParser parser = new PListXMLParser();
            PListXMLHandler handler = new PListXMLHandler();
            parser.setHandler(handler);

            try {
                // area.plist是你要解析的文件，该文件需放在assets文件夹下
                parser.parse(getAssets().open("area.plist"));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();
            Dict root = (Dict) actualPList.getRootElement();

            Map<String, PListObject> provinceCities = root.getConfigMap();

            for (int i = 0; i < provinceCities.keySet().size(); i++) {

                Dict provinceRoot = (Dict) provinceCities.get(String.valueOf(i));
                Map<String, PListObject> province = provinceRoot.getConfigMap();

                String provinceName = province.keySet().iterator().next();
                // 打印省份
                LogUtils.e("省份为:" + provinceName);

                Dict cityRoot = (Dict) province.get(provinceName);

                Map<String, PListObject> cities = cityRoot.getConfigMap();

                for (int j = 0; j < cities.keySet().size(); j++) {
                    Dict city = (Dict) cities.get(String.valueOf(j));
                    String cityName = city.getConfigMap().keySet().iterator().next();
                    // 打印城市
                    LogUtils.e("城市为:" + cityName);
                    Array districts = city.getConfigurationArray(cityName);
                    for (int k = 0; k < districts.size(); k++) {
                        com.tiny.demo.firstlinecode.parser.plist.domain.String district = (com.tiny.demo.firstlinecode.parser.plist.domain.String) districts.get(k);
                        // 打印地区
                        LogUtils.e("地区为:" + district.getValue());
                    }
                }
            }
        }).start();
    }

    /**
     * 读取ios端的plist文件。
     */
    private void parsePlistOfficial() {
        new Thread(() -> {
            // 基于SAX的实现
            PListXMLParser parser = new PListXMLParser();
            PListXMLHandler handler = new PListXMLHandler();
            parser.setHandler(handler);

            try {
                // area.plist是你要解析的文件，该文件需放在assets文件夹下
                parser.parse(getAssets().open("ProvincesAndCities.plist"));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            provinceList = new ArrayList<>();//添加数据
            PList actualPList = ((PListXMLHandler) parser.getHandler()).getPlist();
            //获取array
            Array root = (Array) actualPList.getRootElement();
            if (!root.isEmpty()) {
                LogUtils.e("root.size() --> " + root.size());
                for (int j = 0; j < root.size(); j++) {
                    ProvinceBean provinceBean = new ProvinceBean();
                    Dict province = (Dict) root.get(j);
                    if (province != null) {
                        Map<String, PListObject> map = province.getConfigMap();
                        Iterator<Map.Entry<String, PListObject>> i = map.entrySet().iterator();
                        while (i != null && i.hasNext()) {
                            Map.Entry<String, PListObject> entry = i.next();
                            LogUtils.e("entry.getKey() --> " + entry.getKey());
                            switch (entry.getKey()) {
                                case "State"://String
                                    LogUtils.e("State Value --> "
                                            + ((com.tiny.demo.firstlinecode.parser.plist.domain.String) entry.getValue()).getValue());
                                    provinceBean.state = ((com.tiny.demo.firstlinecode.parser.plist.domain.String) entry.getValue()).getValue();
                                    break;
                                case "Cities"://array
                                    LogUtils.e("Cities Value --> " + entry.getValue());
                                    Array cityArray = (Array) entry.getValue();
                                    provinceBean.cities = new ArrayList<CityBean>();
                                    if (!cityArray.isEmpty()) {
                                        LogUtils.e("cityArray.size() --> " + cityArray.size());
                                        for (int m = 0; m < cityArray.size(); m++) {
                                            CityBean cityBean = new CityBean();
                                            Dict city = (Dict) cityArray.get(m);
                                            Map<String, PListObject> mapCity = city.getConfigMap();
                                            Iterator<Map.Entry<String, PListObject>> iCity = mapCity.entrySet().iterator();
                                            while (iCity != null && iCity.hasNext()) {
                                                Map.Entry<String, PListObject> entryCity = iCity.next();
                                                String key = entryCity.getKey();
                                                LogUtils.e("key --> " + key);
                                                switch (key) {
                                                    case "city"://String
                                                        String valueCity = ((com.tiny.demo.firstlinecode.parser.plist.domain.String) entryCity.getValue()).getValue();
                                                        LogUtils.e("valueCity --> " + valueCity);
                                                        cityBean.city = valueCity;
                                                        break;
                                                    case "lat"://real
                                                        float valueLat = ((Real) entryCity.getValue()).getValue();
                                                        LogUtils.e("valueLat --> " + valueLat);
                                                        cityBean.lat = String.valueOf(valueLat);
                                                        break;
                                                    case "lon"://real
                                                        float valueLon = ((Real) entryCity.getValue()).getValue();
                                                        LogUtils.e("valueLon --> " + valueLon);
                                                        cityBean.lon = String.valueOf(valueLon);
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                            provinceBean.cities.add(cityBean);
                                        }
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    provinceList.add(provinceBean);
                }

                for (int j = 0; j < provinceList.size(); j++) {
                    LogUtils.e("provinceList.get(j).state --> " + provinceList.get(j).state);
                    LogUtils.e("provinceList.get(j).cities --> " + provinceList.get(j).cities);
                }
            } else {
                LogUtils.e("root -- null");
            }
        }).start();
    }

}
