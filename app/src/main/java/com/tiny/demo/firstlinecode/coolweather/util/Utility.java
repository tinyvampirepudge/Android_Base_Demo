package com.tiny.demo.firstlinecode.coolweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tiny.demo.firstlinecode.coolweather.db.City;
import com.tiny.demo.firstlinecode.coolweather.db.County;
import com.tiny.demo.firstlinecode.coolweather.db.Province;
import com.tiny.demo.firstlinecode.coolweather.gson.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 87959 on 2017/6/3.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int j = 0; j < allProvinces.length(); j++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(j);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解释和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCityes = new JSONArray(response);
                for (int j = 0; j < allCityes.length(); j++) {
                    JSONObject cityObject = allCityes.getJSONObject(j);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int j = 0; j < allCounties.length(); j++) {
                    JSONObject countyObject = allCounties.getJSONObject(j);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSOn数据解析成Weather实体类
     */
    public static WeatherInfo handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, WeatherInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
